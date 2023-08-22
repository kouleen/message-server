package com.kouleen.message.service.infrastructure.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @author zhangqing
 * @since 2023/8/3 20:39
 */
public class ThreadUtil {

    public static void closeThread(Collection<Long> threadIdList){
        if(CollectionUtils.isEmpty(threadIdList)){
            return;
        }
        threadIdList.forEach(threadId ->{
            Thread thread = ThreadUtil.findThread(threadId);
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        });
    }

    public static Thread findThread(long threadId) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
                if(threadId == threads[i].getId()) {
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }
}
