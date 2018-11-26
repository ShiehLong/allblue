package com.allblue.service;

import com.allblue.model.BlueSystem;

import java.util.List;

/**
 * @Description:
 * @Author Xone
 * @Date 17:38 2018/11/26
 **/
public interface BlueSystemService {
    /**
     * @Description: 查询系统列表
     * @Author Xone
     * @Date 20:53 2018/11/26
     **/
    List<BlueSystem> getSystemList();

    /**
     * @Description: 获取系统信息
     * @Author Xone
     * @Date 20:53 2018/11/26
     **/
    BlueSystem getSystemInfo(String code);

    /**
     * @Description: 获取系统信息
     * @Author Xone
     * @Date 20:53 2018/11/26
     **/
    BlueSystem getSystemInfo(int id);

    /**
     * @Description: 新增系统
     * @Author Xone
     * @Date 20:53 2018/11/26
     **/
    int add(BlueSystem blueSystem);

    /**
     * @Description: 更新系统
     * @Author Xone
     * @Date 20:53 2018/11/26
     **/
    int update(BlueSystem blueSystem);

    /**
     * @Description: 删除系统(置位无效状态)
     * @Author Xone
     * @Date 20:53 2018/11/26
     **/
    void delete(int id);


    /**
     * @Description: 获取系统列表
     * @Author Xone
     * @Date 22:53 2018/11/26
     **/
    List<BlueSystem> getAllSystem();
}
