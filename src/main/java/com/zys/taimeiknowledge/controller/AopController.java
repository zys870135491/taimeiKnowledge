package com.zys.taimeiknowledge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @GetMapping(value = "/aopController/test")
    public String test(@RequestParam("id") String id,@RequestParam("name") String name){
        return id+name;
    }


}
