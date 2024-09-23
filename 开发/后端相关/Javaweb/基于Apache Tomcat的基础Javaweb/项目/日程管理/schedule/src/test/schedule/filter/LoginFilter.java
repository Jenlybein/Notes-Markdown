package test.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import test.schedule.pojo.SysUser;

import java.io.IOException;

@WebFilter( urlPatterns = {"/schedule/*","/showSchedule.html"} )
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取session域对象
        HttpSession session = request.getSession();

        // 从session域中获得登录的用户对象
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");

        // 判断用户对象是否为空，没登陆则跳回到login.html，登录则放行
        if (sysUser == null) {
            response.sendRedirect("login.html");
        }else{
            filterChain.doFilter(request, response);
        }
    }
}
