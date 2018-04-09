package com.wzh.paper.demo;

public class ConsumerClient {
    public static void main(String[] args) {
        Receiver con1 = new Receiver(1);
        Receiver con2 = new Receiver(2);
        Receiver con3 = new Receiver(3);
        Receiver con4 = new Receiver(4);
        new Thread(con1).start();
        new Thread(con2).start();
        new Thread(con3).start();
        new Thread(con4).start();
    }
}
