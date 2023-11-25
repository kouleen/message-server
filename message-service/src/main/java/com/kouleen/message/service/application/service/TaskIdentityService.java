package com.kouleen.message.service.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kouleen.message.service.domain.task.entity.TaskIdentity;

/**
 * @author zhangqing
 * @since 2023/11/25 12:54
 */
public interface TaskIdentityService extends IService<TaskIdentity> {
    TaskIdentity createTaskIdentity(TaskIdentity taskIdentity);

    boolean checkTaskIdentity(String userCode);
}
