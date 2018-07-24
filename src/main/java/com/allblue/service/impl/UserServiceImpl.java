package com.allblue.service.impl;

import com.allblue.mapper.BlueUserMapper;
import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author Xone
 * @Date 11:47 2018/7/20
 **/
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlueUserMapper blueUserMapper;

    @Override
    public int add(BlueUser userInfo) {
        blueUserMapper.insert(userInfo);
        int id = userInfo.getId();
        logger.info("用户注册ID:" + id);
        return id;
    }

    @Override
    public int getId(BlueUser userInfo) {
        int id = blueUserMapper.selectByUserInfo(userInfo);
        logger.info("用户登录ID：" + id);
        return id;
    }
}
