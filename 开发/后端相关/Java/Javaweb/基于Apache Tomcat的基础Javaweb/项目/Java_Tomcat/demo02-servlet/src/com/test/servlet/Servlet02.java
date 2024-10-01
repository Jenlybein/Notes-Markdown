package com.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/servlet02")
public class Servlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 行相关  GET/POST  URI  URL  HTTP/1.1
        System.out.println(req.getMethod());        // 获取请求方式
        System.out.println(req.getScheme());        // 获取请求协议
        System.out.println(req.getProtocol());      // 获取请求协议及版本号
        System.out.println(req.getRequestURI());    // 获取客户端请求的uri (项目内资源的路径)
        System.out.println(req.getRequestURL());    // 获取客户端请求的url (项目内资源的完整路径）

        System.out.println(req.getLocalPort());     // 获取本应用在所在容器的端口
        System.out.println(req.getServerPort());    // 获取客户端发送请求时的端口（可能因为代理服务器原因与服务器本地不一样）
        System.out.println(req.getRemotePort() + "\n");    // 获取客户端程序的端口

        // 头相关 key:value...
        String Accept = req.getHeader("Accept");
        System.out.println("Accept : " + Accept);

        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(value);
        }
    }
}
