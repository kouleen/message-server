package com.kouleen.message.service.application.resp;

import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.api.interfaces.vo.SystemConfigVO;
import com.kouleen.message.service.infrastructure.feign.base.SystemConfigFeignClient;
import com.kouleen.message.service.infrastructure.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangqing
 * @since 2023/7/27 14:12
 */
@Service
public class RemoteSystemConfigFeignService {

    @Resource
    private SystemConfigFeignClient systemConfigFeignClient;

    public List<SystemConfigVO> convertSystemConfig(String dictHeaderCode){
        ResponseVO<List<SystemConfigVO>> systemConfigResult = systemConfigFeignClient.convertSystemConfig(dictHeaderCode);
        return ResultUtils.response(systemConfigResult);
    }

    public Map<String,String> convertMapSystemConfig(String dictHeaderCode){
        ResponseVO<List<SystemConfigVO>> systemConfigResult = systemConfigFeignClient.convertSystemConfig(dictHeaderCode);
        ResultUtils.checkSuccess(systemConfigResult);
        List<SystemConfigVO> systemConfigVOS = systemConfigResult.getData();
        return systemConfigVOS.stream().collect(Collectors.toMap(SystemConfigVO::getDictLineCode, SystemConfigVO::getDictLineName));
    }

    public Map<String, SystemConfigVO> convertItemMapSystemConfig(String dictHeaderCode) {
        ResponseVO<List<SystemConfigVO>> systemConfigResult = systemConfigFeignClient.convertSystemConfig(dictHeaderCode);
        ResultUtils.checkSuccess(systemConfigResult);
        List<SystemConfigVO> systemConfigVOS = systemConfigResult.getData();
        return systemConfigVOS.stream().collect(Collectors.toMap(SystemConfigVO::getDictLineCode, Function.identity()));
    }
}
