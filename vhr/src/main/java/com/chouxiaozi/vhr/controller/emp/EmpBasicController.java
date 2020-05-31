package com.chouxiaozi.vhr.controller.emp;

import com.chouxiaozi.vhr.service.EmployeeService;
import com.chouxiaozi.vhr.vo.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 员工-基础资料
 */
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public RespPageBean listEmployeesByPage(@RequestParam(defaultValue = "1") Integer curPage
            , @RequestParam(defaultValue = "10")Integer size, String keyword){
        return employeeService.listEmployeesByPage(curPage, size, keyword);
    }
}
