package com.chenyulin.myblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入登录拦截器.....");
        System.out.println(request.getContextPath());
        HttpSession session = request.getSession(true);
        String userName = (String)session.getAttribute("userName");
        System.out.println(userName+"---------------");
        if(session.getAttribute("userName") == null){
            response.sendRedirect(request.getContextPath()+"/blog/loginpage");
            return false;
        }else{
            session.setAttribute("userName", session.getAttribute("userName"));
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
