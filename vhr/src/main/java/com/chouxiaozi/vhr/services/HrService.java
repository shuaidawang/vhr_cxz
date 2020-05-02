package com.chouxiaozi.vhr.services;

import com.chouxiaozi.vhr.mapper.HrMapper;
import com.chouxiaozi.vhr.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrService {
    @Autowired
    private HrMapper hrMapper;

    public Hr selectUserByUsername(String username){
        return hrMapper.loadUserByUsername(username);
    }
}
