package com.allblue.mapper;

import com.allblue.model.BlueUser;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BlueUserMapper {

    int deleteByPrimaryKey(Integer id);

    /**
    * @Description:插入用户信息
    * @Author Xone
    * @Date 14:08 2018/8/25
    **/
    int insert(BlueUser userInfo);

    int insertSelective(BlueUser record);

    /**
     * @Description:根据用户信息查询用户
     * @Author Xone
     * @Date 17:00 2018/8/6
     **/
    BlueUser selectUserInfo(BlueUser userInfo);

    BlueUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlueUser record);

    int updateByPrimaryKey(BlueUser record);

    /**
    * @Description:查询所有用户信息
    * @Author Xone
    * @Date 14:08 2018/8/25
    **/
    List<BlueUser> selectUserList();

    /**
    * @Description:更新用户信息
    * @Author Xone
    * @Date 21:20 2018/8/25
    **/
    int updateById(BlueUser userInfo);

}