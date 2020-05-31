package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.EmployeeMapper;
import com.chouxiaozi.vhr.vo.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public RespPageBean listEmployeesByPage(Integer curPage, Integer size, String keyword) {
        RespPageBean bean = new RespPageBean();
        if (null == curPage) {
            curPage = 1;
        }
        if (null == size) {
            size = 10;
        }
        curPage = (curPage - 1) * curPage;
        bean.setData(employeeMapper.listEmployeesByPage(curPage, size, keyword));
        bean.setTotal(employeeMapper.getTotal(keyword));
        return bean;
    }
}
