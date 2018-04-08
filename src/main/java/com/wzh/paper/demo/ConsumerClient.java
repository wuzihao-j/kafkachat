package com.wzh.paper.demo;

public class ConsumerClient {
    public static void main(String[] args) {
        TestConsumer con = new TestConsumer(ConfigureAPI.TOPIC);
        con.start();
    }
}
