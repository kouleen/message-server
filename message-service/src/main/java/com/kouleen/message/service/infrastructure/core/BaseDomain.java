package com.kouleen.message.service.infrastructure.core;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kouleen.message.api.infrastructure.constants.MessageConstant;
import com.kouleen.message.api.infrastructure.core.Convertor;
import com.kouleen.message.service.infrastructure.utils.IDUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangqing
 * @since 2023/7/16 17:21
 */
public abstract class BaseDomain implements Convertor, Serializable {

    public static final String COL_ID = "id";
    public static final String COL_REMARK = "remark";
    public static final String COL_IS_DELETE = "is_delete";
    public static final String COL_OBJECT_VERSION_NUMBER = "object_version_number";
    public static final String COL_CREATION_DATE = "creation_date";
    public static final String COL_LAST_UPDATE_DATE = "last_update_date";
    public static final String COL_CREATED_BY = "created_by";
    public static final String COL_LAST_UPDATED_BY = "last_updated_by";
    public static final String COL_CREATE_ACCOUNT = "created_account";
    public static final String COL_UPDATE_ACCOUNT = "last_updated_account";

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    protected Long id;

    @TableField(value = "`remark`")
    protected String remark;

    @TableField(value = "`is_delete`")
    protected Integer isDelete;

    @TableField(value = "`object_version_number`")
    protected Integer objectVersionNumber;

    @TableField(value = "`creation_date`")
    protected Date creationDate;

    @TableField(value = "`last_update_date`")
    protected Date lastUpdateDate;

    @TableField(value = "`created_by`")
    protected Long createdBy;

    @TableField(value = "`last_updated_by`")
    protected Long lastUpdatedBy;

    @TableField(value = "`created_account`")
    protected String createdAccount;

    @TableField(value = "`last_updated_account`")
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

    public void updateObjectVersionNumber(){
        this.setObjectVersionNumber(this.getObjectVersionNumber() + 1);
    }

    public void initId(){
        Long nextId = IDUtils.nextId(MessageConstant.BOOK_FICTION_GLOBAL);
        this.setId(nextId);
    }

    public void init() {
        if(ObjectUtils.isEmpty(getIsDelete())){
            this.setIsDelete(0);
        }
        if(ObjectUtils.isEmpty(getObjectVersionNumber())){
            this.setObjectVersionNumber(1);
        }
        if(ObjectUtils.isEmpty(getCreationDate())){
            this.setCreationDate(new Date());
        }
        if(ObjectUtils.isEmpty(getLastUpdateDate())){
            this.setLastUpdateDate(new Date());
        }
        if(!StringUtils.hasText(getCreatedAccount())){
            this.setCreatedAccount("ANONYMOUS");
        }
        if(!StringUtils.hasText(getLastUpdatedAccount())){
            this.setLastUpdatedAccount("ANONYMOUS");
        }
        if(ObjectUtils.isEmpty(getCreatedBy())){
            this.setCreatedBy(-1L);
        }
        if(ObjectUtils.isEmpty(getLastUpdatedBy())){
            this.setLastUpdatedBy(-1L);
        }
    }
}
