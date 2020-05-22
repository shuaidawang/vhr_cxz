package com.chouxiaozi.vhr.controller.system.hr;

import com.chouxiaozi.vhr.model.Hr;
import com.chouxiaozi.vhr.service.HrService;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if(1 == hrService.updateHr(hr)){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
