package com.study.studentsys.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2022/4/15 21:18
 */
@Component
public class CronComponent {

    @Autowired
    private EmailComponent emailComponent;

    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public void cronJob(Date targetDate, String emailAddress, String remindContent) {
        Runnable runnable = () -> {
            // 把run方法里的内容换成你要执行的内容
            emailComponent.sendRemindEmail(emailAddress, remindContent);
        };

        //command--执行的任务,initialDelay--延迟开始,unit--时间单位
        service.schedule(runnable, targetDate.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

}
