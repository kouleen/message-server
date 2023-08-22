package com.kouleen.message.service.infrastructure.feign.fallback;

import com.kouleen.message.api.interfaces.vo.ResponseBuilder;
import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.api.interfaces.vo.SystemConfigVO;
import com.kouleen.message.service.infrastructure.feign.base.SystemConfigFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangqing
 * @since 2023/7/27 14:01
 */
@Component
public class SystemConfigFeignClientFallbackFactory implements SystemConfigFeignClient {

    @Override
    public ResponseVO<List<SystemConfigVO>> convertSystemConfig(String dictHeaderCode) {
        return ResponseBuilder.exception();
    }
}
