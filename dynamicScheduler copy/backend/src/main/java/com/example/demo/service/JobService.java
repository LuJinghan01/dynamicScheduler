package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Jobs;

public interface JobService{
    List<Jobs> findAll();

    void insert(int id, String name);
    void delete(int id);
    void update(int id, String newName);
    Jobs findById(int id);
    void deleteAll();
}