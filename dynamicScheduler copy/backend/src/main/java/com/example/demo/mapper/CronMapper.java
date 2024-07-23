package com.example.demo.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;


@MapperScan
public interface CronMapper {
    @Select("select cron from cron where cron_id = #{id}")
    @Result(column = "cron", property = "cron")
    String findByCronId(int id);

    
    
}