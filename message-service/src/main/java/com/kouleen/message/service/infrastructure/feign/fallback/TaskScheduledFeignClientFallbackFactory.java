package com.kouleen.message.service.infrastructure.feign.fallback;

import com.kouleen.message.api.infrastructure.core.IPage;
import com.kouleen.message.api.interfaces.vo.ResponseBuilder;
import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.api.interfaces.vo.TaskScheduledVO;
import com.kouleen.message.service.infrastructure.feign.base.TaskScheduledFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author zhangqing
 * @since 2023/8/10 10:52
 */
@Component
public class TaskScheduledFeignClientFallbackFactory implements TaskScheduledFeignClient {

    @Override
    public ResponseVO<IPage<TaskScheduledVO>> pageTaskScheduled(Integer current, Integer size) {
        return ResponseBuilder.exception();
    }
}
