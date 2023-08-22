package com.kouleen.message.service.interfaces.assembler;

import com.kouleen.message.api.infrastructure.constants.MessageConstant;
import com.kouleen.message.api.interfaces.dto.BookCrawlRuleDTO;
import com.kouleen.message.api.interfaces.vo.SystemConfigVO;
import com.kouleen.message.service.domain.book.entity.BookCrawlSource;
import com.kouleen.message.service.domain.book.entity.BookFictionContent;
import com.kouleen.message.service.domain.book.entity.BookFictionHeader;
import com.kouleen.message.service.domain.book.entity.BookFictionLine;
import com.kouleen.message.service.infrastructure.utils.HttpUtils;
import com.kouleen.message.service.infrastructure.utils.IDUtils;
import com.kouleen.message.service.infrastructure.utils.PatternUtils;
import com.kouleen.message.service.infrastructure.utils.StringUtil;
import com.kouleen.message.service.interfaces.handle.AbstractBookCrawlSourceHandle;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangqing
 * @since 2023/8/3 19:06
 */
public class BookCrawlSourceAssembler {

    public static void parseBookList(int id, BookCrawlRuleDTO bookCrawlRuleDTO, Long bookCrawlSourceId, AbstractBookCrawlSourceHandle abstractBookCrawlSourceHandle) {
        abstractBookCrawlSourceHandle.parseBookList(id,bookCrawlRuleDTO,bookCrawlSourceId);
    }

    public static boolean parseBookAndSave(int id, BookCrawlRuleDTO bookCrawlRuleDTO, Long bookCrawlSourceId, String bookId,AbstractBookCrawlSourceHandle abstractBookCrawlSourceHandle) {
        return abstractBookCrawlSourceHandle.parseBookAndSave(id,bookCrawlRuleDTO,bookCrawlSourceId,bookId);
    }

