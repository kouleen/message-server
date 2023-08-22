package com.kouleen.message.api.interfaces.vo;


import com.kouleen.message.api.infrastructure.constants.MessageConstant;
import com.kouleen.message.api.infrastructure.constants.ResultCodeEnum;
import org.slf4j.MDC;

/**
 * @author zhangqing
 * @since 2023/4/9 9:41
 */
public class ResponseBuilder {

    private ResponseBuilder() {}

    public static <T> ResponseVO<T> success() {
        return of(ResultCodeEnum.SUCCESS, null);
    }

    public static <T> ResponseVO<T> success(T data) {
        return of(ResultCodeEnum.SUCCESS, data);
    }

    public static <T> ResponseVO<T> exception() {
        return of(ResultCodeEnum.EXCEPTION, null);
    }

    public static <T> ResponseVO<T> exception(String message) {
        return of(ResultCodeEnum.EXCEPTION.getCode(),message, null);
    }

    public static <T> ResponseVO<T> exception(T data) {
        return of(ResultCodeEnum.EXCEPTION, data);
    }

    public static <T> ResponseVO<T> exception(String code, String msg, T data) {
        return of(code, msg, data);
    }

    public static <T> ResponseVO<T> exception(ResultCodeEnum exception, T data) {
        return of(exception, data);
    }

    public static <T> ResponseVO<T> exception(String code, String msg) {
        return of(code, msg, null);
    }

    public static <T> ResponseVO<T> exception(ResultCodeEnum exception) {
        return of(exception, null);
    }

    private static <T> ResponseVO<T> of(ResultCodeEnum resultEnum, T data) {
        return of(resultEnum.getCode(), resultEnum.getDesc(), data);
    }

    public static <T> ResponseVO<T> of(String code, String msg, T data) {
        return ResponseVO
                .<T>newBuilder()
                .sign(String.valueOf(System.currentTimeMillis()))
                .code(code)
                .message(msg)
                .data(data)
                .traceId(MDC.get(MessageConstant.TRACE_ID))
                .build();
    }
}
