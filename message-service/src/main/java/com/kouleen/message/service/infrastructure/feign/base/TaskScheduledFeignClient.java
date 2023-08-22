package com.kouleen.message.service.infrastructure.feign.base;

import com.kouleen.message.api.infrastructure.core.IPage;
import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.api.interfaces.vo.TaskScheduledVO;
import com.kouleen.message.service.infrastructure.feign.fallback.TaskScheduledFeignClientFallbackFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author zhangqing
 * @since 2023/8/10 10:52
 */
@FeignClient(value = "minecraft-server", fallback = TaskScheduledFeignClientFallbackFactory.class,
        url = "https://www.kouleen.cn", contextId = "om.kouleen.message.service.infrastructure.feign.base.TaskScheduledFeignClient")
public interface TaskScheduledFeignClient {

    @GetMapping("/v1/task/scheduled/page")
    @ApiOperation(value = "分页查询",notes = "/v1/task/scheduled/page")
    ResponseVO<IPage<TaskScheduledVO>> pageTaskScheduled(@RequestParam(required = false,defaultValue = "1") Integer current, @RequestParam(required = false,defaultValue = "1000") Integer size);
}
