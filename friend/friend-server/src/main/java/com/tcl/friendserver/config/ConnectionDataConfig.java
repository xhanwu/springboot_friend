package com.tcl.friendserver.config;

import com.tcl.friendserver.task.ConnectionDataTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@Configuration
public class ConnectionDataConfig {

    private static final String CONNECTION_DATA_TASK_IDENTITY = "ConnectionDataTaskQuartz";

    @Bean
    public JobDetail quartzDetail() {
        return JobBuilder.newJob(ConnectionDataTask.class).withIdentity(CONNECTION_DATA_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger() {
        // 两个小时一次将Redis数据库中的数据更新到DB中
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(30)  // 设置时间周期单位秒
//                .withIntervalInMinutes(30) // 设置时间周期单位分钟
                .withIntervalInHours(2)  // 设置时间周期单位小时

                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(CONNECTION_DATA_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
