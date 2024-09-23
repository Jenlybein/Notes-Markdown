package com.test.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/servlet2")
public class Servlet2 extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 修改域中放入数据
        req.setAttribute("key-a","value-aaa");

        // 请求转发
        req.getRequestDispatcher("/servlet3").forward(req,resp);
    }
}
