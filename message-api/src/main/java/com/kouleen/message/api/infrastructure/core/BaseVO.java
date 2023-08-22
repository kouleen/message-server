package com.kouleen.message.api.infrastructure.core;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author zhangqing
 * @since 2023/6/2 15:33
 */
@ApiModel("响应映射基类")
public class BaseVO implements Convertor {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键id")
    protected Long id;

    @ApiModelProperty(value = "备注")
    protected String remark;

    @ApiModelProperty(value = "逻辑删除")
    protected Integer isDelete;

    @ApiModelProperty(value = "数据版本号")
    protected Integer objectVersionNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    protected Date creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    protected Date lastUpdateDate;

    @ApiModelProperty(value = "创建人")
    protected Long createdBy;

    @ApiModelProperty(value = "修改人")
    protected Long lastUpdatedBy;

    @ApiModelProperty(value = "创建帐号")
    protected String createdAccount;

    @ApiModelProperty(value = "更新帐号")
    protected String lastUpdatedAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(String createdAccount) {
        this.createdAccount = createdAccount;
    }

    public String getLastUpdatedAccount() {
        return lastUpdatedAccount;
    }

    public void setLastUpdatedAccount(String lastUpdatedAccount) {
        this.lastUpdatedAccount = lastUpdatedAccount;
    }

    public interface Insert{
    }

    public interface Update{
    }

    public interface Query{
    }

    public interface Delete{
    }
}
