package com.test.servlet;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/*
public class UserServlet implements Servlet {

}
*/

public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp); //注释此处，不然会出现405

        // 1. 从request对象中获取请求中任何信息
        String username = req.getParameter("username"); //根据参数名获取参数值，无论参数是在url?后，还是在请求体中
        // 2. 处理业务代码
        String info = "YES";
        if("admin".equals(username)){
            info = "NO";
        }
        // 3.设置Content-Type响应头，明确返回的数据类型
        resp.setContentType("text/html;charset=utf-8");
        // 4.将要相应的数据放入response
        PrintWriter out = resp.getWriter(); // 该方法返回的是一个向响应体中打印字符串的打印流
        out.println(info);
    }
}

