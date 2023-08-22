package com.kouleen.message.service.application.service;

import com.kouleen.message.service.domain.book.entity.BookCrawlSource;

/**
 * @author zhangqing
 * @since 2023/8/3 18:34
 */
public interface BookCrawlSourceService{

    BookCrawlSource getBookCrawlSource(Long id);

    boolean updateBookCrawlSource(BookCrawlSource bookCrawlSource);
}
