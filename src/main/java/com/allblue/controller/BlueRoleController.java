package com.allblue.controller;

import com.allblue.model.BlueRole;
import com.allblue.model.BlueUser;
import com.allblue.model.dto.ResultInfo;
import com.allblue.model.dto.SearchDTO;
import com.allblue.service.BlueRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/blueRole")
public class BlueRoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlueRoleService blueRoleService;

    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getUserListBySearchDTO(
            @RequestParam(value = "searchContext", required = false) String opts,
            @RequestParam(value = "sort", required = false) String sortName,
            @RequestParam(value = "order", required = false) String sortOrder,
            @RequestParam(value = "offset", required = false) Integer pageNumber,
            @RequestParam(value = "limit", required = false) Integer pageSize) {

        //获取角色数量
        int totalCount = blueRoleService.getRoleTotalCount(opts);
        if (totalCount > 0) {
            //设置参数
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setOffset(pageNumber);
            searchDTO.setLimit(pageSize);
            searchDTO.setSearchContext(opts);
            searchDTO.setSortName(sortName);
            searchDTO.setSortOrder(sortOrder);

            //获取符合条件的角色列表
            List<BlueRole> list = blueRoleService.getRoleList(searchDTO);

            return ResultInfo.success(Integer.toString(totalCount), list);
        }
        return ResultInfo.error("角色数量<0!");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo save(@RequestParam(value = "name") String name,
                           @RequestParam(value = "remark") String remark,
                           HttpSession session) {
        //判断角色名是否已存在
        BlueRole isExist = blueRoleService.getRoleInfo(name);
        if (null != isExist) {
            return ResultInfo.error("角色已存在！");
        } else {
            //获取session内当前操作用户名
            BlueUser blueUser = (BlueUser) session.getAttribute("blueUser");

            BlueRole blueRole = new BlueRole();
            blueRole.setName(name);
            blueRole.setCreator(blueUser.getName());
            blueRole.setModifier(blueUser.getName());
            blueRole.setRemark(remark);
            //插入数据库
            int id = blueRoleService.add(blueRole);
            if (id == 0) {
                return ResultInfo.error("新增角色失败！");
            }
            return ResultInfo.success("新建角色【" + name + "】成功!");
        }
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo detail(@PathVariable("id") int id) {
        if (id == 0) {
            return ResultInfo.error("角色ID不正确！");
        }
        BlueRole roleInfo = blueRoleService.getRoleInfo(id);
        if (roleInfo == null) {
            return ResultInfo.error("角色信息不存在！");
        }
        logger.info("查询角色【" + id + "】成功");
        return ResultInfo.success("SUCCESS", roleInfo);
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
            return ResultInfo.error("角色ID不正确！");
        }
        if ((name == null || "".equals(name)) &&
                (remark == null || "".equals(remark)) &&
                (status == null)) {
            return ResultInfo.error("请填写要修改的信息!!!");
        }

        BlueRole BlueRole = new BlueRole();
        BlueRole.setId(id);
        if (name != null && !"".equals(name)) {
            BlueRole.setName(name);
        }
        if (remark != null && !"".equals(remark)) {
            BlueRole.setRemark(remark);
        }
        if (status == 0 || status == 1) {
            BlueRole.setStatus(status);
        }
        BlueUser blueUser = (BlueUser) session.getAttribute("blueUser");
        BlueRole.setModifier(blueUser.getName());

        //update数据库
        int count = blueRoleService.update(BlueRole);
        if (count != 0) {
            return ResultInfo.success("更新角色【" + id + "】信息成功！");
        } else {
            return ResultInfo.error("修改失败,请重试！");
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo deleteUser(@PathVariable("id") int id) {
        if (id == 0) {
            return ResultInfo.error("角色ID不正确！");
        }
        blueRoleService.delete(id);
        return ResultInfo.success("删除角色【" + id + "】成功！");
    }
}
