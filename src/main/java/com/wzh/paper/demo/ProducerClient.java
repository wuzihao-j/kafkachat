package com.wzh.paper.demo;

public class ProducerClient {

    public static void main(String[] args) {
        Sender pro = new Sender(111, 123);
        new Thread(pro).start();
    }


}
