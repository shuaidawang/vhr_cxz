package com.chouxiaozi.vhr.controller;

import com.chouxiaozi.vhr.services.HrService;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    HrService hrService;

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.ok("请登录");
    }
}
