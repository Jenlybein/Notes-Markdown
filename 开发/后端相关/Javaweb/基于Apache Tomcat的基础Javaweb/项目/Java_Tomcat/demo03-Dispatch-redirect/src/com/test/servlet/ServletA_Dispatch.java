package com.test.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servletA")
public class ServletA_Dispatch extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String money = req.getParameter("money");
        System.out.println("A被触发，获取参数money："+money);

        RequestDispatcher rd = req.getRequestDispatcher("/servletB");
        rd.forward(req, resp);
    }
}
