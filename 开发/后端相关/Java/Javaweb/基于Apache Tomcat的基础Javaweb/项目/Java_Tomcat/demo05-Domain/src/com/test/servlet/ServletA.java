package com.test.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向请求域存放数据
        req.setAttribute("request","requestMessage");

        //向会话域存放数据
        HttpSession session = req.getSession();
        session.setAttribute("session","sessionMessage");

        //向应用域存放数据
        ServletContext context = getServletContext();
        context.setAttribute("context","contextMessage");

        // 请求转发（不使用转发则无法在servletB读取到数据）
        req.getRequestDispatcher("/servletB").forward(req, resp);
    }
}
