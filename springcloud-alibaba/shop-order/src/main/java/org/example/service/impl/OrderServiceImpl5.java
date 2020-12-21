package org.example.service.impl;

import com.alibaba.fastjson.JSON;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.dao.OrderDao;
import org.example.domain.Order;
import org.example.domain.Product;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl5 {

    @Autowired
    private OrderDao orderDao;
    @Qualifier("org.example.service.ProductService")
    @Autowired
    private ProductService productService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GlobalTransactional // 开启全局事务配置
    public Order createOrder(Integer pid){
        log.info("接收到{}几号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        //①调用商品微服务,查询商品信息
        //问题
        // 1 代码可读性不好
        // 2 编程风格不统一
        Product product =productService.findByPid(pid);


        log.info("查询到{}号商品的信息，内容是：{}", pid, JSON.toJSON(product));
        //②下单
        Order order = new Order();
        order.setUid(1)
                .setUsername("测试用户")
                .setPid(pid)
                .setPname(product.getPname())
                .setPprice(product.getPprice())
                .setNumber(1);

        orderDao.save(order);

        log.info("创建订单成功，订单信息为{}", JSON.toJSONString(order));

        //③扣库存
        productService.reduceInventory(pid, order.getNumber());

        //④向mq中投递一个下单成功的消息
        //参数一：指定topic
        //阐述二：指定消息体
        rocketMQTemplate.convertAndSend("order-topic", order);

        return order;
    }
}
