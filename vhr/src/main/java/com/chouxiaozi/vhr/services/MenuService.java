package com.chouxiaozi.vhr.services;

import com.chouxiaozi.vhr.mapper.MenuMapper;
import com.chouxiaozi.vhr.model.Menu;
import com.chouxiaozi.vhr.utils.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuByHrId() {
        return menuMapper.getMenuByHrId(HrUtil.getCurrentHr().getId());
    }

    public List<Menu> getMenuRole(){
        return menuMapper.getMenuRole();
    }
}
