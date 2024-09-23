package test.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 增加日程的请求 /schedule/add
 * 查询日程的请求 /schedule/find
 * 修改日程的请求 /schedule/update
 * 删除日程的请求 /schedule/remove
 *
 */
@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("schedule add");
    }
    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("schedule remove");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("schedule update");
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("schedule find");
    }
}


