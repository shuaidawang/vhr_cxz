package com.chouxiaozi.vhr.mapper;

import com.chouxiaozi.vhr.model.HrRole;
import com.chouxiaozi.vhr.model.HrRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrRoleMapper {
    int countByExample(HrRoleExample example);

    int deleteByExample(HrRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    List<HrRole> selectByExample(HrRoleExample example);

    HrRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HrRole record, @Param("example") HrRoleExample example);

    int updateByExample(@Param("record") HrRole record, @Param("example") HrRoleExample example);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    int batchInsert(@Param("hrId") Integer hrId, @Param("roles") Integer[] roles);
}