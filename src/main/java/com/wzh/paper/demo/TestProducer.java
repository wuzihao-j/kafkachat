package com.wzh.paper.demo;


import kafka.producer.KeyedMessage;
import kafka.producer.Producer;
import kafka.producer.ProducerConfig;
import scala.collection.Seq;

import java.util.Properties;

/**
 * @Date May 22, 2015
 *
 * @Author dengjie
 *
 * @Note Kafka JProducer
 */
public class TestProducer extends Thread {

    private Producer<Integer, String> producer;
    private String topic;
    private Properties props = new Properties();
    private final int SLEEP = 1000 * 3;

    public TestProducer(String topic) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", ConfigureAPI.BROKER_LIST);
        producer = new Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
        int offsetNo = 1;
        while (true) {
            String msg = new String("Message_" + offsetNo);
            System.out.println("Send->[" + msg + "]");
            producer.send((Seq<KeyedMessage<Integer, String>>) new KeyedMessage<Integer, String>(topic, msg));
            offsetNo++;
            try {
                sleep(SLEEP);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
