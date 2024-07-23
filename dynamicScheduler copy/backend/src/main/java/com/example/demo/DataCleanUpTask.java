package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

// import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demo.config.CleanupConfig;
import com.example.demo.mapper.JobsMapper;

@Component
// @RefreshScope
public class DataCleanUpTask implements SchedulingConfigurer {
    @Autowired
    JobsMapper mapper;
    @Autowired
    CleanupConfig cleanupConfig;
    private static final Logger log = LoggerFactory.getLogger(DataCleanUpTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @NacosValue(value = "${cleanup.test}", autoRefreshed = true)
    private String test;

    // @Scheduled(cron="#{@cleanupConfig.getCron()}",
    // zone="#{@cleanupConfig.getZone()}")
    // public void deleteOldData() {
    // log.info("start cleaning up data at {}", dateFormat.format(new Date()));
    // int cleanupDays = cleanupConfig.getCleanupDays();
    // // int cleanupHours = config.getCleanupHours();
    // LocalDateTime thresholdDate = LocalDateTime.now().minus(cleanupDays,
    // ChronoUnit.DAYS);
    // List<Jobs> OldData = mapper.findOldData(thresholdDate);
    // for(Jobs job:OldData){
    // log.info("delete job record: id {}, name {}", job.getId(), job.getName());
    // mapper.delete(job.getId());
    // }
    // // mapper.deleteOldData(thresholdDate);
    // log.info("finished cleaning up data at {}", dateFormat.format(new Date()));

    // }

    // @Scheduled(cron="#{cleanupConfig.getTest()}")
    // public void test() {
    // // System.out.println(test);
    // log.info("start test at {}", dateFormat.format(new Date()));
    // }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> log.info("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                triggerContext -> {
                    String cron = cleanupConfig.getTest();
                    if (cron.isEmpty()) {
                        // Omitted Code ..
                    }
                    return new CronTrigger(cron).nextExecution(triggerContext);
                });
    }

}
