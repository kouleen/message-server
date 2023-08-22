package com.kouleen.message.service.domain.book.face.repository;

import com.kouleen.message.service.domain.book.entity.BookCrawlSource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangqing
 * @since 2023/8/3 18:32
 */
@Repository
public interface BookCrawlSourceRepository extends MongoRepository<BookCrawlSource,Long> {
}
