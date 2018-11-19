package com.allblue.mapper;

import com.allblue.model.BlueRole;
import com.allblue.model.dto.SearchDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlueRoleMapper {
    /**
     * @Description:插入角色信息
     * @Author Xone
     * @Date 14:08 2018/8/25
     **/
    int insert(BlueRole userInfo);

    /**
     * @Description:根据角色信息查询
     * @Author Xone
     * @Date 17:00 2018/8/6
     **/
    BlueRole selectRoleInfo(BlueRole blueInfo);

    /**
     * @Description:更新角色信息
     * @Author Xone
     * @Date 21:20 2018/8/25
     **/
    int updateById(BlueRole userInfo);

    /**
     * @Description:根据条件模糊查询数据条数
     * @Author Xone
     * @Date 15:07 2018/11/9
     **/
    int selectRoleTotalCount(String opts);

    /**
     * @Description:根据条件模糊查询
     * @Author Xone
     * @Date 15:07 2018/11/9
     **/
    List<BlueRole> selectRoleList(SearchDTO searchDTO);
}
