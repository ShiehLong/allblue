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
    * @Description:
    * @Author Xone
    * @Date 11:43 2018/7/20
    **/
    int getId(BlueUser userInfo);
}

