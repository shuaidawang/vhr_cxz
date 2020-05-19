package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.DepartmentMapper;
import com.chouxiaozi.vhr.model.Department;
import com.chouxiaozi.vhr.model.DepartmentExample;
import com.chouxiaozi.vhr.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Administrator
 * @Date 2020/5/1513:51
 * @Version 1.0
 */
@Service
public class DeptService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getDeptTree() {
        return departmentMapper.getDeptTree(-1);
    }

    public RespBean addDept(Department department) {
        department.setEnabled(true);
        departmentMapper.addDept(department);
        if (1 == department.getResult()) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    public RespBean delDept(Department department) {
        departmentMapper.delDept(department);
        if (1 == department.getResult()) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    public List<Department> getDeptList() {
        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andEnabledEqualTo(true);
        return departmentMapper.selectByExample(example);
    }
}
