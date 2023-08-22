package com.kouleen.message.api.infrastructure.constants;

/**
 * @author zhangqing
 * @since 2023/4/9 9:56
 */
public enum ResultCodeEnum {

    SUCCESS("200","请求成功!"),

    RESOURCE_NOT_EXIST("4001001","资源数据不存在"),
    RESOURCE_IS_EXISTS("4001002","资源数据已存在"),
    RESOURCE_UPLOAD_ERROR("4001003","资源上传失败，请联系管理员,QQ群:645375329"),
    RESOURCE_IS_NOT_EXISTS("4001004","资源文件不存在"),



    ILLEGAL_REQUEST("4002001","请求参数异常"),
    LOGIN_ERROR("4002002","验证码已过有效期"),
    USERNAME_REGISTERED_ERROR("4002003","用户名被注册"),
    EMAIL_REGISTERED_ERROR("4002004","邮箱已被其他账号绑定"),
    SERVER_DOES_NOT_EXIST("4002006","该服务器不存在"),
    PLUGIN_IS_EXISTS("4002007","插件已存在"),
    PLUGIN_IS_NOT_EXISTS("4002008","插件不存在"),
    SERVER_EXISTS("4002009","服务器已绑定该插件"),

    OOS_DOWNLOAD_ERROR("4003001","OOS对象存储，文件下载失败"),
    OOS_DELETE_ERROR("4003002","OOS对象存储，文件删除失败"),

    OPERATE_TOO_FAST_EXCEPTION("40004001","你操作太快了,放松一会再试吧!"),
    VERIFICATION_CODE_EXPIRED("40004002","验证码错误"),

    DATA_IS_NOT_EXISTS_EXPIRED("40005002","数据不存在"),
    PHONE_MESSAGE_TYPE_EXPIRED("40005003","手机信息类型异常"),
    DATA_IS_EXISTS_EXPIRED("40005004","数据已存在"),


    UPDATE_OBJECT_ERROR("-1000","版本号错误"),
    EXCEPTION("-9999","系统异常"),
    FAIL("500","请求失败");

    final String code;

    final String desc;

    ResultCodeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
