package com.wzh.paper.controller;

import com.wzh.paper.entity.Chat;
import com.wzh.paper.entity.Result;
import com.wzh.paper.service.SendMessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sender")
public class SenderController {

    @Resource
    private SendMessageService sendMessageService;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Result sendMessage(@RequestBody Chat chat){

        sendMessageService.send(chat.getSender(), chat.getReceiver(), chat.getMsg());

        return new Result(Result.ResultCode.SEND_SUCCESS, "成功");
    }

}
