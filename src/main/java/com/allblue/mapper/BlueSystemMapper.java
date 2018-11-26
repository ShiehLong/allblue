package com.allblue.mapper;

import com.allblue.model.BlueSystem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author Xone
 * @Date 17:38 2018/11/26
 **/
@Repository
public interface BlueSystemMapper {
    /**
     * @Description: 获取系统列表
     * @Author Xone
     * @Date 21:08 2018/11/26
     **/
    List<BlueSystem> selectSystemList();

    /**
     * @Description: 插入系统数据
     * @Author Xone
     * @Date 21:08 2018/11/26
     **/
    void insert(BlueSystem blueSystem);

    /**
     * @Description: 获取系统信息
     * @Author Xone
     * @Date 21:08 2018/11/26
     **/
    BlueSystem selectSystemInfo(BlueSystem blueSystem);

    /**
     * @Description: 获取所有系统列表
     * @Author Xone
     * @Date 21:08 2018/11/26
     **/
    List<BlueSystem> selectAllSystem();
}
