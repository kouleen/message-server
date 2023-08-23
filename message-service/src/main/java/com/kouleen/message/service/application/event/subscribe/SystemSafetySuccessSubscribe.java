package com.kouleen.message.service.application.event.subscribe;

import com.kouleen.message.api.interfaces.dto.MessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author zhangqing
 * @since 2023/8/23 12:14
 */
@Component
public class SystemSafetySuccessSubscribe {

    @Bean
    public Consumer<MessageDTO<Integer>> systemSafety(){
       return messageDTO -> System.exit(messageDTO.getPayload());
    }
}
