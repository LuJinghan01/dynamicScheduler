package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demo.mapper.CronMapper;

@Component
// @ConfigurationProperties(prefix = "")
@NacosConfigurationProperties(prefix = "",autoRefreshed = true, dataId = "demo-public.properties", type = ConfigType.PROPERTIES)
public class CleanupConfig {

    // @NacosValue(value="${cleanup.days}")
    private int cleanupDays;
    // @NacosValue(value="${cleanup.cron}")
    private String cron;
    // @NacosValue(value="${cleanup.zone}")
    private String zone;
    @NacosValue(value="${cleanup.test}",autoRefreshed=true)
    private String test;
    // @NacosValue(value="${cleanup.hours}")
    private int cleanupHours;

    @Autowired
    private CronMapper cronMapper;

    public String getTest() {
        System.out.println(test);
        return test;
        // return cronMapper.findByCronId(2);
    }

    public int getCleanupDays() {
        return cleanupDays;
    }

    public int getCleanupHours() {
        return cleanupHours;
    }

    public void setCleanupDays(int cleanupDays) {
        this.cleanupDays = cleanupDays;
    }

    public String getCron() {
        return cronMapper.findByCronId(1);
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
