package com.chouxiaozi.vhr.mapper;

import com.chouxiaozi.vhr.model.Hr;
import com.chouxiaozi.vhr.model.HrExample;
import com.chouxiaozi.vhr.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int countByExample(HrExample example);

    int deleteByExample(HrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    List<Hr> selectByExample(HrExample example);

    Hr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hr record, @Param("example") HrExample example);

    int updateByExample(@Param("record") Hr record, @Param("example") HrExample example);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getRoles(Integer hrId);

    List<Hr> listHrs(@Param("hrId") Integer hrId,@Param("keyword") String keyword);

    List<Hr> getOtherHrs(Integer id);
}