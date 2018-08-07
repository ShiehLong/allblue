package com.allblue.service;

import com.allblue.model.BlueUser;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author Xone
 * @Date 11:34 2018/7/20
 **/
public interface UserService {
    /**
     * @Description:注册会员
     * @Author Xone
     * @Date 11:40 2018/7/20
     **/
    int add(BlueUser userInfo);

    /**
     * @Description:根据会员信息查询
     * @Author Xone
     * @Date 11:43 2018/7/20
     **/
    int getUserId(BlueUser userInfo);

    /**
     * @Description:根据用户名查询会员信息
     * @Author Xone
     * @Date 17:02 2018/8/6
     **/
    BlueUser getUserInfo(String username);
}

