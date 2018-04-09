package com.wzh.paper.service;

public interface SendMessageService {
    void send(long senderID, long receiverID, String msg);
}
