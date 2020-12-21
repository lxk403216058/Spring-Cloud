package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.impl.OrderServiceImpl3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController3 {
    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @RequestMapping("/order/message1")
    public String message1(){
        return "message1";
    }
    @RequestMapping("/order/message2")
    public String message2(){
        //orderServiceImpl3.message();
        return "message2";
    }
    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name, Integer age){
        return "message3:"+name+" "+age;
    }

    @RequestMapping("/order/message")
    public String message(){
        return orderServiceImpl3.message("Xx");
    }
}
