package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.RoleMapper;
import com.chouxiaozi.vhr.model.Role;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<Role> listRoles() {
        return roleMapper.selectByExample(null);
    }

    public RespBean addRole(Role role) {
        if(1 == roleMapper.insert(role)){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    public RespBean delRole(Integer id) {
        if(1 == roleMapper.deleteByPrimaryKey(id)){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
