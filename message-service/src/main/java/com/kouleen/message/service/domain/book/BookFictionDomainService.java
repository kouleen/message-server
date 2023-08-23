package com.kouleen.message.service.domain.book;

import com.kouleen.message.service.domain.book.entity.BookFictionContent;
import com.kouleen.message.service.domain.book.entity.BookFictionHeader;
import com.kouleen.message.service.domain.book.entity.BookFictionLine;
import com.kouleen.message.service.domain.book.face.repository.BookFictionContentRepository;
import com.kouleen.message.service.domain.book.face.repository.BookFictionHeaderRepository;
import com.kouleen.message.service.domain.book.face.repository.BookFictionLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangqing
 * @since 2023/7/16 17:19
 */
@Slf4j
@Service
public class BookFictionDomainService {

    @Autowired
    private BookFictionLineRepository bookFictionLineRepository;

    @Autowired
    private BookFictionHeaderRepository bookFictionHeaderRepository;

    @Autowired
    private BookFictionContentRepository bookFictionContentRepository;

    public boolean createBookFiction(BookFictionHeader bookHeader, List<BookFictionLine> bookFictionLines, List<BookFictionContent> bookFictionContents) {
        if (!bookFictionHeaderRepository.existsByFictionNameAndFictionAuthorAndIsDelete(bookHeader.getFictionName(),bookHeader.getFictionAuthor(),0)) {
            if (bookFictionLines.size() > 0) {
                bookHeader.init();
                bookFictionHeaderRepository.save(bookHeader);
                bookFictionLines.forEach(BookFictionLine::init);
                bookFictionLineRepository.saveAll(bookFictionLines);
                bookFictionContents.forEach(BookFictionContent::init);
                bookFictionContentRepository.saveAll(bookFictionContents);
            }
        }
        return true;
    }
}
