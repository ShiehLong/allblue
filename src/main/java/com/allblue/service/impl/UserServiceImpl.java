package com.allblue.service.impl;

import com.allblue.mapper.BlueUserMapper;
import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author Xone
 * @Date 11:47 2018/7/20
 **/
@Service
public class UserServiceImpl implements UserService {


    private BlueUserMapper blueUserMapper;

    @Autowired
    public UserServiceImpl(BlueUserMapper blueUserMapper) {
        this.blueUserMapper = blueUserMapper;
    }

    @Override
    public void userRegister(BlueUser userInfo) {
        blueUserMapper.insert(userInfo);
    }

    @Override
    public String userLogin(BlueUser userInfo) {
        return null;
    }
}
