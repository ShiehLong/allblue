package com.allblue.service;

import com.allblue.model.BlueRole;
import com.allblue.model.dto.SearchDTO;

import java.util.List;

public interface BlueRoleService {

    /**
     * @Description:根据条件模糊查询角色列表
     * @Author Xone
     * @Date 14:53 2018/11/9
     **/
    List<BlueRole> getRoleList(SearchDTO searchDTO);

    /**
     * @Description:注册会员
     * @Author Xone
     * @Date 11:40 2018/7/20
     **/
    int add(BlueRole blueRole);

    /**
     * @Description:根据角色ID查询信息
     * @Author Xone
     * @Date 15:56 2018/8/25
     **/
    BlueRole getRoleInfo(int id);

    /**
     * @Description:根据角色ID查询信息
     * @Author Xone
     * @Date 15:56 2018/8/25
     **/
    BlueRole getRoleInfo(String name);

    /**
     * @Description:删除角色（置为无效）
     * @Author Xone
     * @Date 20:59 2018/11/5
     **/
    int delete(int id);

    /**
     * @Description:根据条件模糊查询角色列表
     * @Author Xone
     * @Date 14:53 2018/11/9
     **/
    int getRoleTotalCount(String opts);

    /**
     * @Description:更新信息
     * @Author Xone
     * @Date 21:13 2018/8/25
     **/
    int update(BlueRole blueRole);
}
