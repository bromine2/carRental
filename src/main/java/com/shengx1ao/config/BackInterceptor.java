package com.shengx1ao.config;

import com.shengx1ao.common.Common;
import com.shengx1ao.entity.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 * 未登录用户，重定向到登录页面
 */

public class BackInterceptor implements HandlerInterceptor {
    /**
     * 访问所有后台请求前，先要经过此方法
     * 返回true，继续访问，返回false中断访问，重定向至登录页面
     * **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo=(UserInfo) request.getSession().getAttribute(Common.USER_INFO);
        if(userInfo==null){
            response.sendRedirect("/end/page/login.html");
            return false;
        }
        return true;
    }
}
