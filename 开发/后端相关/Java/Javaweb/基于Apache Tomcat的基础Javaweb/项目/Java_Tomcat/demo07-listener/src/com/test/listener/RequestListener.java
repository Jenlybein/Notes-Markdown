package com.test.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        System.out.println(request.hashCode()+"请求域 初始化");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        System.out.println(request.hashCode()+"请求域 销毁");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        String key = srae.getName();
        Object value = srae.getValue();
        ServletRequest request = srae.getServletRequest();
        System.out.println(request.hashCode()+"请求域 增加： "+key+"："+value);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        String key = srae.getName();
        Object value = srae.getValue();
        ServletRequest request = srae.getServletRequest();
        System.out.println(request.hashCode()+"请求域 删除： "+key+"："+value);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        String key = srae.getName();
        Object value = srae.getValue();
        ServletRequest request = srae.getServletRequest();
        Object keyNew = request.getAttribute(key);
        System.out.println(request.hashCode()+"请求域 修改： "+key+"："+value+" 变为 "+keyNew);
    }
}
