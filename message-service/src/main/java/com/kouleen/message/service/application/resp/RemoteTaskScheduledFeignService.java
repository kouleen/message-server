package com.kouleen.message.service.application.resp;

import com.kouleen.message.api.infrastructure.core.IPage;
import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.api.interfaces.vo.TaskScheduledVO;
import com.kouleen.message.service.infrastructure.feign.base.TaskScheduledFeignClient;
import com.kouleen.message.service.infrastructure.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangqing
 * @since 2023/8/10 14:12
 */
@Service
public class RemoteTaskScheduledFeignService {

    @Resource
    private TaskScheduledFeignClient taskScheduledFeignClient;

    public List<TaskScheduledVO> getTaskScheduledList(Integer current, Integer size) {
        ResponseVO<IPage<TaskScheduledVO>> pageResponseVO = taskScheduledFeignClient.pageTaskScheduled(current, size);
        ResultUtils.checkSuccess(pageResponseVO);
        IPage<TaskScheduledVO> taskScheduledVOIPage = pageResponseVO.getData();
        if(ObjectUtils.isEmpty(taskScheduledVOIPage)){
            return Collections.emptyList();
        }
        return taskScheduledVOIPage.getRecords();
    }
}
