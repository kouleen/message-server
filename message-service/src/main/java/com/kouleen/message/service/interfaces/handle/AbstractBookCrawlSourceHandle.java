package com.kouleen.message.service.interfaces.handle;

import com.kouleen.message.api.interfaces.vo.BookCrawlSourceVO;

/**
 * @author zhangqing
 * @since 2023/8/4 10:45
 */
public abstract class AbstractBookCrawlSourceHandle implements BookCrawlSourceHandleInterface{

    @Override
    public void handle(BookCrawlSourceVO bookCrawlSourceVO) {
    }
}
