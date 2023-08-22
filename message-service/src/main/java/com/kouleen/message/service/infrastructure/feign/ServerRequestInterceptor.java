package com.kouleen.message.service.infrastructure.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kouleen.message.api.interfaces.vo.ResponseVO;
import com.kouleen.message.api.interfaces.vo.SystemAuthorizationVO;
import com.kouleen.message.service.infrastructure.utils.MD5Util;
import com.kouleen.message.service.infrastructure.utils.ResultUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author zhangqing
 * @since 2023/8/10 15:01
 */
@RefreshScope
@Configuration
public class ServerRequestInterceptor implements RequestInterceptor {

    private final static String MESSAGE_SERVER_TOKEN = "message-server::token";

    @Value("${auth.username}")
    private String username;

    @Value("${auth.password}")
    private String password;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(Boolean.TRUE.equals(redisTemplate.hasKey(MESSAGE_SERVER_TOKEN))){
            requestTemplate.header("token", redisTemplate.opsForValue().get(MESSAGE_SERVER_TOKEN));
            return;
        }
        String password = this.password + this.username;
        String responseStr = restTemplate.postForObject("https://minecraft.kouleen.cn/v2/system/auth/login?username=" + username + "&password=" + MD5Util.getMD5(password), null, String.class);
        ResponseVO<SystemAuthorizationVO> authorizationVOResponseVO = JSON.parseObject(responseStr,new TypeReference<ResponseVO<SystemAuthorizationVO>>(){});
        SystemAuthorizationVO systemAuthorizationVO = ResultUtils.response(authorizationVOResponseVO);
        redisTemplate.opsForValue().set(MESSAGE_SERVER_TOKEN,systemAuthorizationVO.getAccess_token(), Duration.ofSeconds(systemAuthorizationVO.getExpires_in()));
        requestTemplate.header("token", systemAuthorizationVO.getAccess_token());
    }
}
