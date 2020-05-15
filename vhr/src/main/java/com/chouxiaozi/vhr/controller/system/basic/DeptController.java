package com.chouxiaozi.vhr.controller.system.basic;

import com.chouxiaozi.vhr.model.Department;
import com.chouxiaozi.vhr.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
