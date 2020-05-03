package com.chouxiaozi.vhr.controller;

import com.chouxiaozi.vhr.model.Menu;
import com.chouxiaozi.vhr.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/getMenuByHrId")
    public List<Menu> getMenuByHrId(){
        return menuService.getMenuByHrId();
    }
}
