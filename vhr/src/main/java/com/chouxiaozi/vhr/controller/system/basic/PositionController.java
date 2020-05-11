package com.chouxiaozi.vhr.controller.system.basic;

import com.chouxiaozi.vhr.model.Position;
import com.chouxiaozi.vhr.services.PositionService;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 基础信息设置
 * @Author chouxiaozi
 * @Date 2020/5/8 15:27
 **/

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPosition(){
        return positionService.getAllPosition();
    }
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        return positionService.addPosition(position);
    }
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        return positionService.updatePosition(position);
    }
    @DeleteMapping("/{id}")
    public RespBean delPosition(@PathVariable("id") Integer id){
        return positionService.delPosition(id);
    }

    @DeleteMapping("/")
    public RespBean delPositions(Integer[] ids){
        return positionService.delPositions(ids);
    }
}
