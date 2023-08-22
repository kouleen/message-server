package com.kouleen.message.service.interfaces.scheduling;

import com.kouleen.message.api.infrastructure.core.Convertor;
import com.kouleen.message.api.interfaces.dto.TaskScheduledDTO;
import com.kouleen.message.api.interfaces.vo.TaskScheduledVO;
import com.kouleen.message.service.application.resp.RemoteTaskScheduledFeignService;
import com.kouleen.message.service.domain.task.TaskScheduledDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author zhangqing
 * @since 2023/8/10 8:11
 */
@Component
@EnableScheduling
public class ScheduledManager {

    private volatile static Map<Long,TaskScheduledDTO> taskScheduledMap = new HashMap<>();

    private volatile static Map<Long, Future<?>> futureMap = new HashMap<>();

    @Autowired
    private TaskScheduledDomainService taskScheduledDomainService;

    @Autowired
    private RemoteTaskScheduledFeignService remoteTaskScheduledFeignService;

    @Scheduled(fixedRate = 5000)
    public void loadTask(){
        List<TaskScheduledVO> taskScheduledVOList = remoteTaskScheduledFeignService.getTaskScheduledList(1, 1000);
        List<TaskScheduledDTO> taskScheduledList = Convertor.convertList(taskScheduledVOList, TaskScheduledDTO.class);

        for (TaskScheduledDTO taskScheduled : taskScheduledList) {
            Long id = taskScheduled.getId();
            boolean needStart = false;
            if(taskScheduledMap.containsKey(id)){
                if(!taskScheduled.equals(taskScheduledMap.get(id))){
                    needStart = true;
                    taskScheduledDomainService.stopScheduledTask(id, taskScheduledMap, futureMap);
                }
            }else {
                needStart = true;
            }
            if (needStart && taskScheduled.getTaskStatus().equals(1)) {
                taskScheduledDomainService.startScheduledTask(taskScheduled,taskScheduledMap, futureMap);
            }
        }
        Iterator<Map.Entry<Long, TaskScheduledDTO>> entryIterator = taskScheduledMap.entrySet().iterator();
        List<Long> idList = taskScheduledList.stream().map(TaskScheduledDTO::getId).collect(Collectors.toList());
        while (entryIterator.hasNext()){
            Long id = entryIterator.next().getKey();
            if(!idList.contains(id)){
                taskScheduledDomainService.stopScheduledTask(id, taskScheduledMap, futureMap);
            }
        }
    }
}
