package com.kouleen.message.api.interfaces.vo;

import com.kouleen.message.api.infrastructure.core.BaseVO;

/**
 * @author zhangqing
 * @since 2023/11/25 12:31
 */
public class TaskIdentityVO extends BaseVO {

    private String code;
    private String message;
    private String user;
    private String userid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
