package com.wzh.paper.demo;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Date May 22, 2015
 *
 * @Author dengjie
 *
 * @Note Kafka Consumer
 */
public class Receiver implements Runnable {

    private ConsumerConnector consumer;
    private String topic;
    private final int SLEEP = 1000 * 3;

    public Receiver(long receiverID) {
        consumer = Consumer.createJavaConsumerConnector(this.consumerConfig(receiverID));
        this.topic = receiverID + "";
    }

    private ConsumerConfig consumerConfig(long receiverID) {
        Properties props = new Properties();
        props.put("zookeeper.connect", ConfigureAPI.ZK);
        props.put("group.id", receiverID + "");
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        if (it.hasNext()) {
            String line = new String(it.next().message());
            int sep = line.indexOf(":");
            String senderID = line.substring(0, sep);
            String content = line.substring(sep + 1, line.length());
            System.out.println(senderID + "say:" + content);
            try {
                Thread.sleep(SLEEP);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}