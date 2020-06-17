package com.chouxiaozi.mapper;

import com.chouxiaozi.model.JobLevel;
import com.chouxiaozi.model.JobLevelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobLevelMapper {
    int countByExample(JobLevelExample example);

    int deleteByExample(JobLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    List<JobLevel> selectByExample(JobLevelExample example);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobLevel record, @Param("example") JobLevelExample example);

    int updateByExample(@Param("record") JobLevel record, @Param("example") JobLevelExample example);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    int delJobLevelByIds(@Param("ids") Integer[] ids);
}