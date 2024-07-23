package com.example.demo.bean;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Getter
@Setter
public class Jobs {
    @ExcelProperty("id")
    private int id;

    @ExcelProperty("name")
    private String name;

    @ExcelProperty("status")
    private boolean status;
    
    @ExcelProperty(value = "creationTime")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(20)
    private LocalDateTime creationTime;
    
    @ExcelProperty("userName")
    private String userName;
    
    public Jobs(){

    }
    public Jobs(String name){
        this.name = name;
        status = false;
    }
    public int getId(){
        return id;
    }

}