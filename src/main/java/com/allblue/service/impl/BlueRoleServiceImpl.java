package com.allblue.service.impl;

import com.allblue.mapper.BlueRoleMapper;
import com.allblue.model.BlueRole;
import com.allblue.model.dto.SearchDTO;
import com.allblue.service.BlueRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlueRoleServiceImpl implements BlueRoleService {

    @Autowired
    private BlueRoleMapper blueRoleMapper;

    @Override
    public List<BlueRole> getRoleList(SearchDTO searchDTO) {
        return blueRoleMapper.selectRoleList(searchDTO);
    }

    @Override
    public int add(BlueRole blueRole) {
        blueRoleMapper.insert(blueRole);
        return blueRole.getId();
    }

    @Override
    public BlueRole getRoleInfo(int id) {
        BlueRole roleInfo = new BlueRole();
        roleInfo.setId(id);
        roleInfo = blueRoleMapper.selectRoleInfo(roleInfo);
        return roleInfo;
    }

    @Override
    public BlueRole getRoleInfo(String name) {
        BlueRole roleInfo = new BlueRole();
        roleInfo.setName(name);
        roleInfo = blueRoleMapper.selectRoleInfo(roleInfo);
        return roleInfo;
    }

    @Override
    public int delete(int id) {
        BlueRole roleInfo = new BlueRole();
        roleInfo.setId(id);
        roleInfo.setStatus(0);
        int count = blueRoleMapper.updateById(roleInfo);
        return count;
    }

    @Override
    public int getRoleTotalCount(String opts) {
        int count = blueRoleMapper.selectRoleTotalCount(opts);
        return count;
    }

    @Override
    public int update(BlueRole blueRole) {
        int count = blueRoleMapper.updateById(blueRole);
        return count;
    }
}
