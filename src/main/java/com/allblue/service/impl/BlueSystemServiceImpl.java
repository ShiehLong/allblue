package com.allblue.service.impl;

import com.allblue.mapper.BlueSystemMapper;
import com.allblue.model.BlueSystem;
import com.allblue.model.dto.ZTreeNode;
import com.allblue.service.BlueSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author Xone
 * @Date 17:39 2018/11/26
 **/
@Service
public class BlueSystemServiceImpl implements BlueSystemService {

    @Autowired
    private BlueSystemMapper blueSystemMapper;

    @Override
    public List<ZTreeNode> getSystemList() {
        return blueSystemMapper.selectSystemList();
    }

    @Override
    public int add(BlueSystem blueSystem) {
        blueSystemMapper.insert(blueSystem);
        return blueSystem.getId();
    }

    @Override
    public BlueSystem getSystemInfo(String code) {
        BlueSystem blueSystem = new BlueSystem();
        blueSystem.setCode(code);
        blueSystem = blueSystemMapper.selectSystemInfo(blueSystem);
        return blueSystem;
    }

    @Override
    public int update(BlueSystem blueSystem) {
        return blueSystemMapper.updateByCode(blueSystem);
    }

    @Override
    public void delete(String code) {
        BlueSystem blueSystem = new BlueSystem();
        blueSystem.setCode(code);
        blueSystem.setStatus(0);
        blueSystemMapper.updateByCode(blueSystem);
    }

    @Override
    public List<ZTreeNode> getAllSystem() {
        return blueSystemMapper.selectAllSystem();
    }

    @Override
    public List<String> getListByParentCode(String code) {
        return blueSystemMapper.selectListByParentCode(code);
    }
}
