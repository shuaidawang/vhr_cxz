package com.chouxiaozi.vhr.service;

import com.chouxiaozi.vhr.mapper.DepartmentMapper;
import com.chouxiaozi.vhr.mapper.EmployeeMapper;
import com.chouxiaozi.vhr.mapper.JobLevelMapper;
import com.chouxiaozi.vhr.mapper.NationMapper;
import com.chouxiaozi.vhr.mapper.PoliticsstatusMapper;
import com.chouxiaozi.vhr.mapper.PositionMapper;
import com.chouxiaozi.vhr.model.Department;
import com.chouxiaozi.vhr.model.DepartmentExample;
import com.chouxiaozi.vhr.model.Employee;
import com.chouxiaozi.vhr.model.JobLevel;
import com.chouxiaozi.vhr.model.JobLevelExample;
import com.chouxiaozi.vhr.model.MailConstants;
import com.chouxiaozi.vhr.model.Nation;
import com.chouxiaozi.vhr.model.NationExample;
import com.chouxiaozi.vhr.model.Politicsstatus;
import com.chouxiaozi.vhr.model.PoliticsstatusExample;
import com.chouxiaozi.vhr.model.Position;
import com.chouxiaozi.vhr.model.PositionExample;
import com.chouxiaozi.vhr.util.DateUtil;
import com.chouxiaozi.vhr.vo.RespPageBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    RabbitTemplate rabbitTemplate;

    public RespPageBean listEmployeesByPage(Integer curPage, Integer size, Employee employee, Date[] beginDateScope) {
        RespPageBean bean = new RespPageBean();
        bean.setData(employeeMapper.listEmployeesByPage(curPage, size, employee, beginDateScope));
        bean.setTotal(employeeMapper.getTotal(employee,beginDateScope));
        return bean;
    }

    public int add(Employee employee) {
        employee.setContractTerm(DateUtil.getBetweenYears(employee.getBeginContract(),employee.getEndContract()));
        int result = employeeMapper.insertSelective(employee);
        if(result == 1){
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            //生成消息的唯一id
            /*String msgId = UUID.randomUUID().toString();
            MailSendLog mailSendLog = new MailSendLog();
            mailSendLog.setMsgId(msgId);
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setEmpId(emp.getId());
            mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));*/
            //mailSendLogService.insert(mailSendLog);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp);
        }
        return result;
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
