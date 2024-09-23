package com.test.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志过滤器 记录请求的历史 将日志打印到控制台
 *
 * 1 实现Filter接口
 * 2 重写过滤方法
 * 3 配置过滤器
 *  web.xml
 *  注释
 */
public class LoggingFilter implements Filter {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 过滤请求的响应方法
     *  1 请求到达目标资源之前，先经过该方法
     *  2 该方法有能力控制请求是否继续向后到达目标资源 可以在该方法内直接向客户端做响应处理
     *  3 请求到达目标资源后，响应之后，还会经过该方法
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1 请求到达目标资源之前的功能代码
        System.out.println("logginFilter before filterChain.doFilter invoked");

        // (选填)打印日志
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String dateTime = dateFormat.format(new Date());

        long t1 = System.currentTimeMillis();

        // 2 放行代码
        filterChain.doFilter(servletRequest, servletResponse);
        // 3 响应之前 HttpServletResponse 转换为响应报文之前的功能代码
        System.out.println("logginFilter after filterChain.doFilter invoked");

        // (选填)打印日志
        long t2 = System.currentTimeMillis();

        System.out.println(requestURI + " 于 " + dateTime + " 被访问. " + " 请求耗时：" + (t2 - t1) + " ms");
    }
}
