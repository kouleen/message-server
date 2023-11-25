package com.kouleen.message.service.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kouleen.message.service.application.service.TaskIdentityService;
import com.kouleen.message.service.domain.task.entity.TaskIdentity;
import com.kouleen.message.service.domain.task.face.mapper.TaskIdentityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhangqing
 * @since 2023/11/25 12:54
 */
@Service
public class TaskIdentityServiceImpl extends ServiceImpl<TaskIdentityMapper, TaskIdentity> implements TaskIdentityService {

    @Autowired
    private TaskIdentityMapper taskIdentityMapper;

    @Override
    public boolean checkTaskIdentity(String userCode) {
        List<TaskIdentity> taskIdentities = taskIdentityMapper.selectList(new LambdaQueryWrapper<TaskIdentity>().eq(TaskIdentity::getUserCode, userCode));
        return !CollectionUtils.isEmpty(taskIdentities);
    }

    @Override
    public TaskIdentity createTaskIdentity(TaskIdentity taskIdentity) {
        taskIdentityMapper.insert(taskIdentity);
        return taskIdentityMapper.selectById(taskIdentity.getId());
    }
}
