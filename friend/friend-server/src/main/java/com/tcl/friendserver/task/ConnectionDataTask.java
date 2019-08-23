package com.tcl.friendserver.task;

import com.tcl.friendserver.service.connectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author guoqi.zou.hz
 * Create By 2019/7/15
 */

@Slf4j
public class ConnectionDataTask extends QuartzJobBean {

    @Autowired
    private connectionService connectionService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("ConnectionDataTask implement", sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
        connectionService.transWeiboSupportDataFromRedisToDB();
        connectionService.transWeiboSupportCountFromRedis2DB();
        connectionService.transCommentSupportDataFromRedisToDB();
        connectionService.transCommentSupportCountFromRedis2DB();
    }
}
