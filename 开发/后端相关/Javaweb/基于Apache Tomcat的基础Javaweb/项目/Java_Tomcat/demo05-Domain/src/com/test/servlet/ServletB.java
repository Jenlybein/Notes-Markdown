package com.test.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求域中的数据
        String reqMessage = (String)req.getAttribute("request");
        System.out.println("请求域： "+reqMessage);

        // 获取会话域中的数据
        HttpSession session = req.getSession();
        String sessionMessage = (String)session.getAttribute("session");
        System.out.println("会话域： "+sessionMessage);

        // 获取应用域中的数据
        ServletContext context = getServletContext();
        String contextMessage = (String)context.getAttribute("context");
        System.out.println("应用域： "+contextMessage);
    }
}
