package com.kouleen.message.service.domain.book.entity;


import com.kouleen.message.service.infrastructure.core.BaseDomain;

/**
 * @author zhangqing
 * @since 2023/7/16 17:25
 */
public class BookFictionLine extends BaseDomain {

    public static final String COL_HID = "hid";
    public static final String COL_TITLE = "title";
    public static final String COL_SORT = "sort";
    public static final String COL_WORD_COUNT = "word_count";

    private Long hid;

    private String title;

    private Integer sort;

    private Integer wordCount;

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
