package com.kouleen.message.service.infrastructure.utils;

import com.kouleen.message.service.infrastructure.core.RedisIDWorker;

/**
 * @author zhangqing
 * @since 2023/8/21 10:38
 */
public class IDUtils {

    public static Long nextId(String keyPrefix){
        RedisIDWorker redisIDWorker = SpringContextUtil.getBean(RedisIDWorker.class);
        return redisIDWorker.nextId(keyPrefix);
    }
}
