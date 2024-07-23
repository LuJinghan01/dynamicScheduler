package com.example.demo;

// import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
// import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
// import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.example.demo.bean.Cron;
import com.example.demo.bean.Jobs;
import com.example.demo.bean.MyUser;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.demo")
@MappedTypes({Jobs.class, MyUser.class, Cron.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
