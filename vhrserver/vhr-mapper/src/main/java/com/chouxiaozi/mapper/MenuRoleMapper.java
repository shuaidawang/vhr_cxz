package com.chouxiaozi.mapper;

import com.chouxiaozi.model.MenuRole;
import com.chouxiaozi.model.MenuRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRoleMapper {
    int countByExample(MenuRoleExample example);

    int deleteByExample(MenuRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    List<MenuRole> selectByExample(MenuRoleExample example);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    int updateByExample(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    void deleteByRoleId(Integer rid);

    Integer insertRoleMenus(@Param("rid") Integer rid, @Param("menuIds") Integer[] menuIds);
}