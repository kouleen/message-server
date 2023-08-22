package com.kouleen.message.service.interfaces.handle;

import com.kouleen.message.api.interfaces.dto.BookCrawlRuleDTO;
import com.kouleen.message.api.interfaces.vo.BookCrawlSourceVO;
import com.kouleen.message.service.domain.book.entity.BookFictionContent;
import com.kouleen.message.service.domain.book.entity.BookFictionHeader;
import com.kouleen.message.service.domain.book.entity.BookFictionLine;

import java.util.List;

/**
 * @author zhangqing
 * @since 2023/8/3 19:07
 */
@FunctionalInterface
public interface BookCrawlSourceHandleInterface {

    void handle(BookCrawlSourceVO bookCrawlSourceVO);

    default void parseBookList(int id, BookCrawlRuleDTO bookCrawlRuleDTO, Long bookCrawlSourceId){
    }

    default boolean parseBookAndSave(int id, BookCrawlRuleDTO bookCrawlRuleDTO, Long bookCrawlSourceId, String bookId){
        return false;
    }

    default void handleBook(BookFictionHeader bookFictionHeader){
    }

    default void handleFiction(BookFictionHeader bookFictionHeader, List<BookFictionLine> bookFictionLines, List<BookFictionContent> bookFictionContents){
    }
}
