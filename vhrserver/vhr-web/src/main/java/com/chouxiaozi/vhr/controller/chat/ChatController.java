package com.chouxiaozi.vhr.controller.chat;

import com.chouxiaozi.vhr.model.Hr;
import com.chouxiaozi.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private HrService hrService;


    @GetMapping("/otherHrs")
    public List<Hr> getOtherHrs(){
        return hrService.getOtherHrs();
    }
}
