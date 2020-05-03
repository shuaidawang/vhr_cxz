package com.chouxiaozi.vhr.utils;

import com.chouxiaozi.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtil {
    public static Hr getCurrentHr(){
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
