package com.chouxiaozi.vhr.services;

import com.chouxiaozi.vhr.mapper.PositionMapper;
import com.chouxiaozi.vhr.model.Position;
import com.chouxiaozi.vhr.model.PositionExample;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;


    public List<Position> getAllPosition() {
        return positionMapper.selectByExample(new PositionExample());
    }

    public RespBean addPosition(Position position) {
        position.setCreateDate(new Date());
        if (positionMapper.insertSelective(position) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    public RespBean updatePosition(Position position) {
        if (positionMapper.updateByPrimaryKeySelective(position) == 1) {
            return RespBean.ok("更新成功!");
        } else {
            return RespBean.error("更新失败!");
        }
    }

    public RespBean delPosition(Integer id) {
        if(positionMapper.deleteByPrimaryKey(id) == 1){
            return RespBean.ok("删除成功!");
        }else{
            return RespBean.error("删除失败!");
        }
    }
}
