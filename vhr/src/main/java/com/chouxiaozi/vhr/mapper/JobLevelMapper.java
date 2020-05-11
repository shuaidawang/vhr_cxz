package com.chouxiaozi.vhr.mapper;

import com.chouxiaozi.vhr.model.JobLevel;
import com.chouxiaozi.vhr.model.JobLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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