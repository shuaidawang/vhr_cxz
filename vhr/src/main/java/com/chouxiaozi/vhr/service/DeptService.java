package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.DepartmentMapper;
import com.chouxiaozi.vhr.model.Department;
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
}
