package com.wzh.paper.demo;

public class ProducerClient {

    public static void main(String[] args) {
        TestProducer pro = new TestProducer(ConfigureAPI.TOPIC);
        pro.start();
    }


}
