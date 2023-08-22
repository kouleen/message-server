package com.kouleen.message.service.domain.book.entity;


import com.kouleen.message.service.infrastructure.core.BaseDomain;

/**
 * @author zhangqing
 * @since 2023/7/16 17:26
 */
public class BookFictionContent extends BaseDomain {

    public static final String COL_LID = "lid";
    public static final String COL_CONTENT = "content";

    private Long lid;

    private String content;

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
