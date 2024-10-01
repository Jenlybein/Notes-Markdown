package com.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/servlet04")
public class Servlet04 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 直接根据键获取
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println(username+"\n"+password+"\n"+ Arrays.toString(hobbies)+"\n");

        // 获取所有参数名后逐个输出
        Enumeration<String> pnames = req.getParameterNames();
        while(pnames.hasMoreElements()){
            String pname = pnames.nextElement();
            String[] values = req.getParameterValues(pname);
            if(values.length>1){
                System.out.println(pname+"="+Arrays.toString(values));
            }else{
                System.out.println(pname+"="+values[0]);
            }
        }

        System.out.println("\n");

        // 返回所有参数的map集合，用键值对回去值。
        Map<String,String[]> map = req.getParameterMap();
        Set<Map.Entry<String,String[]>> entries = map.entrySet(); // 遍历Map的方式
        for(Map.Entry<String,String[]> entry : entries){
            String pname = entry.getKey();
            String[] values = entry.getValue();
            if(values.length>1){
                System.out.println(pname+"="+Arrays.toString(values));
            }else{
                System.out.println(pname+"="+values[0]);
            }
        }

    }
}
