package com.chouxiaozi.vhr.mapper;

import com.chouxiaozi.vhr.model.HrHaoYong;
import com.chouxiaozi.vhr.model.HrHaoYongExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrHaoYongMapper {
    int countByExample(HrHaoYongExample example);

    int deleteByExample(HrHaoYongExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HrHaoYong record);

    int insertSelective(HrHaoYong record);

    List<HrHaoYong> selectByExample(HrHaoYongExample example);

    HrHaoYong selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HrHaoYong record, @Param("example") HrHaoYongExample example);

    int updateByExample(@Param("record") HrHaoYong record, @Param("example") HrHaoYongExample example);

    int updateByPrimaryKeySelective(HrHaoYong record);

    int updateByPrimaryKey(HrHaoYong record);
}