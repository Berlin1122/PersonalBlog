package com.chenyulin.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog")
public class TestController {

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String sayHello(){
        return "html/index";
    }
}
