package com.test.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 向域中放入数据
        req.setAttribute("key-a","value-a");

        // 请求转发
        req.getRequestDispatcher("/servlet2").forward(req,resp);
    }
}
