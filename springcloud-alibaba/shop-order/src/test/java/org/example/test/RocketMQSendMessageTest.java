package org.example.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class RocketMQSendMessageTest {
    //发送消息
    public static void main(String[] args) throws Exception {
        /*DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");

        producer.setNamesrvAddr("39.99.288.233:9876");

        producer.start();

        for (int i = 0; i < 1000; i++) {
            try {

                Message msg = new Message("TopicTest" *//* Topic *//*,
                        "TagA" *//* Tag *//*,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) *//* Message body *//*
                );

                SendResult sendResult = producer.send(msg);

                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();*/
        //1. 创建消息生产者，并设置生产组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        //2. 为生产者设置NameServer的地址
        producer.setNamesrvAddr("39.99.228.233:9876");
        //3. 启动生产者
        producer.start();
        //4. 构建消息对象，主要是设置消息的主题 标签内容
        Message msg = new Message("myTopic", "myTag",
                ("RocketMQ Message").getBytes());
        //5. 发送消息 第二个参数代表超时时间
        SendResult result = producer.send(msg, 10000);
        System.out.println(result);
        //6. 关闭生产者
        producer.shutdown();
    }
}
