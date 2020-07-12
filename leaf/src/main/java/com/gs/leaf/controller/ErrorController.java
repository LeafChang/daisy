package com.gs.leaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/err")
public class ErrorController {

    @RequestMapping("/error")
    public String error(){
        int i = 1/0;
        return "thymeleaf/error";
    }


}
