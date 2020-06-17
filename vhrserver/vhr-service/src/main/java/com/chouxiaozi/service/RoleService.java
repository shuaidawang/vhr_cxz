package com.chouxiaozi.service;

import com.chouxiaozi.mapper.HrRoleMapper;
import com.chouxiaozi.mapper.RoleMapper;
import com.chouxiaozi.model.HrRoleExample;
import com.chouxiaozi.model.Role;
import com.chouxiaozi.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;

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

    public List<Role> listRolesByHrId(Integer hrId){
        return roleMapper.listRolesByHrId(hrId);
    }

    @Transactional
    public int updateRoles(Integer hrId, Integer[] roles) {
        delRolesByHrId(hrId);
        if(roles.length == 0){
            return 0;
        }
        return hrRoleMapper.batchInsert(hrId, roles);
    }

    public void delRolesByHrId(Integer hrId) {
        HrRoleExample hrRoleExample = new HrRoleExample();
        hrRoleExample.createCriteria().andHridEqualTo(hrId);
        hrRoleMapper.deleteByExample(hrRoleExample);
    }
}
