package com.chouxiaozi.vhr.controller;

import com.chouxiaozi.vhr.services.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HrService hrService;

    @GetMapping("/hello")
    public String Hello(String name){
        UserDetails user = hrService.selectUserByUsername(name);
        if(null == user){
            return  "用户不存在";
        }
        return user.getPassword();
    }
}
