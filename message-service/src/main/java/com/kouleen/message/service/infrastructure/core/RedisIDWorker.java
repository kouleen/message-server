package com.kouleen.message.service.infrastructure.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author zhangqing
 * @since 2023/8/21 10:30
 */
@Component
public class RedisIDWorker {

    private static final long BEGIN_TIMESTAMP = 1672531200L;

    private static final int COUNT_BITS = 32;

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param keyPrefix 前缀标识
     * @return 唯一ID
     */
    public long nextId(String keyPrefix){
        //1.生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timeStamp = nowSecond-BEGIN_TIMESTAMP;
        //2.生成序列号
        //2.1 获取当前日期，精确到天
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        //2.2 自增长/计数器  该key的设计不仅可以解决redis自增数上限的问题，还便于统计某一天/月/年的订单的数量
        Long count = stringRedisTemplate.opsForValue().increment(applicationName + "::ID::" + keyPrefix + "::" + date);
        //3.拼接并返回
        return timeStamp << COUNT_BITS | (ObjectUtils.isEmpty(count) ? 0L : count);
    }


    public static void main(String[] args) {
        //用于生成2023-01-01 00:00:00的时间戳
        LocalDateTime time = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
        //获取当前时间的秒数
        long second = time.toEpochSecond(ZoneOffset.UTC);
        System.out.println("second="+second);
    }
}
