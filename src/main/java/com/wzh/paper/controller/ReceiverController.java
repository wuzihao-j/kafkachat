package com.wzh.paper.controller;

import com.wzh.paper.demo.Receiver;
import com.wzh.paper.entity.Chat;
import com.wzh.paper.entity.Result;
import com.wzh.paper.service.ReceiverMessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    @Resource
    private ReceiverMessageService receiverMessageService;

    @RequestMapping(value = "/receiveMessage", method = RequestMethod.POST)
    public Result receiveMessage(@RequestBody Chat chat){

        String content = receiverMessageService.receive(chat.getReceiver());

        return new Result(Result.ResultCode.SEND_SUCCESS, content);
    }




}
