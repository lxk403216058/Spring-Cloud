package org.example.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.example.pojo.Order;
import org.example.pojo.Product;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    //服务引用
    @Reference
    private ProductService productService;

    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接收到{}几号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        //调用商品微服务,查询商品信息
        //问题
        // 1 代码可读性不好
        // 2 编程风格不统一
        Product product = productService.findByPid(pid);

        log.info("查询到{}号商品的信息，内容是：{}", pid, JSON.toJSON(product));
        //下单
        Order order = new Order();
        order.setUid(1)
                .setUsername("测试用户")
                .setPid(pid)
                .setPname(product.getPname())
                .setPprice(product.getPprice())
                .setNumber(1);

        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为{}", JSON.toJSONString(order));

        return order;
    }
}
