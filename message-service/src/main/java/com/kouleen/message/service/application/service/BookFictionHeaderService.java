package com.kouleen.message.service.application.service;

import com.kouleen.message.service.domain.book.entity.BookFictionHeader;

/**
 * @author zhangqing
 * @since 2023/7/16 17:37
 */
public interface BookFictionHeaderService {

    BookFictionHeader findByFictionNameAndFictionAuthor(String fictionName, String fictionAuthor);
}
