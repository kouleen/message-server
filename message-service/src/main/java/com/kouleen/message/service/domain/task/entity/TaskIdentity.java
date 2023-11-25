package com.kouleen.message.service.domain.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kouleen.message.service.infrastructure.core.BaseDomain;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangqing
 * @since 2023/11/25 12:31
 */
@TableName(value = "task_identity")
public class TaskIdentity extends BaseDomain {

    public static final String COL_USER_CODE = "user_code";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_USER_GENDER = "user_gender";
    public static final String COL_USER_PHONE = "user_phone";

    @ApiModelProperty("居民编码")
    @TableField(value = "`user_code`")
    private String userCode;

    @ApiModelProperty("居民名称")
    @TableField(value = "`user_name`")
    private String userName;

    @ApiModelProperty("居民性别 1:男 0:女")
    @TableField(value = "`user_gender`")
    private Integer userGender;

    @ApiModelProperty("电话号码")
    @TableField(value = "`user_phone`")
    private String userPhone;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
