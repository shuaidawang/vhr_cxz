package com.chouxiaozi.vhr.controller;

import com.chouxiaozi.vhr.model.Hr;
import com.chouxiaozi.vhr.services.HrService;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HrService hrService;

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.ok("请登录");
    }

    @GetMapping("/hello")
    public String Hello(String name){
        Hr user = hrService.selectUserByUsername(name);
        if(null == user){
            return  "用户不存在";
        }
        return user.getPassword();
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "hello2";
    }

}
