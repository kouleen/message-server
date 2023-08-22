package com.kouleen.message.service.application.service.impl;

import com.kouleen.message.service.application.service.BookCrawlSourceService;
import com.kouleen.message.service.domain.book.entity.BookCrawlSource;
import com.kouleen.message.service.domain.book.face.repository.BookCrawlSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zhangqing
 * @since 2023/8/3 18:34
 */
@Service
public class BookCrawlSourceServiceImpl implements BookCrawlSourceService {

    @Autowired
    private BookCrawlSourceRepository bookCrawlSourceRepository;

    @Override
    public BookCrawlSource getBookCrawlSource(Long id) {
        return bookCrawlSourceRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateBookCrawlSource(BookCrawlSource bookCrawlSource) {
        bookCrawlSource.updateObjectVersionNumber();
        bookCrawlSourceRepository.save(bookCrawlSource);
        return true;
    }
}
