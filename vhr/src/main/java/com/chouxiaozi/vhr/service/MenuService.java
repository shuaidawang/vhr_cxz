package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.MenuMapper;
import com.chouxiaozi.vhr.model.Menu;
import com.chouxiaozi.vhr.util.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuByHrId() {
        return menuMapper.getMenuByHrId(HrUtil.getCurrentHr().getId());
    }

    public List<Menu> getMenuRole() {
        return menuMapper.getMenuRole();
    }

    /**
     * 查询出所有的菜单，树形结构
     *
     * @return
     */
    public List<Menu> listMenus() {
        List<Menu> list = getMenusByParentId(null,null);
        return list;
    }

    /**
     * 递归查询子菜单，组装children
     * @param list
     * @param parentId
     * @return
     */
    public List<Menu> getMenusByParentId(List<Menu> list, Integer parentId) {
        List<Menu> list1 = menuMapper.getByParentId(parentId);
        if(null == list){
            list = new ArrayList<>();
            list.addAll(list1);
        }
        if(CollectionUtils.isEmpty(list1)){
            return null;
        }else{
            for (Menu menu : list1) {
                menu.setChildren(getMenusByParentId(list1,menu.getId()));
            }
        }
        return list1;
    }
}
