package com.test.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servletB")
public class ServletB_Dispatch extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String money = req.getParameter("money");
        System.out.println("B被触发，获取参数money："+money); // B也能获取到parameter中的money

        //重定向到视图资源
        System.out.println("B重定向到aaa.html");
        RequestDispatcher rd = req.getRequestDispatcher("aaa.html");
        rd.forward(req, resp);
    }
}
