package com.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet06")
public class Servlet06 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = "<h1>Hello World</h1>";

        // 设置相应行相关的API  ->  HTTP/1.1 200/404/405/500/...
        resp.setStatus(200);    //设置200(成功)，即使没有找到资源也会返回该状态。设置404同理。

        // 设置响应头相关的API
        resp.setHeader("Content-Type", "text/html;charset=utf-8");   // 表明相应的内容
        resp.setHeader("Content-Length", String.valueOf(info.getBytes().length));                      // 标明返回内容的长度
            // 另一种写法
            //resp.setContentType("text/html;charset=utf-8");
            //resp.setContentLength(info.getBytes().length);

        // 设置响应体内容API
        // 获得一个向相应体中输出文本字符输出流
        PrintWriter out = resp.getWriter();
        out.write(info);

        // 获得一个向响应体中输入二进制信息的字节输出流
        // ServletOutputStream out = resp.getOutputStream();
    }

}
