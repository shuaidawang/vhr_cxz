package com.chouxiaozi.vhr.controller.system.hr;

import com.chouxiaozi.vhr.model.Hr;
import com.chouxiaozi.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author Administrator
 * @Date 2020/5/229:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @GetMapping("/")
    public List<Hr> listHrs(String keyword){
        return hrService.listHrs(keyword);
    }
}
