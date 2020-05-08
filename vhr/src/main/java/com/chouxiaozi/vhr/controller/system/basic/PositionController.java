package com.chouxiaozi.vhr.controller.system.basic;

import com.chouxiaozi.vhr.model.Position;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 基础信息设置
 * @Author chouxiaozi
 * @Date 2020/5/8 15:27
 **/

@RestController
@RequestMapping("/system/basic")
public class PositionController {

    @GetMapping("/")
    public List<Position> getAllPosition(){
        return null;
    }
    @PostMapping("/")
    public RespBean addPosition(Position position){
        return null;
    }
    @DeleteMapping("/{id}")
    public RespBean delPosition(@PathVariable("id") Integer id){
        return null;
    }
    @PutMapping("/")
    public RespBean updatePosition(Position position){
        return null;
    }

}
