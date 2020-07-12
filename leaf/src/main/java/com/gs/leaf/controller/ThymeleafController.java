package com.gs.leaf.controller;

import com.gs.leaf.pojo.User;
import com.gs.leaf.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Autowired
    private Resource resource;


    @RequestMapping("/index")
    public String index(){
        return "/thymeleaf/index";
    }

    @RequestMapping("/center")
    public String center(ModelMap map){
        resource.setDate(new Date());
        map.addAttribute("resource",resource);
        map.addAttribute("user",resource);
        return "/thymeleaf/center/center";
    }

    @RequestMapping("/postform")
    public String postform(User user,ModelMap map){
        System.out.println("====user ："+user);
        map.addAttribute("user",user);
        return "/thymeleaf/center/center";
    }




}
