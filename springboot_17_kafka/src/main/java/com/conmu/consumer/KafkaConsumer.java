package com.conmu.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author mucongcong
 * @date 2022/05/19 16:44
 * @since
 **/
@Component
public class KafkaConsumer {
    // 消费监听
    @KafkaListener(topics = {"topic001"})
    public void onMessage1(ConsumerRecord<?, ?> record){
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
    }
}
