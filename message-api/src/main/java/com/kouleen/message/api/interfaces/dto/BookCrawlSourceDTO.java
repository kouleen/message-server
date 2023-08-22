package com.kouleen.message.api.interfaces.dto;

import com.kouleen.message.api.infrastructure.core.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author zhangqing
 * @since 2023/8/3 18:39
 */
@ApiModel("爬虫源请求映射")
public class BookCrawlSourceDTO extends BaseDTO {

    @ApiModelProperty(value = "字典头编码")
    private String dictHeaderCode;

    @ApiModelProperty(value = "字典行编码")
    private String dictLineCode;

    @ApiModelProperty(value = "爬虫源状态，0：关闭，1：开启")
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
