package com.kouleen.message.api.interfaces.vo;

import com.kouleen.message.api.infrastructure.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhangqing
 * @since 2023/4/9 9:40
 */
@ApiModel("通用唯一返回体")
public class ResponseVO<T> implements Serializable {

    @ApiModelProperty(value = "标志",example = "dsadasdasda132dasdada1a")
    private String sign;

    @ApiModelProperty(value = "响应码",example = "200")
    private String code;

    @ApiModelProperty(value = "响应消息",example = "请求成功!")
    private String message;

    @ApiModelProperty(value = "响应体",example = "{a:b}")
    private T data;

    @ApiModelProperty(value = "返回唯一跟踪ID",example = "46546546464")
    private String traceId;

    public ResponseVO() {
    }

    public ResponseVO(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(String code, String message, T data, String sign) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.sign = sign;
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder<>();
    }

    private ResponseVO(Builder<T> builder) {
        this.sign = builder.sign;
        this.code = builder.code;
        this.message = builder.message;
        this.traceId = builder.traceId;
        this.data = builder.data;
    }

    public static class Builder<T> {

        @ApiModelProperty("标志")
        private String sign;

        @ApiModelProperty("响应码")
        private String code;

        @ApiModelProperty("响应消息")
        private String message;

        @ApiModelProperty(value = "返回唯一跟踪ID")
        private String traceId;

        @ApiModelProperty("响应体")
        private T data;

        private Builder() {
        }

        public Builder<T> sign(String sign) {
            this.sign = sign;
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }
        public ResponseVO<T> build() {
            return new ResponseVO<>(this);
        }
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public boolean checkSuccess(){
        return this.code.equals(ResultCodeEnum.SUCCESS.getCode());
    }
}
