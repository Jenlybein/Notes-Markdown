package com.test.listener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class SessionBindingListener implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 当前监听器实例放入某个session中作为数据  绑定
        HttpSession session = event.getSession();
        String key = event.getName();
        System.out.println("SessionBindingListener"+this.hashCode()+" 以名称 "+ key + " 绑入 "+session.hashCode());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 当前监听器实例从某个session中移除  解绑定
        HttpSession session = event.getSession();
        String key = event.getName();
        System.out.println("SessionBindingListener"+this.hashCode()+" 以名称 "+ key + " 解绑 "+session.hashCode());
    }
}
