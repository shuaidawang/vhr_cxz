package com.chouxiaozi.vhr.controller.emp;

import com.chouxiaozi.vhr.model.Employee;
import com.chouxiaozi.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp/basic")
public class BasicController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public List<Employee> listEmployees(String keyword){
        return employeeService.listEmployees(keyword);
    }
}
