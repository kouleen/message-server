package com.kouleen.message.api.interfaces.vo;

import com.kouleen.message.api.infrastructure.core.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangqing
 * @since 2023/7/12 16:20
 */
@ApiModel("系统配置响应映射")
public class SystemConfigVO extends BaseVO {

    @ApiModelProperty(value = "字典头编码")
    private String dictHeaderCode;

    @ApiModelProperty(value = "字典头值名称")
    private String dictHeaderName;

    @ApiModelProperty(value = "字典行编码")
    private String dictLineCode;

    @ApiModelProperty(value = "字典行名称")
    private String dictLineName;

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
}
