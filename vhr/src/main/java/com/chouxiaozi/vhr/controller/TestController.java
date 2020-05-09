package com.chouxiaozi.vhr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author Administrator
 * @Date 2020/5/916:53
 * @Version 1.0
 */
@RestController
@CrossOrigin()
public class TestController {

    /**
     * @Author chouxiaozi
     * @Date 2020/5/9 16:56
     * @Param []
     * @return void
     **/
    @RequestMapping("/singleArray")
    public Integer singleArray(Integer[] ids){
        return ids.length;
    }
    @RequestMapping("/singleArray2")
    public Integer singleArray2(@RequestParam("ids[]") Integer[] ids){
        return ids.length;
    }
    @RequestMapping("/singleArray3")
    public String singleArray3(String ids){
        return ids;
    }

    @RequestMapping("/singleList")
    public Integer singleList(List<Integer> ids){
        return ids.size();
    }
}
