package com.wzh.paper.service.impl;

import com.wzh.paper.demo.ConfigureAPI;
import com.wzh.paper.service.SendMessageService;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    public void send(long senderID, long receiverID, String msg) {
        kafka.javaapi.producer.Producer<Integer, String> producer;
        final Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", ConfigureAPI.BROKER_LIST);
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        String topic = receiverID  + "";
        msg = senderID + ":" + msg;
        producer.send(new KeyedMessage<Integer, String>(topic, msg));
    }
}
