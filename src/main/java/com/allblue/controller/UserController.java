package com.allblue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author Xone
 * @Date 19:08 2018/7/19
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String userRegister(HttpServletRequest request) {
        //入参
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //返回数据
        JSONObject re = new JSONObject();
        //入参判断
        if (name == null || "".equals(name) ||
                email == null || "".equals(email) ||
                password == null || "".equals(password)) {
            re.put("result", "fail");
            re.put("msg", "注册信息不能为空~");
            return JSON.toJSONString(re);
        }
        //判断用户名是否已存在
        BlueUser isExist = userService.getUserInfo(name);
        if (null != isExist) {
            re.put("result", "fail");
            re.put("msg", "用户名已存在，换一个试试~");
            return JSON.toJSONString(re);
        } else {
            BlueUser blueUser = new BlueUser();
            blueUser.setName(name);
            blueUser.setEmail(email);
            blueUser.setPassword(password);
            blueUser.setCreator(name);
            blueUser.setModifier(name);
            //插入数据库
            int id = userService.add(blueUser);
            if (id != 0) {
                re.put("result", "success");
                re.put("msg", "注册成功~");
                logger.info("注册成功！！！注册信息：name:" + name);
            } else {
                re.put("result", "fail");
                re.put("msg", "注册失败,请重试~");
            }
            return JSON.toJSONString(re);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String userLogin(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        logger.info("name:" + name + "   password:" + password);

        BlueUser blueUser = userService.getUserInfo(name, password);

        JSONObject re = new JSONObject();
        if (blueUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("blueUser", blueUser);

            re.put("result", "success");
            re.put("msg", "登录成功");

            Cookie nameCookie = new Cookie("name", name);
            nameCookie.setMaxAge(6000);
            nameCookie.setPath("/");
            response.addCookie(nameCookie);

            Cookie[] cookies = request.getCookies();
            logger.info("外部的SessionId:" + session.getId());
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    logger.info("Cookie里边的：" + session.getId());
                    cookie.setValue(session.getId());
                    cookie.setPath("/");
                    cookie.setMaxAge(6000);
                    response.addCookie(cookie);
                }
            }

        } else {
            re.put("result", "fail");
            re.put("msg", "用户名或密码错误~");
        }
        return JSON.toJSONString(re);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();
        Cookie nameCookie = new Cookie("name", "");
        nameCookie.setMaxAge(0);
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
        logger.info("清除数据，退出登录！！！");
        return "user/login";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取用户信息列表
        List<BlueUser> list = userService.getUserList();
        model.addAttribute("list", list);
        return "user/list";
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") int id, Model model, HttpSession session) {
        if (id == 0) {
            return "redirect:/index.jsp";
        }
        BlueUser userInfo = userService.getUserInfo(id);
        if (userInfo == null) {
            return "redirect:/index.jsp";
        }

        //更新session
        BlueUser cur = (BlueUser) session.getAttribute("blueUser");
        if (cur.getId() == id) {
            session.setAttribute("blueUser", userInfo);
        }
        model.addAttribute("userInfo", userInfo);
        return "user/detail";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String updatePage(@PathVariable("id") int id, Model model) {
        if (id == 0) {
            return "redirect:/user/list";
        }
        BlueUser userInfo = userService.getUserInfo(id);
        logger.info("更新用户信息页面【" + userInfo + "】");
        if (StringUtils.isEmpty(userInfo)) {
            return "redirect:/user/list";
        }
        model.addAttribute("userInfo", userInfo);
        return "user/update";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public String update(@PathVariable("id") int id, String email, String password, Integer status, MultipartFile photo, HttpSession session) {
        //获取用户信息
        if (id == 0) {
            return "redirect:/user/list";
        }
        //入参判断
        if ((email == null || "".equals(email)) &&
                (password == null || "".equals(password)) &&
                photo.getSize() <= 0) {
            logger.info("请填写要修改的信息!!!");
            return "redirect:/user/" + id + "/update";
        }

        BlueUser blueUser = new BlueUser();
        blueUser.setId(id);
        if (email != null && !"".equals(email)) {
            blueUser.setEmail(email);
        }
        if (password != null && !"".equals(password)) {
            blueUser.setPassword(password);
        }
        if (status == 0 || status == 1) {
            blueUser.setStatus(status);
        }
        //获取图片原始名字
        String originalName = photo.getOriginalFilename();
        //上传图片
        if (originalName != null && originalName.length() > 0) {
            //图片存储物理地址
            String store = "D:\\photos\\user\\";
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //图片新名称
            String newPhotoName = uuid + originalName.substring(originalName.lastIndexOf("."));
            //新图片生成
            File file = new File(store + newPhotoName);
            //将内存中的图片写入磁盘
            try {
                photo.transferTo(file);
                logger.info("头像写入磁盘！！！");
            } catch (IOException e) {
                e.printStackTrace();
            }
            blueUser.setPhoto("/photos/user/" + newPhotoName);
        }
        //从session中取当前的用户名
        BlueUser cur = (BlueUser) session.getAttribute("blueUser");
        blueUser.setModifier(cur.getName());

        //update数据库
        int count = userService.update(blueUser);
        if (count != 0) {
            logger.info("更新用户信息成功！！！");
        } else {
            logger.info("修改失败,请重试！！！");
        }
        return "redirect:/user/" + id + "/detail";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id) {
        if (id == 0) {
            return "redirect:/user/list";
        }
        userService.delete(id);
        logger.info("删除ID为" + id + "的用户成功！");
        return "redirect:/user/list";
    }
}
