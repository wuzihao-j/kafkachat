package com.wzh.paper.controller;

import com.wzh.paper.entity.Chat;
import com.wzh.paper.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sender")
public class SenderController {

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Result sendMessage(@RequestBody Chat chat){



        return new Result(Result.ResultCode.SEND_SUCCESS, "成功");
    }

}
