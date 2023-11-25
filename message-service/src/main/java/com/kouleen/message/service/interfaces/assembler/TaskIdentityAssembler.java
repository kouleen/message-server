package com.kouleen.message.service.interfaces.assembler;

import com.kouleen.message.api.interfaces.vo.TaskIdentityVO;
import com.kouleen.message.service.domain.task.entity.TaskIdentity;

/**
 * @author zhangqing
 * @since 2023/11/25 12:53
 */
public class TaskIdentityAssembler {
    public static TaskIdentity toDO(TaskIdentityVO taskIdentityVO) {
        String userid = taskIdentityVO.getUserid();
        TaskIdentity taskIdentity = new TaskIdentity();
        taskIdentity.setUserCode(userid);
        taskIdentity.setUserName(taskIdentityVO.getUser());
        taskIdentity.setUserGender(Integer.parseInt(userid.substring(16, 17)) % 2 == 0 ? 0 : 1);
        return taskIdentity;
    }
}
