package com.chouxiaozi.vhr.controller;

import com.chouxiaozi.model.Menu;
import com.chouxiaozi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/getMenu")
    public List<Menu> getMenu(){
        return menuService.getMenuByHrId();
    }
}
