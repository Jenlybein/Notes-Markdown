package com.test.listener;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletContextAttributeListener{
    @Override
    public void contextInitialized(ServletContextEvent sce){
        ServletContext application = sce.getServletContext();
        System.out.println(application.hashCode()+"应用域 初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        ServletContext application = sce.getServletContext();
        System.out.println(application.hashCode()+"应用域 销毁");

    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        ServletContext application = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println(application.hashCode()+"应用域 增加： "+key+"："+value);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        ServletContext application = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println(application.hashCode()+"应用域 移除： "+key+"："+value);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        ServletContext application = scae.getServletContext();
        // 获取旧的值
        String key = scae.getName();
        Object value = scae.getValue();
        // 获取新的值
        Object keyNew = application.getAttribute(key);

        System.out.println(application.hashCode()+"应用域 修改： "+key+"："+value+" 变为 "+keyNew);
    }
}
