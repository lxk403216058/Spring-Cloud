package org.example.service.impl;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.example.dao.TxLogDao;
import org.example.domain.Order;
import org.example.domain.TxLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RocketMQTransactionListener(txProducerGroup = "tx_produce_group")
public class OrderServiceImpl4Listener implements RocketMQLocalTransactionListener {
    @Autowired
    private OrderServiceImpl4 orderServiceImpl4;
    @Autowired
    private TxLogDao txLogDao;

    //执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        String txId = (String) message.getHeaders().get("txId");

        try{
            Order order = (Order)o;
            orderServiceImpl4.createOrder(txId, order);

            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //消息回查
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String txId = (String) message.getHeaders().get("txId");
        TxLog txLog = txLogDao.findById(txId).get();

        if (txLog != null){
            //本地事务（订单成功了）
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
