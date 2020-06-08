package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.EmployeeMapper;
import com.chouxiaozi.vhr.mapper.NationMapper;
import com.chouxiaozi.vhr.mapper.PoliticsstatusMapper;
import com.chouxiaozi.vhr.model.Employee;
import com.chouxiaozi.vhr.model.Nation;
import com.chouxiaozi.vhr.model.Politicsstatus;
import com.chouxiaozi.vhr.model.PoliticsstatusExample;
import com.chouxiaozi.vhr.vo.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    PoliticsstatusMapper politicsstatusMapper;
    @Autowired
    NationMapper nationMapper;

    public RespPageBean listEmployeesByPage(Integer curPage, Integer size, String keyword) {
        RespPageBean bean = new RespPageBean();
        if (null == curPage) {
            curPage = 1;
        }
        if (null == size) {
            size = 10;
        }
        curPage = (curPage - 1) * size;
        bean.setData(employeeMapper.listEmployeesByPage(curPage, size, keyword));
        bean.setTotal(employeeMapper.getTotal(keyword));
        return bean;
    }

    public int add(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }

    public int update(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public int delete(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public List<Politicsstatus> listPoliticsStatus() {
        return politicsstatusMapper.selectByExample(new PoliticsstatusExample());
    }

    public List<Nation> listNations() {
        return nationMapper.selectByExample(null);
    }

    public Integer getMaxWorkID() {
        return employeeMapper.getMaxWorkID();
    }

}
