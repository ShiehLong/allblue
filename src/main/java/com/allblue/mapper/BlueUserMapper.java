package com.allblue.mapper;

import com.allblue.model.BlueUser;
import com.allblue.model.BlueUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlueUserMapper {

    int countByExample(BlueUserExample example);

    int deleteByExample(BlueUserExample example);

    int deleteByPrimaryKey(Integer id);

    /**
    * @Description:插入用户信息
    * @Author Xone
    * @Date 14:08 2018/8/25
    **/
    int insert(BlueUser record);

    int insertSelective(BlueUser record);

    /**
     * @Description:根据用户信息查询用户
     * @Author Xone
     * @Date 17:00 2018/8/6
     **/
    BlueUser selectUserInfo(BlueUser userInfo);

    List<BlueUser> selectByExample(BlueUserExample example);

    BlueUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlueUser record, @Param("example") BlueUserExample example);

    int updateByExample(@Param("record") BlueUser record, @Param("example") BlueUserExample example);

    int updateByPrimaryKeySelective(BlueUser record);

    int updateByPrimaryKey(BlueUser record);

    /**
    * @Description:查询所有用户信息
    * @Author Xone
    * @Date 14:08 2018/8/25
    **/
    List<BlueUser> selectUserList();
}