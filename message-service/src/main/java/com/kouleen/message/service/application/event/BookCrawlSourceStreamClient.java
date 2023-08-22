package com.kouleen.message.service.application.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhangqing
 * @since 2023/8/22 19:20
 */
public interface BookCrawlSourceStreamClient {

    String BOOK_CRAWL_SOURCE = "book-crawl-source";

    @Input(BOOK_CRAWL_SOURCE)
    SubscribableChannel inBookCrawlSource();
}
