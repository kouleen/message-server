package com.kouleen.message.service.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangqing
 * @since 2023/6/2 17:23
 */
@Configuration
public class ServerAutoConfiguration {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
