package com.kouleen.message.service.domain.book.face.repository;

import com.kouleen.message.service.domain.book.entity.BookFictionHeader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangqing
 * @since 2023/7/16 17:31
 */
@Repository
public interface BookFictionHeaderRepository extends MongoRepository<BookFictionHeader,Long> {

    BookFictionHeader findByFictionNameAndFictionAuthorAndIsDelete(String fictionName, String fictionAuthor,Integer isDelete);

    boolean existsByFictionNameAndFictionAuthorAndIsDelete(String fictionName, String fictionAuthor,Integer isDelete);
}
