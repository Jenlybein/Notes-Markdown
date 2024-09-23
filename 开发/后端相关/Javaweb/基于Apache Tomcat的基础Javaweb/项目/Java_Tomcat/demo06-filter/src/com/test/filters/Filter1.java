package com.test.filters;

import jakarta.servlet.*;
import java.io.IOException;

public class Filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1 请求到达目标资源之前的功能代码
        System.out.println("Filter1 before doFilter invoked");
        // 2 放行代码
        filterChain.doFilter(servletRequest, servletResponse);
        // 3 响应之前 HttpServletResponse 转换为响应报文之前的功能代码
        System.out.println("Filter1 after doFilter invoked");
    }
}
