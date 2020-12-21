package org.example.service.impl;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.dao.OrderDao;
import org.example.dao.TxLogDao;
import org.example.domain.Order;
import org.example.domain.TxLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl4 {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TxLogDao txLogDao;

    //发送半事务消息
    public void createOrderBefore(Order order){
        String txId = UUID.randomUUID().toString();
        rocketMQTemplate.sendMessageInTransaction(
                "tx_produce_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(),
                order
        );

    }

    @Transactional
    public void createOrder(String txId, Order order){
        //保存订单
        orderDao.save(order);
        TxLog txLog = new TxLog();
        txLog.setTxId(txId).setDate(new Date());

        //记录事务日志
        txLogDao.save(txLog);
    }

}
