package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.RoleMapper;
import com.chouxiaozi.vhr.model.Role;
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
}
