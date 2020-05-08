package com.chouxiaozi.vhr.exception;

import com.chouxiaozi.vhr.vo.RespBean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理类
 * @Date 2020-05-08
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    private RespBean sqlException(SQLException e){
        if(e instanceof MySQLIntegrityConstraintViolationException){
            if(e.getMessage().contains("Cannot delete or update a parent row: a foreign key")){
                return RespBean.error("该数据有关联数据，操作失败!");
            }else if(e.getMessage().contains("Duplicate entry")){
                return RespBean.error("该数据已存在，操作失败!");
            }
        }
        return RespBean.error("数据库异常，操作失败!");
    }
}
