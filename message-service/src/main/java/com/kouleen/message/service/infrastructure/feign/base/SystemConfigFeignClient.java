package com.kouleen.message.service.infrastructure.feign.base;

import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.api.interfaces.vo.SystemConfigVO;
import com.kouleen.message.service.infrastructure.feign.fallback.SystemConfigFeignClientFallbackFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangqing
 * @since 2023/7/27 14:01
 */
@FeignClient(value = "minecraft-server", fallback = SystemConfigFeignClientFallbackFactory.class,
        url = "https://www.kouleen.cn", contextId = "com.kouleen.message.service.infrastructure.feign.base.SystemConfigFeignClient")
public interface SystemConfigFeignClient {

    @GetMapping("/v1/system/config/convert")
    @ApiOperation(value = "字典转换",notes = "/v1/system/config/convert")
    ResponseVO<List<SystemConfigVO>> convertSystemConfig(@RequestParam String dictHeaderCode);
}