    public static void parseBook(BookCrawlRuleDTO bookCrawlRuleDTO, String bookId, SystemConfigVO globalController, AbstractBookCrawlSourceHandle abstractBookCrawlSourceHandle) {
        BookFictionHeader bookFictionHeader = new BookFictionHeader();
        String bookDetailUrl = bookCrawlRuleDTO.getBookDetailUrl();
        bookDetailUrl = bookDetailUrl.replace("{bookId}", bookId);
        String bookDetailHtml = HttpUtils.getByHttpClientWithChrome(bookDetailUrl);
        if(StringUtils.hasText(bookDetailHtml)){
            Pattern bookNamePatten = PatternUtils.getPattern(bookCrawlRuleDTO.getBookNamePatten());
            Matcher bookNameMatch = bookNamePatten.matcher(bookDetailHtml);
            boolean isFindBookName = bookNameMatch.find();
            if (isFindBookName) {
                if (!StringUtils.hasText(bookCrawlRuleDTO.getStatusPatten())) {
                    return;
                }
                Pattern bookStatusPattens = PatternUtils.getPattern(bookCrawlRuleDTO.getStatusPatten());
                Matcher bookStatusMatchs = bookStatusPattens.matcher(bookDetailHtml);
                if (!bookStatusMatchs.find()) {
                    return;
                }
                String bookStatusa = bookStatusMatchs.group(1);
                Byte aBytes = bookCrawlRuleDTO.getBookStatusRule().get(bookStatusa);
                if(globalController.getDictLineName().equals("2")){
                    // 只爬取完本
                    if (aBytes == null || aBytes == 0) {
                        return;
                    }
                }
                String bookName = bookNameMatch.group(1);
                bookFictionHeader.setFictionName(bookName);
                Pattern authorNamePatten = PatternUtils.getPattern(bookCrawlRuleDTO.getAuthorNamePatten());
                Matcher authorNameMatch = authorNamePatten.matcher(bookDetailHtml);
                boolean isFindAuthorName = authorNameMatch.find();
                if (isFindAuthorName) {
                    String authorName = authorNameMatch.group(1);
                    bookFictionHeader.setFictionAuthor(authorName);
                    if(StringUtils.hasText(bookCrawlRuleDTO.getPicUrlPatten())){
                        Pattern picUrlPatten = PatternUtils.getPattern(bookCrawlRuleDTO.getPicUrlPatten());
                        Matcher picUrlMatch = picUrlPatten.matcher(bookDetailHtml);
                        boolean isFindPicUrl = picUrlMatch.find();
                        if (isFindPicUrl) {
                            String picUrl = picUrlMatch.group(1);
                            if (StringUtils.hasText(picUrl) && StringUtils.hasText(bookCrawlRuleDTO.getPicUrlPrefix())) {
                                picUrl = bookCrawlRuleDTO.getPicUrlPrefix() + picUrl;
                            }
                            //设置封面图片路径
                            bookFictionHeader.setImageUrl(picUrl);
                        }
                    }
                    if (StringUtils.hasText(bookCrawlRuleDTO.getScorePatten())) {
                        Pattern scorePatten = PatternUtils.getPattern(bookCrawlRuleDTO.getScorePatten());
                        Matcher scoreMatch = scorePatten.matcher(bookDetailHtml);
                        boolean isFindScore = scoreMatch.find();
                        if (isFindScore) {
                            String score = scoreMatch.group(1);
                            //设置评分
                            bookFictionHeader.setScore(Float.parseFloat(score));
                        }
                    }
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(bookCrawlRuleDTO.getVisitCountPatten())) {
                        Pattern visitCountPatten = PatternUtils.getPattern(bookCrawlRuleDTO.getVisitCountPatten());
                        Matcher visitCountMatch = visitCountPatten.matcher(bookDetailHtml);
                        boolean isFindVisitCount = visitCountMatch.find();
                        if (isFindVisitCount) {
                            String visitCount = visitCountMatch.group(1);
                            //设置访问次数
                            bookFictionHeader.setViews(Integer.parseInt(visitCount));
                        }
                    }

                    String desc = bookDetailHtml.substring(bookDetailHtml.indexOf(bookCrawlRuleDTO.getDescStart()) + bookCrawlRuleDTO.getDescStart().length());
                    desc = desc.substring(0, desc.indexOf(bookCrawlRuleDTO.getDescEnd()));
                    //过滤掉简介中的特殊标签
                    desc = desc.replaceAll("<a[^<]+</a>", "")
                            .replaceAll("<font[^<]+</font>", "")
                            .replaceAll("<p>\\s*</p>", "")
                            .replaceAll("<p>", "")
                            .replaceAll("</p>", "<br/>");
                    bookFictionHeader.setFictionIntroduce(desc);
                    if (StringUtils.hasText(bookCrawlRuleDTO.getStatusPatten())) {
                        Pattern bookStatusPatten = PatternUtils.getPattern(bookCrawlRuleDTO.getStatusPatten());
                        Matcher bookStatusMatch = bookStatusPatten.matcher(bookDetailHtml);
                        boolean isFindBookStatus = bookStatusMatch.find();
                        if (isFindBookStatus) {
                            String bookStatus = bookStatusMatch.group(1);
                            Byte aByte = bookCrawlRuleDTO.getBookStatusRule().get(bookStatus);
                            if (aByte != null) {
                                //设置更新状态
                                bookFictionHeader.setFictionState(aByte == 0 ? "连载中" : "已完结");
                            }
                        }
                    }

                    if (StringUtils.hasText(bookCrawlRuleDTO.getUpadateTimePatten()) && StringUtils.hasText(bookCrawlRuleDTO.getUpadateTimeFormatPatten())) {
                        Pattern updateTimePatten = PatternUtils.getPattern(bookCrawlRuleDTO.getUpadateTimePatten());
                        Matcher updateTimeMatch = updateTimePatten.matcher(bookDetailHtml);
                        boolean isFindUpdateTime = updateTimeMatch.find();
                        if (isFindUpdateTime) {
                            String updateTime = updateTimeMatch.group(1);
                            //设置更新时间
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(bookCrawlRuleDTO.getUpadateTimeFormatPatten());
                            try {
                                Date lastUpdateDate = simpleDateFormat.parse(updateTime);
                                bookFictionHeader.setLastUpdateDate(lastUpdateDate);
                            }catch (Exception exception){
                                bookFictionHeader.setLastUpdateDate(new Date());
                            }
                        }
                    }
                }
            }
        }
        abstractBookCrawlSourceHandle.handleBook(bookFictionHeader);
    }

    public static void parseBookIndexAndContent(String sourceBookId, BookFictionHeader bookFictionHeader, BookCrawlRuleDTO bookCrawlRuleDTO, Map<Integer, BookFictionLine> existBookFictionLineMap, AbstractBookCrawlSourceHandle abstractBookCrawlSourceHandle) {
        Date currentDate = new Date();
        List<BookFictionLine> bookFictionLines = new ArrayList<>();
        List<BookFictionContent> bookFictionContents = new ArrayList<>();
        //读取目录
        String indexListUrl = bookCrawlRuleDTO.getBookIndexUrl().replace("{bookId}", sourceBookId);
        String indexListHtml = HttpUtils.getByHttpClientWithChrome(indexListUrl);
        if(StringUtils.hasText(indexListHtml)){
            if(StringUtils.hasText(bookCrawlRuleDTO.getBookIndexStart())){
                indexListHtml = indexListHtml.substring(indexListHtml.indexOf(bookCrawlRuleDTO.getBookIndexStart()) + bookCrawlRuleDTO.getBookIndexStart().length());
            }
            Pattern indexIdPatten = PatternUtils.getPattern(bookCrawlRuleDTO.getIndexIdPatten());
            Matcher indexIdMatch = indexIdPatten.matcher(indexListHtml);

            Pattern indexNamePatten = PatternUtils.getPattern(bookCrawlRuleDTO.getIndexNamePatten());
            Matcher indexNameMatch = indexNamePatten.matcher(indexListHtml);

            boolean isFindIndex = indexIdMatch.find() & indexNameMatch.find();

            int indexNum = 0;
            //总字数
            int totalWordCount = bookFictionHeader.getWordCount() == null ? 0 : bookFictionHeader.getWordCount();

            while (isFindIndex) {
                BookFictionLine hasIndex = existBookFictionLineMap.get(indexNum);
                String indexName = indexNameMatch.group(1);
                if (hasIndex == null || !org.apache.commons.lang3.StringUtils.deleteWhitespace(hasIndex.getTitle()).equals(org.apache.commons.lang3.StringUtils.deleteWhitespace(indexName))) {
                    String sourceIndexId = indexIdMatch.group(1);
                    String bookContentUrl = bookCrawlRuleDTO.getBookContentUrl();
                    int calStart = bookContentUrl.indexOf("{cal_");
                    if (calStart != -1) {
                        //内容页URL需要进行计算才能得到
                        String calStr = bookContentUrl.substring(calStart, calStart + bookContentUrl.substring(calStart).indexOf("}"));
                        String[] calArr = calStr.split("_");
                        int calType = Integer.parseInt(calArr[1]);
                        if (calType == 1) {
                            ///{cal_1_1_3}_{bookId}/{indexId}.html
                            //第一种计算规则，去除第x个参数的最后y个字母
                            int x = Integer.parseInt(calArr[2]);
                            int y = Integer.parseInt(calArr[3]);
                            String calResult;
                            if (x == 1) {
                                calResult = sourceBookId.substring(0, sourceBookId.length() - y);
                            } else {
                                calResult = sourceIndexId.substring(0, sourceBookId.length() - y);
                            }
                            if (calResult.length() == 0) {
                                calResult = "0";

                            }
                            bookContentUrl = bookContentUrl.replace(calStr + "}", calResult);
                        }
                    }
                    String contentUrl = bookContentUrl.replace("{bookId}", sourceBookId).replace("{indexId}", sourceIndexId);
                    //查询章节内容
                    String contentHtml = HttpUtils.getByHttpClientWithChrome(contentUrl);
                    if(StringUtils.hasText(contentHtml) && !contentHtml.contains("正在手打中")){
                        String content = contentHtml.substring(contentHtml.indexOf(bookCrawlRuleDTO.getContentStart()) + bookCrawlRuleDTO.getContentStart().length());
                        content = content.substring(0, content.indexOf(bookCrawlRuleDTO.getContentEnd()));
                        //插入章节目录和章节内容
                        BookFictionLine bookFictionLine = new BookFictionLine();
                        bookFictionLine.setTitle(indexName);
                        bookFictionLine.setSort(indexNum);
                        bookFictionLine.setHid(bookFictionHeader.getId());
                        bookFictionLines.add(bookFictionLine);
                        int wordCount = StringUtil.getStrValidWordCount(content);
                        BookFictionContent bookFictionContent = new BookFictionContent();
                        bookFictionContent.setId(IDUtils.nextId(MessageConstant.BOOK_FICTION_CONTENT));
                        bookFictionContent.setContent(content);
                        bookFictionContents.add(bookFictionContent);
                        if (hasIndex != null) {
                            //章节更新
                            bookFictionLine.setId(hasIndex.getId());
                            bookFictionContent.setLid(hasIndex.getId());

                            //计算总字数
                            totalWordCount = (totalWordCount + wordCount - hasIndex.getWordCount());
                        } else {
                            //章节插入
                            //设置目录和章节内容
                            Long bookFictionLineId = IDUtils.nextId(MessageConstant.BOOK_FICTION_LINE);
                            bookFictionLine.setId(bookFictionLineId);
                            bookFictionLine.setHid(bookFictionHeader.getId());
                            bookFictionContent.setLid(bookFictionLineId);
                            //计算总字数
                            totalWordCount += wordCount;
                        }
                    }
                }
                indexNum++;
                isFindIndex = indexIdMatch.find() & indexNameMatch.find();
            }
            if (bookFictionLines.size() > 0) {
                //如果有爬到最新章节，则设置小说主表的最新章节信息
                //获取爬取到的最新章节
                BookFictionLine lastIndex = bookFictionLines.get(bookFictionLines.size() - 1);
                bookFictionHeader.setNewest(lastIndex.getTitle());
                bookFictionHeader.setLastUpdateDate(currentDate);
            }
            bookFictionHeader.setWordCount(totalWordCount);
            if(bookFictionLines.size() == bookFictionContents.size() && bookFictionLines.size() > 0){
                abstractBookCrawlSourceHandle.handleFiction(bookFictionHeader,bookFictionLines,bookFictionContents);
            }
        }
    }
}
