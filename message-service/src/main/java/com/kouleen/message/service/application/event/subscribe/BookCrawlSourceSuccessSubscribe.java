package com.kouleen.message.service.application.event.subscribe;

import com.kouleen.message.api.interfaces.dto.BookCrawlSourceDTO;
import com.kouleen.message.service.application.event.BookCrawlSourceStreamClient;
import com.kouleen.message.service.domain.book.BookCrawlSourceDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author zhangqing
 * @since 2023/8/22 19:20
 */
@Slf4j
@EnableBinding(BookCrawlSourceStreamClient.class)
public class BookCrawlSourceSuccessSubscribe {

    @Autowired
    private BookCrawlSourceDomainService bookCrawlSourceDomainService;

    @StreamListener(BookCrawlSourceStreamClient.BOOK_CRAWL_SOURCE)
    public void receiverBookCrawlSource(BookCrawlSourceDTO bookCrawlSourceDTO) {
        bookCrawlSourceDomainService.modifyBookCrawlSource(bookCrawlSourceDTO);
    }
}
