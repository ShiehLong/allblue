package com.allblue.interceptor;

import com.allblue.model.BlueUser;
import com.allblue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String url = "/jsp/user/login.jsp";

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
//        logger.info("进入拦截器preHandle方法！！！！");
        //先从session拿取用户
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            logger.error("cookie信息为空！！！");
            ajaxRequest(request, response);
            return false;
        }
        try {
            HttpSession session = request.getSession(false);
            String sessionId = session.getId();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    if (!cookie.getValue().equals(sessionId)) {
                        logger.error("cookie信息与session信息不一致！！！");
                        ajaxRequest(request, response);
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
//                        logger.info("session内user信息：" + user);
                        if (user != null) {
                            if (user.getPassword().equals(realPassword)) {
                                logger.info("拦截器验证用户信息通过！！！");
                                return true;
                            } else {
                                logger.error("用户信息已修改，请重新登录！！！");
                                ajaxRequest(request, response);
                                return false;
                            }
                        }
                    } catch (NullPointerException e) {
                        logger.error("查询用户密码错误或者获取session信息错误:" + e);
                        ajaxRequest(request, response);
                        return false;
                    }

                }
            }
        } catch (Exception e) {
            logger.error("拦截器异常：" + e);
        }
        logger.error("拦截器校验未通过！回到登录页面!!!");
        ajaxRequest(request, response);
        return false;
    }

    /**
     * @Description:由于ajax请求不能收到拦截器的返回消息，需要自己写入response
     * @Author Xone
     * @Date 15:57 2018/11/5
     **/
    public void ajaxRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader("x-requested-with") != null &&
                request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有x-requested-with
            try {
                response.getWriter().write("loseSession");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

