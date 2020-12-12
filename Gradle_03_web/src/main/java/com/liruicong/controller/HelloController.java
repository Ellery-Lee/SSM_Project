package com.liruicong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/list")
    public String toList(){
        return "success";
    }
}
