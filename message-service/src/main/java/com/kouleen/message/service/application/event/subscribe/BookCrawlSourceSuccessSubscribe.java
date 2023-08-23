package com.kouleen.message.service.application.event.subscribe;

import com.kouleen.message.api.interfaces.dto.BookCrawlSourceDTO;
import com.kouleen.message.api.interfaces.dto.MessageDTO;
import com.kouleen.message.service.domain.book.BookCrawlSourceDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


/**
 * @author zhangqing
 * @since 2023/8/22 19:20
 */
@Component
public class BookCrawlSourceSuccessSubscribe {

    @Autowired
    private BookCrawlSourceDomainService bookCrawlSourceDomainService;

    @Bean
    public Consumer<MessageDTO<BookCrawlSourceDTO>> bookCrawlSource() {
        return messageDTO -> bookCrawlSourceDomainService.modifyBookCrawlSource(messageDTO.getPayload());
    }
}
