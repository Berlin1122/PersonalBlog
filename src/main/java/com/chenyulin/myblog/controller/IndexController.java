package com.chenyulin.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 跳转主页,请求路径不加项目名称可直接跳转到主页
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String gotoIndex(){
        return "redirect:/blog/index";
    }
}
