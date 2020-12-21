package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Order;
import org.example.service.impl.OrderServiceImpl5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController5 {
    @Autowired
    private OrderServiceImpl5 orderServiceImpl5;

    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        return orderServiceImpl5.createOrder(pid);
    }


}
