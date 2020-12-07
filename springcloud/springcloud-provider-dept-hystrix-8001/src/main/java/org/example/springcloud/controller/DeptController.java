package org.example.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.example.springcloud.pojo.Dept;
import org.example.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供RESTFUL服务
@RestController
public class DeptController {
    @Autowired
    private DeptService service;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = service.queryById(id);

        if (dept == null){
            throw new RuntimeException("id=>"+id+",不存在该用户，或者信息无法找到~");
        }

        return dept;
    }

    //备选方案
    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id=>"+id+"没有对应的信息，null---@Hystrix")
                .setDbsource("no this database in Mysql");
    }
}
