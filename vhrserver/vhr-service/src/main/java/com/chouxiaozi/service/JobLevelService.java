package com.chouxiaozi.service;

import com.chouxiaozi.denum.TitleLevelEnum;
import com.chouxiaozi.mapper.JobLevelMapper;
import com.chouxiaozi.model.JobLevel;
import com.chouxiaozi.model.JobLevelExample;
import com.chouxiaozi.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelService{
    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getJobLevels(Boolean enable) {
        JobLevelExample example = new JobLevelExample();
        JobLevelExample.Criteria criteria = example.createCriteria();
        if(null != enable){
            criteria.andEnabledEqualTo(enable);
        }
        return jobLevelMapper.selectByExample(example);
    }

    public List<String> getTitleLevels() {
        return TitleLevelEnum.getTitleLevels();
    }

    public RespBean addJobLevel(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        if(1== jobLevelMapper.insertSelective(jobLevel)){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
    public RespBean updateJobLevel(JobLevel jobLevel) {
        if(1== jobLevelMapper.updateByPrimaryKeySelective(jobLevel)){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    public RespBean delJobLevelById(Integer id) {
        if(1== jobLevelMapper.deleteByPrimaryKey(id)){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    public RespBean delJobLevelByIds(Integer[] ids) {
        if(ids.length == jobLevelMapper.delJobLevelByIds(ids)){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}