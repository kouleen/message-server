package com.kouleen.message.service.domain.book.face.repository;

import com.kouleen.message.service.domain.book.entity.BookFictionLine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangqing
 * @since 2023/7/16 17:33
 */
@Repository
public interface BookFictionLineRepository extends MongoRepository<BookFictionLine,Long> {
}
