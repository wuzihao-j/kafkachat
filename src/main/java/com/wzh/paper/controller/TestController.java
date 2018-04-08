package com.wzh.paper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = "/test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/aaa/{str}", method = RequestMethod.GET)
    public String aaa(@PathVariable("str") String str){
        return str;
    }
}
