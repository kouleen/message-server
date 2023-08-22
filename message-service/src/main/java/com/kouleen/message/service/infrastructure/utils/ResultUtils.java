package com.kouleen.message.service.infrastructure.utils;

import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.service.infrastructure.core.CommonException;

/**
 * @author zhangqing
 * @since 2023/7/27 14:18
 */
public abstract class ResultUtils<T> {

    public static <T> void checkSuccess(ResponseVO<T> tResponseVO){
        if (!tResponseVO.checkSuccess()) {
            throw new CommonException(tResponseVO.getMessage());
        }
    }

    public static <T> T response(ResponseVO<T> tResponseVO){
        if (!tResponseVO.checkSuccess()) {
            throw new CommonException(tResponseVO.getMessage());
        }
        return tResponseVO.getData();
    }
}
