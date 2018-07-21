package com.allblue.controller;

import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author Xone
 * @Date 19:08 2018/7/19
 **/
@Controller
@RequestMapping("/user")
public class userController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public userController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String userRegister(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.info("username:" + username +"   email:" + email + "   password:" + password);
        BlueUser blueUser = new BlueUser();
        blueUser.setUsername(username);
        blueUser.setEmail(email);
        blueUser.setPassword(password);
        userService.userRegister(blueUser);
        return "login";
    }
}
