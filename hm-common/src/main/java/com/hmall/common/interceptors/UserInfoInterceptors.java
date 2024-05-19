package com.hmall.common.interceptors;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、获取登陆用户信息
        String userInfo = request.getHeader("user-info");
        //2、判断是否获取了用户，如果有，存入ThreadLocal
        if (StringUtils.isNotBlank(userInfo)) {
            UserContext.setUser(Long.valueOf(userInfo));
        }
        //3、放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeUser();
    }
}