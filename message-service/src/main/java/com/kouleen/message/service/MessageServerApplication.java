package com.kouleen.message.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;


/**
 * @author zhangqing
 * @since 2023/8/9 12:00
 */
@SpringBootApplication
@EnableConfigurationProperties(AutoServiceRegistrationProperties.class)
public class MessageServerApplication{

    public static void main(String[] args) {
        SpringApplication.run(MessageServerApplication.class,args);
    }
}
