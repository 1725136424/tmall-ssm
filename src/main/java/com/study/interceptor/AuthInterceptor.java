package com.study.interceptor;

import com.study.pojo.user.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] noNeedAuthPage = {
                "/home",
                "/register",
                "/login",
                "/product",
                "/checkLogin",
                "/loginAjax",
                "/category",
        };
        // 经过此拦截器必然是前台请求
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI().replace(contextPath, "");
        // 前台请求
        String uri = requestUri.replace("fore", "");
        boolean isExist = false;
        for (String s : noNeedAuthPage) {
            if (s.equals(uri)) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            return true;
        } else {
            // 判断当前是否是登入状态
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                return true;
            }
            // 跳转登录页面
            response.sendRedirect(request.getContextPath() + "/loginPage");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
