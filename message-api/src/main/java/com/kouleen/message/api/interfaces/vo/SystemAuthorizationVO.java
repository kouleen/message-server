package com.kouleen.message.api.interfaces.vo;

import io.swagger.annotations.ApiModel;

/**
 * @author zhangqing
 * @since 2023/7/21 14:45
 */
@ApiModel("鉴权信息实体")
public class SystemAuthorizationVO {

    private final static String TOKEN_TYPE = "token";

    private String access_token;
    private String token_type;
    private Long expires_in;
    private String scope;

    public static SystemAuthorizationVO buildAuthorization(String access_token,Long expires_in){
        SystemAuthorizationVO systemAuthorizationVO = new SystemAuthorizationVO();
        systemAuthorizationVO.setAccess_token(access_token);
        systemAuthorizationVO.setExpires_in(expires_in);
        systemAuthorizationVO.setToken_type(TOKEN_TYPE);
        systemAuthorizationVO.setScope("all");
        return systemAuthorizationVO;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
