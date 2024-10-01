package test.schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import test.schedule.common.Result;
import test.schedule.common.ResultCodeEnum;
import test.schedule.pojo.SysUser;
import test.schedule.service.SysUserService;
import test.schedule.service.impl.SysUserServiceImpl;
import test.schedule.util.MD5Util;
import test.schedule.util.WebUtil;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
    private final SysUserService userService = new SysUserServiceImpl();

    /**
     * 接收用户注册请求的业务处理方法（业务接口）
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收客户端提交的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 调用服务层的方法，完成注册功能
        SysUser sysUser = new SysUser(null,username,password);
        int rows = userService.register(sysUser);

        // 返回注册结果
        if (rows > 0) {
            // resp.sendRedirect("/registerSuccess.html"); // 此处为重定向另外一页表示成功
            // 此处我用重定向到本页，刷新网页，修改params，配合js显示隐藏信息
            resp.sendRedirect("/registerSuccess.html");
        } else {
            resp.sendRedirect("/register.html?status_register=failed");
        }
    }

    /**
     * 接收用户登录请求的业务处理方法（业务接口）
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收客户端提交的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 调用服务层的方法，完成登录功能
        SysUser sysUser = userService.findByUsername(username);

        // 检测用户名是否存在
        if (sysUser==null) {
            resp.sendRedirect("/login.html?status_login=wrongname");
        }
        // 检测密码是否符合
        else if( ! MD5Util.encrypt(password).equals(sysUser.getUserPwd()) ){
            resp.sendRedirect("/login.html?status_login=wrongpswd");
        }
        // 登录成功
        else {
            // 登录成功后，将登录的用户信息放入session
            HttpSession session = req.getSession();
            session.setAttribute("sysUser", sysUser);

            // 跳转
            resp.sendRedirect("/showSchedule.html");
        }
    }

    /**
     * 注册时，接收要注册的用户名，校验用户名是否被占用的业务接口
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");

        // 调用服务层的方法，查看是否存在对应用户
        SysUser sysUser = userService.findByUsername(username);

        // 检测用户名是否存在
        Result<Object> result = Result.build(null, ResultCodeEnum.USERNAME_EXIST);
        if (sysUser==null) {
            result = Result.build(null, ResultCodeEnum.SUCCESS);
        }

        WebUtil.writeJson(resp,result);
    }
}
