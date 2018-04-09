package com.wzh.paper.demo;


import kafka.producer.KeyedMessage;
import kafka.producer.Producer;
import kafka.producer.ProducerConfig;
import scala.collection.Seq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static java.lang.Thread.sleep;

/**
 * @Date May 22, 2015
 *
 * @Author dengjie
 *
 * @Note Kafka JProducer
 */
public class Sender implements Runnable {

    private final kafka.javaapi.producer.Producer<Integer, String> producer;
    private String topic;
    private long senderID;
    private final Properties props = new Properties();

    public Sender(long senderID, long receiverID)
    {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", ConfigureAPI.BROKER_LIST);
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        this.topic = receiverID  + "";
        this.senderID = senderID;
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
                while((line = br.readLine()) != null){
                    line = senderID + ":" + line;
                    producer.send(new KeyedMessage<Integer, String>(topic, line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
