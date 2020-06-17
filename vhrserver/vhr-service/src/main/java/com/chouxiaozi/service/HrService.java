package com.chouxiaozi.service;

import com.chouxiaozi.mapper.HrMapper;
import com.chouxiaozi.model.Hr;
import com.chouxiaozi.model.Role;
import com.chouxiaozi.util.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    private HrMapper hrMapper;

    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获取用户
        Hr hr = hrMapper.loadUserByUsername(username);
        if(null == hr){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        List<Role> roles = hrMapper.getRoles(hr.getId());
        hr.setRoles(roles);
        return hr;
    }

    public List<Hr> listHrs(String keyword) {
        return hrMapper.listHrs(HrUtil.getCurrentHr().getId(),keyword);
    }

    public int updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    public int deleteHr(Integer hrId) {
        return hrMapper.deleteByPrimaryKey(hrId);
    }
}
