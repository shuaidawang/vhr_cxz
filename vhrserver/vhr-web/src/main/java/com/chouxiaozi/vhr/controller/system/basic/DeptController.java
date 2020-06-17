package com.chouxiaozi.vhr.controller.system.basic;

import com.chouxiaozi.model.Department;
import com.chouxiaozi.service.DeptService;
import com.chouxiaozi.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 部分管理
 * @Author Administrator
 * @Date 2020/5/1513:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/")
    public List<Department> getDeptTree(){
        return deptService.getDeptTree();
    }

    @PostMapping("/")
    public RespBean addDept(@RequestBody Department department){
        return deptService.addDept(department);
    }

    @DeleteMapping("/{id}")
    public RespBean delDept(@PathVariable Integer id){
        Department department = new Department();
        department.setId(id);
        return deptService.delDept(department);
    }

    @GetMapping("/list")
    public List<Department> getDeptList(){
        return deptService.getDeptList();
    }

}
