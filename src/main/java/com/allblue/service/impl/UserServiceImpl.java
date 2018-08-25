package com.allblue.service.impl;

import com.allblue.mapper.BlueUserMapper;
import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
    public int getUserId(BlueUser userInfo) {
        userInfo = blueUserMapper.selectUserInfo(userInfo);
        int id = StringUtils.isEmpty(userInfo) ? 0 : userInfo.getId();
        logger.info("用户登录ID：" + id);
        return id;
    }

    @Override
    public BlueUser getUserInfo(String username) {
        BlueUser userInfo = new BlueUser();
        userInfo.setUsername(username);
        userInfo = blueUserMapper.selectUserInfo(userInfo);
        logger.info("用户信息【" + userInfo + "】");
        return userInfo;
    }

    @Override
    public BlueUser getUserInfo(int id) {
        BlueUser userInfo = new BlueUser();
        userInfo.setId(id);
        userInfo = blueUserMapper.selectUserInfo(userInfo);
        logger.info("用户信息【" + userInfo + "】");
        return userInfo;
    }

    @Override
    public List<BlueUser> getUserList() {
        return blueUserMapper.selectUserList();
    }
}
