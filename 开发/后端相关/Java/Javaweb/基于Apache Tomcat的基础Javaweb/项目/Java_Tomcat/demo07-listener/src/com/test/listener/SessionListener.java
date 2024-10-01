package com.test.listener;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener{
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 任何一个session域对象的创建都会触发该方法的执行
        HttpSession session = se.getSession();
        System.out.println(session.hashCode()+"会话域 初始化");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        // 任何一个session域对象的销毁都会触发该方法的执行
        HttpSession session = se.getSession();
        System.out.println(session.hashCode()+"会话域 销毁");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // 任何一个session域对象中增加数据都会触发该方法的执行
        String key = se.getName();
        Object value = se.getValue();
        HttpSession session = se.getSession();
        System.out.println(session.hashCode()+"会话域 增加： "+key+"："+value);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // 任何一个session域对象中移除数据都会触发该方法的执行
        String key = se.getName();
        Object value = se.getValue();
        HttpSession session = se.getSession();
        System.out.println(session.hashCode()+"会话域 删除： "+key+"："+value);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // 任何一个session域对象中修改数据都会触发该方法的执行
        String key = se.getName();
        Object value = se.getValue();
        HttpSession session = se.getSession();
        Object keyNew = session.getAttribute(key);
        System.out.println(session.hashCode()+"会话域 修改： "+key+"："+value+" 变为 "+keyNew);
    }
}
