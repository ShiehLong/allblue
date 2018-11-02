package com.allblue.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        String url = "/jsp/user/login.jsp";
        request.setCharacterEncoding("UTF-8");
        logger.info("进入拦截器preHandle方法！！！！");
        //先从session拿取用户
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            logger.info("cookie信息为空！！！");
            if (request.getHeader("x-requested-with") != null &&
                    request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //如果是ajax请求响应头会有x-requested-with
                response.getWriter().write("loseSession");
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
            return false;
        }
        try {
            HttpSession session = request.getSession(false);
            String sessionId = session.getId();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    if (!cookie.getValue().equals(sessionId)) {
                        logger.info("cookie信息与session信息不一致！！！");
                        if (request.getHeader("x-requested-with") != null &&
                                request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                            //如果是ajax请求响应头会有x-requested-with
                            response.getWriter().write("loseSession");
                        } else {
                            request.getRequestDispatcher(url).forward(request, response);
                        }
                        return false;
                    }
                }
            }

            for (Cookie cookie2 : cookies) {
                if (cookie2.getName().equals("name") && cookie2.getValue() != null) {
                    String cookieUsername = cookie2.getValue();
                    try {
                        String realPassword = userService.getUserInfo(cookieUsername).getPassword();
                        BlueUser user = (BlueUser) session.getAttribute("blueUser");
                        logger.info("session内user信息：" + user);
                        if (user != null) {
                            if (user.getPassword().equals(realPassword)) {
                                logger.info("验证用户信息通过！！！");
                                return true;
                            } else {
                                logger.info("用户信息已修改，请重新登录！！！");
                                if (request.getHeader("x-requested-with") != null &&
                                        request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                                    //如果是ajax请求响应头会有x-requested-with
                                    response.getWriter().write("loseSession");
                                } else {
                                    request.getRequestDispatcher(url).forward(request, response);
                                }
                                return false;
                            }
                        }
                    } catch (NullPointerException e) {
                        logger.info("查询用户密码错误或者获取session信息错误！！！");
                        if (request.getHeader("x-requested-with") != null &&
                                request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                            //如果是ajax请求响应头会有x-requested-with
                            response.getWriter().write("loseSession");
                        } else {
                            request.getRequestDispatcher(url).forward(request, response);
                        }
                        return false;
                    }

                }
            }
        } catch (Exception e) {
            logger.error("拦截器异常：" + e);
        }
        logger.info("拦截器校验未通过！回到登录页面!!!");
        if (request.getHeader("x-requested-with") != null &&
                request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有x-requested-with
            response.getWriter().write("loseSession");
        } else {
            request.getRequestDispatcher(url).forward(request, response);
        }
        return false;
    }
}

