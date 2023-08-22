package com.kouleen.message.api.interfaces.vo;

import com.kouleen.message.api.infrastructure.core.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangqing
 * @since 2023/8/3 18:37
 */
@ApiModel("爬虫源响应映射")
public class BookCrawlSourceVO extends BaseVO {

    @ApiModelProperty(value = "字典头编码")
    private String dictHeaderCode;

    private String dictHeaderName;

    @ApiModelProperty(value = "字典行编码")
    private String dictLineCode;

    private String dictLineName;

    @ApiModelProperty(value = "爬虫源状态，0：关闭，1：开启")
    private Integer sourceStatus;

    public String getDictHeaderCode() {
        return dictHeaderCode;
    }

    public void setDictHeaderCode(String dictHeaderCode) {
        this.dictHeaderCode = dictHeaderCode;
    }

    public String getDictHeaderName() {
        return dictHeaderName;
    }

    public void setDictHeaderName(String dictHeaderName) {
        this.dictHeaderName = dictHeaderName;
    }

    public String getDictLineCode() {
        return dictLineCode;
    }

    public void setDictLineCode(String dictLineCode) {
        this.dictLineCode = dictLineCode;
    }

    public String getDictLineName() {
        return dictLineName;
    }

    public void setDictLineName(String dictLineName) {
        this.dictLineName = dictLineName;
    }

    public Integer getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(Integer sourceStatus) {
        this.sourceStatus = sourceStatus;
    }
}
