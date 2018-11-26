package com.allblue.controller;

import com.allblue.model.BlueSystem;
import com.allblue.model.BlueUser;
import com.allblue.model.dto.ResultInfo;
import com.allblue.service.BlueSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author Xone
 * @Date 17:37 2018/11/26
 **/
@Controller
@RequestMapping("/system")
public class BlueSystemController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlueSystemService blueSystemService;

    @RequestMapping(value = "/getSystemList", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getSystemList() {
        //查询系统列表
        List<BlueSystem> list = blueSystemService.getSystemList();
        if (list == null) return ResultInfo.error("系统数据为空");
        return ResultInfo.success("系统数据获取成功",list);
    }

    @RequestMapping(value = "/getAllSystem", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getAllSystem() {
        //查询系统列表
        List<BlueSystem> list = blueSystemService.getAllSystem();
        if (list == null) return ResultInfo.error("系统数据为空");
        return ResultInfo.success("系统数据获取成功",list);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo save(@RequestParam(value = "code") String code,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "url") String url,
                           @RequestParam(value = "parent_code") String parent_code,
                           @RequestParam(value = "remark") String remark,
                           HttpSession session) {
        //判断系统名是否已存在
        BlueSystem isExist = blueSystemService.getSystemInfo(code);
        if (null != isExist) {
            return ResultInfo.error("系统已存在！");
        } else {
            //获取session内当前操作用户名
            BlueUser blueUser = (BlueUser) session.getAttribute("blueUser");

            int level = 1;
            int sort_id = 1;
            BlueSystem blueSystem = new BlueSystem();
            blueSystem.setCode(code);
            blueSystem.setName(name);
            blueSystem.setUrl(url);
            blueSystem.setLevel(level);
            blueSystem.setParent_code(parent_code);
            blueSystem.setSort_id(sort_id);
            blueSystem.setCreator(blueUser.getName());
            blueSystem.setModifier(blueUser.getName());
            blueSystem.setRemark(remark);
            //插入数据库
            int id = blueSystemService.add(blueSystem);
            if (id == 0) {
                return ResultInfo.error("新增系统失败！");
            }
            return ResultInfo.success("新建系统【" + name + "】成功!");
        }
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo detail(@PathVariable("id") int id) {
        if (id == 0) {
            return ResultInfo.error("系统ID不正确！");
        }
        BlueSystem systemInfo = blueSystemService.getSystemInfo(id);
        if (systemInfo == null) {
            return ResultInfo.error("系统信息不存在！");
        }
        logger.info("查询系统【" + id + "】成功");
        return ResultInfo.success("SUCCESS", systemInfo);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo update(@PathVariable("id") int id,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "status", required = false) Integer status,
                             @RequestParam(value = "remark", required = false) String remark,
                             HttpSession session) {
        //入参判断
        if (id == 0) {
            return ResultInfo.error("系统ID不正确！");
        }
        if ((name == null || "".equals(name)) &&
                (remark == null || "".equals(remark)) &&
                (status == null)) {
            return ResultInfo.error("请填写要修改的信息!!!");
        }

        BlueSystem BlueSystem = new BlueSystem();
        BlueSystem.setId(id);
        if (name != null && !"".equals(name)) {
            BlueSystem.setName(name);
        }
        if (remark != null && !"".equals(remark)) {
            BlueSystem.setRemark(remark);
        }
        if (status == 0 || status == 1) {
            BlueSystem.setStatus(status);
        }
        BlueUser blueUser = (BlueUser) session.getAttribute("blueUser");
        BlueSystem.setModifier(blueUser.getName());

        //update数据库
        int count = blueSystemService.update(BlueSystem);
        if (count != 0) {
            return ResultInfo.success("更新系统【" + id + "】信息成功！");
        } else {
            return ResultInfo.error("修改失败,请重试！");
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo deleteUser(@PathVariable("id") int id) {
        if (id == 0) {
            return ResultInfo.error("系统ID不正确！");
        }
        blueSystemService.delete(id);
        return ResultInfo.success("删除系统【" + id + "】成功！");
    }

}
