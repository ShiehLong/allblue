package com.allblue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.allblue.model.BlueUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        String url = request.getServletPath();
        logger.info("post URL：" + url);
        if (!url.equals("")) {
            //判断是否已登录
            BlueUser loginUser = (BlueUser) request.getSession().getAttribute("user");
            if (loginUser == null) {
                //无session則是未登录状态
                logger.info(">>>未登录，请重新登录<<<");

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                JSONObject res = new JSONObject();
                res.put("result", "unauthorized");
                res.put("msg", "请先登录~");
                PrintWriter out = response.getWriter();
                out.append(res.toString());

                return false;
            }
        }
        return true;
    }
}

