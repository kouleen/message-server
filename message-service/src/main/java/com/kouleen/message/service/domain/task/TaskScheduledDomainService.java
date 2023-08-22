package com.kouleen.message.service.domain.task;

import com.kouleen.message.api.interfaces.dto.TaskScheduledDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author zhangqing
 * @since 2023/8/10 14:06
 */
@Slf4j
@Service
public class TaskScheduledDomainService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public void stopScheduledTask(Long id, Map<Long, TaskScheduledDTO> taskScheduledMap, Map<Long, Future<?>> futureMap) {
        log.info("停止定时任务 {}",id);
        taskScheduledMap.remove(id);
        Future<?> future = futureMap.get(id);
        if(!ObjectUtils.isEmpty(future)){
            future.cancel(false);
            futureMap.remove(id);
        }
    }

    public void startScheduledTask(TaskScheduledDTO taskScheduled, Map<Long, TaskScheduledDTO> taskScheduledMap, Map<Long, Future<?>> futureMap) {
        log.info("启动定时任务 {}",taskScheduled.getId());
        Trigger trigger = new CronTrigger(taskScheduled.getCronExpression());
        Future<?> future = threadPoolTaskScheduler.schedule(() -> {
            String taskCommand = taskScheduled.getTaskCommand();
            String[] commands = taskCommand.split(" ");
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
        }, trigger);
        futureMap.put(taskScheduled.getId(),future);
        taskScheduledMap.put(taskScheduled.getId(),taskScheduled);
    }
}
