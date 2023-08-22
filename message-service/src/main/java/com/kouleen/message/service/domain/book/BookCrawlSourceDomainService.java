package com.kouleen.message.service.domain.book;

import com.alibaba.fastjson.JSON;
import com.kouleen.message.api.infrastructure.constants.FictionTypeEnum;
import com.kouleen.message.api.infrastructure.constants.MessageConstant;
import com.kouleen.message.api.infrastructure.constants.ResultCodeEnum;
import com.kouleen.message.api.interfaces.dto.BookCrawlRuleDTO;
import com.kouleen.message.api.interfaces.dto.BookCrawlSourceDTO;
import com.kouleen.message.api.interfaces.vo.SystemConfigVO;
import com.kouleen.message.service.application.resp.RemoteSystemConfigFeignService;
import com.kouleen.message.service.application.service.BookCrawlSourceService;
import com.kouleen.message.service.application.service.BookFictionHeaderService;
import com.kouleen.message.service.domain.book.entity.BookCrawlSource;
import com.kouleen.message.service.domain.book.entity.BookFictionContent;
import com.kouleen.message.service.domain.book.entity.BookFictionHeader;
import com.kouleen.message.service.domain.book.entity.BookFictionLine;
import com.kouleen.message.service.infrastructure.utils.HttpUtils;
import com.kouleen.message.service.infrastructure.utils.IDUtils;
import com.kouleen.message.service.infrastructure.utils.ThreadUtil;
import com.kouleen.message.service.interfaces.assembler.BookCrawlSourceAssembler;
import com.kouleen.message.service.interfaces.handle.AbstractBookCrawlSourceHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangqing
 * @since 2023/8/3 18:30
 */
