package com.chouxiaozi.vhr.controller.emp;

import com.chouxiaozi.vhr.mapper.PoliticsstatusMapper;
import com.chouxiaozi.vhr.model.Department;
import com.chouxiaozi.vhr.model.Employee;
import com.chouxiaozi.vhr.model.JobLevel;
import com.chouxiaozi.vhr.model.Nation;
import com.chouxiaozi.vhr.model.Politicsstatus;
import com.chouxiaozi.vhr.model.PoliticsstatusExample;
import com.chouxiaozi.vhr.model.Position;
import com.chouxiaozi.vhr.service.DeptService;
import com.chouxiaozi.vhr.service.EmployeeService;
import com.chouxiaozi.vhr.service.JobLevelService;
import com.chouxiaozi.vhr.service.PositionService;
import com.chouxiaozi.vhr.vo.RespBean;
import com.chouxiaozi.vhr.vo.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 员工-基础资料
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PositionService positionService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    DeptService deptService;


    @GetMapping("/")
    public RespPageBean listEmployeesByPage(@RequestParam(defaultValue = "1") Integer curPage
            , @RequestParam(defaultValue = "10") Integer size, String keyword) {
        return employeeService.listEmployeesByPage(curPage, size, keyword);
    }

    @PostMapping("/")
    public RespBean add(@RequestBody Employee employee){
        if(1 == employeeService.add(employee)){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean update(@RequestBody Employee employee){
        if(1 == employeeService.update(employee)){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean update(@PathVariable("id") Integer id){
        if(1 == employeeService.delete(id)){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    //政治面貌
    @GetMapping("/listPoliticsStatus")
    public List<Politicsstatus> listPoliticsStatus(){
        return employeeService.listPoliticsStatus();
    }
    //民族
    @GetMapping("/listNations")
    public List<Nation> listNations(){
        return employeeService.listNations();
    }
    //职位
    @GetMapping("/listPositions")
    public List<Position> listPositions(){
        return positionService.getAllPosition(true);
    }
    //职称
    @GetMapping("/listJobLevels")
    public List<JobLevel> listJobLevels(){
        return jobLevelService.getJobLevels(true);
    }
    //工号
    @GetMapping("/getMaxWorkID")
    public String getMaxWorkID(){
        return String.format("%08d",employeeService.getMaxWorkID()+1);
    }
    @GetMapping("/listAllDeps")
    public List<Department> listAllDeps(){
        return deptService.getDeptTree();
    }
}
