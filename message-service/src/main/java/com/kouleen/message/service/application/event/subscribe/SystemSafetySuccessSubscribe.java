package com.kouleen.message.service.application.event.subscribe;

import com.kouleen.message.api.interfaces.dto.MessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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

    @Bean Consumer<MessageDTO<String>> systemCommand(){
        return messageDTO -> {
            String payload = messageDTO.getPayload();
            String[] commands = payload.split(" ");
            String string;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                Process process = Runtime.getRuntime().exec(commands);
                InputStream inputStream = process.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
                while ((string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(string).append("\n");
                }
                process.waitFor();
                System.out.println(stringBuilder);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
