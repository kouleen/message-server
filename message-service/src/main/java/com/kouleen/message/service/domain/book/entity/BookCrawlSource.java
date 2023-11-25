package com.kouleen.message.service.domain.book.entity;


import com.kouleen.message.service.infrastructure.core.BaseDomain;

/**
 * @author zhangqing
 * @since 2023/8/3 18:25
 */
public class BookCrawlSource  extends BaseDomain {

    public static final String COL_DICT_HEADER_CODE = "dict_header_code";
    public static final String COL_DICT_LINE_CODE = "dict_line_code";
    public static final String COL_SOURCE_STATUS = "source_status";

    private String dictHeaderCode;

    private String dictLineCode;

    private Integer sourceStatus;

    public String getDictHeaderCode() {
        return dictHeaderCode;
    }

    public void setDictHeaderCode(String dictHeaderCode) {
        this.dictHeaderCode = dictHeaderCode;
    }

    public String getDictLineCode() {
        return dictLineCode;
    }

    public void setDictLineCode(String dictLineCode) {
        this.dictLineCode = dictLineCode;
    }

    public Integer getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(Integer sourceStatus) {
        this.sourceStatus = sourceStatus;
    }
}
