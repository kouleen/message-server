package com.kouleen.message.service.application.service.impl;

import com.kouleen.message.service.application.service.BookFictionHeaderService;
import com.kouleen.message.service.domain.book.entity.BookFictionHeader;
import com.kouleen.message.service.domain.book.face.repository.BookFictionHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangqing
 * @since 2023/7/16 17:37
 */
@Service
public class BookFictionHeaderServiceImpl implements BookFictionHeaderService {

    @Autowired
    private BookFictionHeaderRepository bookFictionHeaderRepository;

    @Override
    public BookFictionHeader findByFictionNameAndFictionAuthor(String fictionName, String fictionAuthor) {
        return bookFictionHeaderRepository.findByFictionNameAndFictionAuthorAndIsDelete(fictionName,fictionAuthor,0);
    }
}
