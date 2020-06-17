package com.chouxiaozi.vhr.controller.system.basic;

import com.chouxiaozi.model.JobLevel;
import com.chouxiaozi.service.JobLevelService;
import com.chouxiaozi.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 职称管理
 *
 * @Author https://blog.csdn.net/shuai8624
 * @Date 2020/5/11 15:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getJobLevels() {
        return jobLevelService.getJobLevels(null);
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
        return jobLevelService.addJobLevel(jobLevel);
    }

    @GetMapping("/titleLevels")
    public List<String> getTitleLevels() {
        return jobLevelService.getTitleLevels();
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel) {
        return jobLevelService.updateJobLevel(jobLevel);
    }

    @DeleteMapping("/{id}")
    public RespBean delJobLevelById(@PathVariable("id") Integer id) {
        return jobLevelService.delJobLevelById(id);
    }

    @DeleteMapping("/")
    public RespBean delJobLevelByIds(@RequestBody Integer[] ids) {
        return jobLevelService.delJobLevelByIds(ids);
    }
}