@Slf4j
@Service
public class BookCrawlSourceDomainService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private BookCrawlSourceService bookCrawlSourceService;

    @Autowired
    private BookFictionHeaderService bookFictionHeaderService;

    @Autowired
    private BookFictionDomainService bookFictionDomainService;

    @Autowired
    private RemoteSystemConfigFeignService remoteSystemConfigFeignService;

    public Boolean modifyBookCrawlSource(BookCrawlSourceDTO bookCrawlSourceDTO) {
        log.info("[书籍爬取服务v2] 修改爬虫 {}", JSON.toJSONString(bookCrawlSourceDTO));
        BookCrawlSource bookCrawlSource = bookCrawlSourceService.getBookCrawlSource(bookCrawlSourceDTO.getId());
        Assert.notNull(bookCrawlSource, ResultCodeEnum.DATA_IS_NOT_EXISTS_EXPIRED.getDesc());
        if (bookCrawlSourceDTO.getSourceStatus() == 0) {
            if(bookCrawlSourceDTO.getSourceStatus().equals(bookCrawlSource.getSourceStatus())){
                return true;
            }
            bookCrawlSource.setSourceStatus(0);
            bookCrawlSourceService.updateBookCrawlSource(bookCrawlSource);
            String threadIdStr = redisTemplate.opsForValue().get(MessageConstant.THREAD_ID + bookCrawlSourceDTO.getId());
            Collection<Long> threadIdList = JSON.parseArray(threadIdStr, Long.class);
            ThreadUtil.closeThread(threadIdList);
            redisTemplate.delete(MessageConstant.THREAD_ID + bookCrawlSourceDTO.getId());
        } else {
            if (bookCrawlSource.getSourceStatus() == 0) {
                bookCrawlSource.setSourceStatus(1);
                bookCrawlSourceService.updateBookCrawlSource(bookCrawlSource);
                Map<String, SystemConfigVO> systemConfigMap = remoteSystemConfigFeignService.convertItemMapSystemConfig(bookCrawlSource.getDictHeaderCode());
                Map<String, SystemConfigVO> globalControllerMap = remoteSystemConfigFeignService.convertItemMapSystemConfig(MessageConstant.GLOBAL_CONTROLLER);
                SystemConfigVO globalController = globalControllerMap.get(MessageConstant.NOVEL_PROGRESS_TYPES);
                SystemConfigVO systemConfigVO = systemConfigMap.get(bookCrawlSource.getDictLineCode());
                if(!ObjectUtils.isEmpty(systemConfigVO)){
                    String remark = systemConfigVO.getRemark();
                    BookCrawlRuleDTO bookCrawlRuleDTO = JSON.parseObject(remark, BookCrawlRuleDTO.class);
                    Set<Long> threadIds = new HashSet<>();
                    for (int i = 0; i < 8; i++) {
                        final int catId = i;
                        Thread thread = new Thread(() -> BookCrawlSourceAssembler.parseBookList(catId, bookCrawlRuleDTO, bookCrawlSource.getId(), new AbstractBookCrawlSourceHandle() {
                            @Override
                            public void parseBookList(int id, BookCrawlRuleDTO bookCrawlRuleDTO, Long bookCrawlSourceId) {
                                int page = 1;
                                int totalPage = page;
                                while (page <= totalPage) {
                                    Map<String, String> catIdRule = bookCrawlRuleDTO.getCatIdRule();
                                    String catId = catIdRule.get("catId" + id);
                                    if(StringUtils.hasText(catId)){
                                        String bookListUrl = bookCrawlRuleDTO.getBookListUrl();
                                        bookListUrl = bookListUrl.replace("{catId}", catId);
                                        bookListUrl = bookListUrl.replace("{page}", page + "");
                                        String bookListHtml = HttpUtils.getByHttpClientWithChrome(bookListUrl);
                                        if(StringUtils.hasText(bookListHtml)){
                                            Pattern bookIdPatten = Pattern.compile(bookCrawlRuleDTO.getBookIdPatten());
                                            Matcher bookIdMatcher = bookIdPatten.matcher(bookListHtml);
                                            boolean isFindBookId = bookIdMatcher.find();
                                            while (isFindBookId){
                                                if (Thread.currentThread().isInterrupted()) {
                                                    return;
                                                }
                                                String bookId = bookIdMatcher.group(1);
                                                BookCrawlSourceAssembler.parseBookAndSave(id, bookCrawlRuleDTO, bookCrawlSourceId, bookId, new AbstractBookCrawlSourceHandle() {
                                                    @Override
                                                    public boolean parseBookAndSave(int id, BookCrawlRuleDTO bookCrawlRuleDTO, Long bookCrawlSourceId, String bookId) {
                                                        final AtomicBoolean parseResult = new AtomicBoolean(false);
                                                        BookCrawlSourceAssembler.parseBook(bookCrawlRuleDTO, bookId,globalController ,new AbstractBookCrawlSourceHandle() {
                                                            @Override
                                                            public void handleBook(BookFictionHeader bookFictionHeader) {
                                                                if (bookFictionHeader.getFictionName() == null || bookFictionHeader.getFictionAuthor() == null) {
                                                                    return;
                                                                }
                                                                System.out.printf("小说名称 %s 作者 %s%n",bookFictionHeader.getFictionName(),bookFictionHeader.getFictionAuthor());
                                                                BookFictionHeader bookHeader = bookFictionHeaderService.findByFictionNameAndFictionAuthor(bookFictionHeader.getFictionName(),bookFictionHeader.getFictionAuthor());
                                                                if(ObjectUtils.isEmpty(bookHeader)){
                                                                    bookFictionHeader.setFictionType(FictionTypeEnum.getFictionTypeName(id));
                                                                    bookFictionHeader.setId(IDUtils.nextId(MessageConstant.BOOK_FICTION_HEADER));
                                                                    BookCrawlSourceAssembler.parseBookIndexAndContent(bookId,bookFictionHeader,bookCrawlRuleDTO,new HashMap<>(0),new AbstractBookCrawlSourceHandle(){
                                                                        @Override
                                                                        public void handleFiction(BookFictionHeader bookHeader, List<BookFictionLine> bookFictionLines, List<BookFictionContent> bookFictionContents) {
                                                                            log.info("[书籍爬取服务v2] 新增书籍 {} 作者 {} 总章数 {}",bookFictionHeader.getFictionName(),bookFictionHeader.getFictionAuthor(),bookFictionLines.size());
                                                                            bookFictionDomainService.createBookFiction(bookHeader,bookFictionLines,bookFictionContents);
                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        });
                                                        return parseResult.get();
                                                    }
                                                });
                                                isFindBookId = bookIdMatcher.find();
                                            }
                                            Pattern totalPagePatten = Pattern.compile(bookCrawlRuleDTO.getTotalPagePatten());
                                            Matcher totalPageMatcher = totalPagePatten.matcher(bookListHtml);
                                            boolean isFindTotalPage = totalPageMatcher.find();
                                            if (isFindTotalPage) {
                                                totalPage = Integer.parseInt(totalPageMatcher.group(1));
                                            }
                                        }
                                    }
                                    page++;
                                }
                            }
                        }));
                        thread.start();
                        threadIds.add(thread.getId());
                    }
                    redisTemplate.opsForValue().set(MessageConstant.THREAD_ID + bookCrawlSource.getId(),JSON.toJSONString(threadIds));
                }
            }
        }
        return true;
    }
}
