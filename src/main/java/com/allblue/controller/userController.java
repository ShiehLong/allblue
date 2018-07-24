package com.allblue.controller;

import com.alibaba.fastjson.JSON;
import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.info("username:" + username + "   email:" + email + "   password:" + password);
        BlueUser blueUser = new BlueUser();
        blueUser.setUsername(username);
        blueUser.setEmail(email);
        blueUser.setPassword(password);
        int id = userService.add(blueUser);
        Map<String, String> map = new HashMap<String, String>();
        if (id != 0) {
            map.put("result", "success");
            map.put("msg", "注册成功");
        } else {
            map.put("result", "fail");
            map.put("msg", "注册失败");
        }
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String userLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("username:" + username + "   password:" + password);
        BlueUser blueUser = new BlueUser();
        blueUser.setUsername(username);
        blueUser.setPassword(password);
        int id = userService.getId(blueUser);

        Map<String, String> map = new HashMap<String, String>();
        if (id != 0) {
            map.put("result", "success");
            map.put("msg", "登录成功");
        } else {
            map.put("result", "fail");
            map.put("msg", "登录失败");
        }
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
}
