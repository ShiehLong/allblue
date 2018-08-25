package com.allblue.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        logger.info("进入拦截器preHandle方法！！！！");
        //先从session拿取用户
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            logger.info("cookie信息为空！！！");
            response.sendRedirect("login");
        }
        try {
            HttpSession session = request.getSession(false);
            String sessionId = session.getId();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    if (!cookie.getValue().equals(sessionId)) {
                        logger.info("cookie信息与session信息不一致！！！");
                        response.sendRedirect("login");
                    }
                }
            }

            for (Cookie cookie2 : cookies) {
                if (cookie2.getName().equals("username") && cookie2.getValue() != null) {
                    String cookieUsername = cookie2.getValue();
                    try {
                        String realPassword = userService.getUserInfo(cookieUsername).getPassword();
                        BlueUser user = (BlueUser) session.getAttribute("blueUser");
                        if (user != null) {
                            if (user.getPassword().equals(realPassword)) {
                                logger.info("验证用户信息通过！！！");
                                return true;
//                            response.sendRedirect("redirect:home");
                            } else {
                                logger.info("验证用户信息未通过，密码不正确！！！");
                                response.sendRedirect("login");
                            }
                        }
                    } catch (NullPointerException e) {
                        logger.info("查询用户密码错误或者获取session信息错误！！！");
                        response.sendRedirect("login");
                    }

                }
            }
        } catch (Exception e) {
            logger.error("拦截器异常：" + e);
        }

//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        JSONObject res = new JSONObject();
//        res.put("result", "unauthorized");
//        res.put("msg", "请先登录~");
//        PrintWriter out = response.getWriter();
//        out.append(res.toString());
        logger.info("拦截器校验未通过！回到登录页面!!!");
        response.sendRedirect("login");
        return false;
    }
}

