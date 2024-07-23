package com.example.demo.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.bean.Jobs;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface JobsMapper {
    @Select("select * from jobs;")
    @Results({
        @Result(id = true, column = "id", property = "id"),
        @Result(column = "name", property = "name"),
        @Result(column = "status", property = "status"),
        @Result(column = "creation_time", property = "creationTime"),
        @Result(column = "user_name", property = "userName"),
    })
    List<Jobs> findAll();

    @Insert("insert into jobs (id,name) values (#{id},#{name})")
    void insert(@Param("id") int id,@Param("name") String name);

    @Select("select * from jobs where id=#{id}")
    Jobs findById(int id);

    @Insert("update jobs set name=#{name} where id=#{id};")
    void update(int id, String name);

    @Delete("delete from jobs where id=#{id};")
    void delete(int id);

    @Delete("delete from jobs;")
    void deleteAll();

    @Delete("DELETE FROM jobs WHERE creation_time < #{thresholdDate}")
    void deleteOldData(@Param("thresholdDate") LocalDateTime thresholdDate);

    @Select("select * from jobs WHERE creation_time < #{thresholdDate}")
    List<Jobs> findOldData(@Param("thresholdDate") LocalDateTime thresholdDate);

}