package com.kouleen.message.api.interfaces.vo;

import com.kouleen.message.api.infrastructure.core.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangqing
 * @since 2023/8/10 8:25
 */
@ApiModel("定时任务响应映射")
public class TaskScheduledVO extends BaseVO {

    @ApiModelProperty("定时任务编码")
    private String taskCode;

    @ApiModelProperty("定时任务名称")
    private String taskName;

    @ApiModelProperty("定时任务CRON表达式")
    private String cronExpression;

    @ApiModelProperty("定时任务执行命令")
    private String taskCommand;

    @ApiModelProperty("定时任务状态")
    private Integer taskStatus;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTaskCommand() {
        return taskCommand;
    }

    public void setTaskCommand(String taskCommand) {
        this.taskCommand = taskCommand;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
