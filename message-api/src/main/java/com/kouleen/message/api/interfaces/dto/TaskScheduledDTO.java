package com.kouleen.message.api.interfaces.dto;

import com.kouleen.message.api.infrastructure.core.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;


/**
 * @author zhangqing
 * @since 2023/8/10 8:28
 */
@ApiModel("定时任务请求映射")
public class TaskScheduledDTO extends BaseDTO {

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

    @Override
    public boolean equals(Object obj) {
        if (ObjectUtils.isEmpty(obj)) return false;
        if (obj instanceof TaskScheduledDTO) {
            TaskScheduledDTO taskScheduledDTO = (TaskScheduledDTO) obj;
            // 执行命令
            return this.getTaskCommand().equals(taskScheduledDTO.getTaskCommand())
                    // 时间表达式
                    && this.getCronExpression().equals(taskScheduledDTO.getCronExpression())
                    // 装填
                    && this.getTaskStatus().equals(taskScheduledDTO.getTaskStatus());
        }
        return false;
    }
}
