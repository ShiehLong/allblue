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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author Xone
 * @Date 19:08 2018/7/19
 **/
@Controller
@RequestMapping("/user")
public class userController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String userRegister(HttpServletRequest request) {
        //入参
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //返回数据
        JSONObject re = new JSONObject();
        //入参判断
        if (username == null || "".equals(username) ||
                email == null || "".equals(email) ||
                password == null || "".equals(password)) {
            re.put("result", "fail");
            re.put("msg", "注册信息不能为空~");
            return JSON.toJSONString(re);
        }
        //判断用户名是否已存在
        BlueUser isExistBlueUser = userService.getUserInfo(username);
        if (null != isExistBlueUser) {
            re.put("result", "fail");
            re.put("msg", "用户名已存在，换一个试试~");
            return JSON.toJSONString(re);
        } else {
            BlueUser blueUser = new BlueUser();
            blueUser.setUsername(username);
            blueUser.setEmail(email);
            blueUser.setPassword(password);
            //插入数据库
            int id = userService.add(blueUser);
            if (id != 0) {
                re.put("result", "success");
                re.put("msg", "注册成功~");
                logger.info("注册成功！！！注册信息：username:" + username + "   email:" + email + "   password:" + password);
            } else {
                re.put("result", "fail");
                re.put("msg", "注册失败,请重试~");
            }
            return JSON.toJSONString(re);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String userLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("username:" + username + "   password:" + password);
        BlueUser blueUser = new BlueUser();
        blueUser.setUsername(username);
        blueUser.setPassword(password);
        int id = userService.getUserId(blueUser);

        JSONObject re = new JSONObject();
        if (id != 0) {
            HttpSession session = request.getSession();
            blueUser.setId(id);
            session.setAttribute("blueUser", blueUser);

            re.put("result", "success");
            re.put("msg", "登录成功");

            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(500);
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);

            Cookie[] cookies = request.getCookies();
            logger.info("外部的SessionId:" + session.getId());
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    logger.info("Cookie里边的：" + session.getId());
                    cookie.setValue(session.getId());
                    cookie.setPath("/");
                    cookie.setMaxAge(500);
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
        Cookie usernameCookie = new Cookie("username", "");
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
        logger.info("清除数据，退出登录！！！");
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取用户信息列表
        List<BlueUser> list = userService.getUserList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") int id, Model model) {
        //获取用户信息
        if (id == 0) {
            return "redirect:/user/home";
        }
        BlueUser userInfo = userService.getUserInfo(id);
        if (userInfo == null) {
            return "redirect:/user/home";
        }
        model.addAttribute("userInfo", userInfo);
        return "detail";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        //获取用户信息
        if (id == 0) {
            return "redirect:/user/list";
        }
        BlueUser userInfo = userService.getUserInfo(id);
        if (userInfo == null) {
            return "redirect:/user/list";
        }
        model.addAttribute("userInfo", userInfo);
        return "update";
    }
}
