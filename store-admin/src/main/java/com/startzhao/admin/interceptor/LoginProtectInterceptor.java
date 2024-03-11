package com.startzhao.admin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: LoginProtectInterceptor
 * Package: com.startzhao.admin.interceptor
 * Description: 登录保护拦截器
 *
 * @Author StartZhao
 * @Create 2024/3/12 0:18
 * @Version 1.0
 */
@Configuration
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userInfo") == null) {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return false;
        }
        return true;
    }
}
