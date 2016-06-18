package com.duanzq.easyBuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by duanzq on 16/6/17.
 */
@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String toIndex(){
        System.out.println("123");
        return "index";
    }
}
