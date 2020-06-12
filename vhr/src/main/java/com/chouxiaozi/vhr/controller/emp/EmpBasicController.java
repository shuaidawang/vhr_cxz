package com.chouxiaozi.vhr.controller.emp;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
        if (null == curPage) {
            curPage = 1;
        }
        if (null == size) {
            size = 10;
        }
        curPage = (curPage - 1) * size;
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

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(){
        RespPageBean respPageBean = employeeService.listEmployeesByPage(null, null, null);
        HttpHeaders headers = new HttpHeaders();
        byte[] baos = null;
        if(null != respPageBean.getTotal() && respPageBean.getTotal() > 0){
            List<Employee> list = (List<Employee>)respPageBean.getData();
            // 写法1
            String name =  "员工信息表" + System.currentTimeMillis();
            String fileName = "E://"+name+ ".xlsx";
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            // 如果这里想使用03 则 传入excelType参数即可
            EasyExcel.write(fileName, Employee.class).sheet("员工信息表").doWrite(list);
            try {
                headers.setContentDispositionFormData("attachment", new String((name+".xlsx").getBytes("UTF-8"), "ISO-8859-1"));
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                InputStream fileInputStream = new FileInputStream(fileName);
                baos = new byte[fileInputStream.available()];
                fileInputStream.read(baos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<byte[]>(baos, headers, HttpStatus.CREATED);
    }
}
