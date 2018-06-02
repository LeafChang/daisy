package com.gs.leaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/hello/")
    public String hellWorld(Model model) {

        model.addAttribute("hello","Hello World!");

        return  "hello";
    }

}
