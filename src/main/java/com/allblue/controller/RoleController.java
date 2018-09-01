package com.allblue.controller;

import com.allblue.model.InputRole;
import com.allblue.model.Role;
import com.allblue.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author Xone
 * @Date 17:26 2018/8/31
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String addRole(@ModelAttribute InputRole inputRole) {

        //入参判断
        if (inputRole.getName() == null || "".equals(inputRole.getName()) ||
                inputRole.getSex() == null || "".equals(inputRole.getSex()) ||
                inputRole.getAge() <= 0 ||
                inputRole.getDescription() == null || "".equals(inputRole.getDescription()) ||
                inputRole.getVideo() == null || "".equals(inputRole.getVideo()) ||
                inputRole.getPic().getSize() <= 0) {
            logger.info("角色信息不完整!!!");
            return "redirect:/role/list";
        }

        Role role = new Role();
        role.setName(inputRole.getName());
        role.setSex(inputRole.getSex());
        role.setAge(inputRole.getAge());
        role.setDescription(inputRole.getDescription());
        role.setVideo(inputRole.getVideo());

        //获取图片原始名字
        String originalName = inputRole.getPic().getOriginalFilename();
        //上传图片
        if (originalName != null && originalName.length() > 0) {
            //图片存储物理地址
            String store = "D:\\photos\\role\\";
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //图片新名称
            String newPhotoName = uuid + originalName.substring(originalName.lastIndexOf("."));
            //新图片生成
            File file = new File(store + newPhotoName);
            //将内存中的图片写入磁盘
            try {
                inputRole.getPic().transferTo(file);
                logger.info("头像写入磁盘！！！");
            } catch (IOException e) {
                e.printStackTrace();
            }
            role.setPic("/photos/role/" + newPhotoName);
        }

        int id = roleService.addRole(role);
        if (id != 0) {
            logger.info("添加角色成功!!!");
            return "redirect:/role/" + id + "/detail";
        } else {
            logger.info("添加角色失败!!!");
            return "redirect:/role/list";
        }
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String updateRolePage(@PathVariable("id") int id, Model model) {
        if (id == 0) {
            return "redirect:/role/list";
        }
        Role roleInfo = roleService.getRoleInfo(id);
        logger.info("查询到角色信息如下：名称-" + roleInfo.getName() +
                "/年龄-" + roleInfo.getAge() + "/图片-" + roleInfo.getPic());
        if (StringUtils.isEmpty(roleInfo)) {
            return "redirect:/role/list";
        }
        model.addAttribute("roleInfo", roleInfo);
        return "role/roleUpdate";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String updateRole(@PathVariable("id") int id, @ModelAttribute InputRole inputRole) {
        if (id == 0) {
            return "redirect:/role/list";
        }
        //入参判断
        if (inputRole.getName() == null || "".equals(inputRole.getName()) &&
                inputRole.getSex() == null || "".equals(inputRole.getSex()) &&
                inputRole.getAge() <= 0 &&
                inputRole.getDescription() == null || "".equals(inputRole.getDescription()) &&
                inputRole.getVideo() == null || "".equals(inputRole.getVideo()) &&
                inputRole.getPic().getSize() <= 0) {
            logger.info("角色信息不完整!!!");
            return "redirect:/role/list";
        }

        Role role = new Role();
        role.setName(inputRole.getName());
        role.setSex(inputRole.getSex());
        role.setAge(inputRole.getAge());
        role.setDescription(inputRole.getDescription());
        role.setVideo(inputRole.getVideo());
        role.setId(id);

        //获取图片原始名字
        String originalName = inputRole.getPic().getOriginalFilename();
        //上传图片
        if (originalName != null && originalName.length() > 0) {
            //图片存储物理地址
            String store = "D:\\photos\\role\\";
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //图片新名称
            String newPhotoName = uuid + originalName.substring(originalName.lastIndexOf("."));
            //新图片生成
            File file = new File(store + newPhotoName);
            //将内存中的图片写入磁盘
            try {
                inputRole.getPic().transferTo(file);
                logger.info("头像写入磁盘！！！");
            } catch (IOException e) {
                e.printStackTrace();
            }
            role.setPic("/photos/role/" + newPhotoName);
        }

        int count = roleService.updateRole(role);
        if (count != 0) {
            logger.info("更新角色成功!!!");
            return "redirect:/role/" + id + "/detail";
        } else {
            logger.info("更新角色失败!!!");
            return "redirect:/role/list";
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String roleList(Model model) {
        //获取用户信息列表
        List<Role> list = roleService.getRoleList();
        model.addAttribute("list", list);
        return "role/roleList";
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String roleDetail(@PathVariable("id") int id, Model model) {
        //获取用户信息
        if (id == 0) {
            return "redirect:/role/list";
        }
        Role roleInfo = roleService.getRoleInfo(id);
        logger.info("查询到角色信息如下：名称-" + roleInfo.getName() +
                "/年龄-" + roleInfo.getAge() + "/图片-" + roleInfo.getPic());
        if (StringUtils.isEmpty(roleInfo)) {
            return "redirect:/role/list";
        }
        model.addAttribute("roleInfo", roleInfo);
        return "role/roleDetail";
    }

}
