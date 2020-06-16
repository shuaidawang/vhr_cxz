package com.chouxiaozi.vhr.mapper;

import com.chouxiaozi.vhr.model.Employee;
import com.chouxiaozi.vhr.model.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> listEmployeesByPage(@Param("curPage") Integer curPage, @Param("size") Integer size, @Param("keyword") String keyword);

    Long getTotal(String keyword);

    Integer getMaxWorkID();

    int batchInsert(List<Employee> list);
}