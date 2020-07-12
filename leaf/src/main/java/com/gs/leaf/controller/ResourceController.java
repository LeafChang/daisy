package com.gs.leaf.controller;

import com.gs.leaf.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private Resource resource;

    @RequestMapping("/getResource")
    public Object getResource(){
        Map<String,Object> resMap = new HashMap<String, Object>(4);
        resMap.put("name",resource.getName());
        resMap.put("domain",resource.getDomain());
        resMap.put("lang",resource.getLang());
        resMap.put("you",resource.getYou());
        return resMap;
    }



}
