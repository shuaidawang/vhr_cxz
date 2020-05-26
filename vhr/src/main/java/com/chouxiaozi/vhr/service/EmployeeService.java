package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.EmployeeMapper;
import com.chouxiaozi.vhr.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    public List<Employee> listEmployees(String keyword) {
        return employeeMapper.listEmployees(keyword);
    }
}
