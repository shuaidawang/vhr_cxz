package com.chouxiaozi.service;

import com.chouxiaozi.mapper.DepartmentMapper;
import com.chouxiaozi.mapper.EmployeeMapper;
import com.chouxiaozi.mapper.JobLevelMapper;
import com.chouxiaozi.mapper.NationMapper;
import com.chouxiaozi.mapper.PoliticsstatusMapper;
import com.chouxiaozi.mapper.PositionMapper;
import com.chouxiaozi.model.Department;
import com.chouxiaozi.model.DepartmentExample;
import com.chouxiaozi.model.Employee;
import com.chouxiaozi.model.JobLevel;
import com.chouxiaozi.model.JobLevelExample;
import com.chouxiaozi.model.Nation;
import com.chouxiaozi.model.NationExample;
import com.chouxiaozi.model.Politicsstatus;
import com.chouxiaozi.model.PoliticsstatusExample;
import com.chouxiaozi.model.Position;
import com.chouxiaozi.model.PositionExample;
import com.chouxiaozi.util.DateUtil;
import com.chouxiaozi.vo.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    PoliticsstatusMapper politicsstatusMapper;
    @Autowired
    NationMapper nationMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    JobLevelMapper jobLevelMapper;

    public RespPageBean listEmployeesByPage(Integer curPage, Integer size, Employee employee, Date[] beginDateScope) {
        RespPageBean bean = new RespPageBean();
        bean.setData(employeeMapper.listEmployeesByPage(curPage, size, employee, beginDateScope));
        bean.setTotal(employeeMapper.getTotal(employee,beginDateScope));
        return bean;
    }

    public int add(Employee employee) {
        employee.setContractTerm(DateUtil.getBetweenYears(employee.getBeginContract(),employee.getEndContract()));
        return employeeMapper.insertSelective(employee);
    }

    public int update(Employee employee) {
        employee.setContractTerm(DateUtil.getBetweenYears(employee.getBeginContract(),employee.getEndContract()));
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

    public int batchImport(List<Employee> list) {
        for (Employee employee : list) {
            String name = employee.getNation().getName();
            NationExample example = new NationExample();
            example.createCriteria().andNameEqualTo(name);
            List<Nation> nations = nationMapper.selectByExample(example);
            employee.setNationId(nations.get(0).getId());
            ///
            name = employee.getPoliticsstatus().getName();
            PoliticsstatusExample example1 = new PoliticsstatusExample();
            example1.createCriteria().andNameEqualTo(name);
            List<Politicsstatus> politicsstatuses = politicsstatusMapper.selectByExample(example1);
            employee.setPoliticId(politicsstatuses.get(0).getId());
            ////
            name = employee.getPosition().getName();
            PositionExample example2 = new PositionExample();
            example2.createCriteria().andNameEqualTo(name);
            List<Position> positions = positionMapper.selectByExample(example2);
            employee.setPosId(positions.get(0).getId());
            /////
            name = employee.getDepartment().getName();
            DepartmentExample example3 = new DepartmentExample();
            example3.createCriteria().andNameEqualTo(name);
            List<Department> departments = departmentMapper.selectByExample(example3);
            employee.setDepartmentId(departments.get(0).getId());
            ////
            name = employee.getJobLevel().getName();
            JobLevelExample example4 = new JobLevelExample();
            example4.createCriteria().andNameEqualTo(name);
            List<JobLevel> jobLevels = jobLevelMapper.selectByExample(example4);
            employee.setJobLevelId(jobLevels.get(0).getId());
        }
        return employeeMapper.batchInsert(list);
    }
}
