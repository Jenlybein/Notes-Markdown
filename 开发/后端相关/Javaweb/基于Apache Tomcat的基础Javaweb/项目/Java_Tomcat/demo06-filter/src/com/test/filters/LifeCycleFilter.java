package com.test.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/*")
public class LifeCycleFilter implements Filter {
    // 创建对象
    public LifeCycleFilter(){
        System.out.println("LifeCycleFilter constructor method invoked");
    }

    // 初始化，FilterConfig是从web.xml中读取信息
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LifeCycleFilter init method invoked");
        System.out.println(filterConfig.getInitParameter("dateTimePattern"));
    }

    //过滤请求
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LifeCycleFilter doFilter method invoked");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    //销毁
    @Override
    public void destroy() {
        System.out.println("LifeCycleFilter destory method invoked");
    }
}