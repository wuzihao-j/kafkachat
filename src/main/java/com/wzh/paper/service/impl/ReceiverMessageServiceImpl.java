package com.wzh.paper.service.impl;

import com.wzh.paper.demo.ConfigureAPI;
import com.wzh.paper.service.ReceiverMessageService;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class ReceiverMessageServiceImpl implements ReceiverMessageService {

    public String receive(long receiverID) {
        String topic = receiverID + "";
        Properties props = new Properties();
        props.put("zookeeper.connect", ConfigureAPI.ZK);
        props.put("group.id", receiverID + "");
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("max.block.ms", "0");
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            String line = new String(it.next().message());
            int sep = line.indexOf(":");
            String senderID = line.substring(0, sep);
            String content = line.substring(sep + 1, line.length());
            System.out.println(senderID + "say:" + content);
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "没数据";
    }
}
