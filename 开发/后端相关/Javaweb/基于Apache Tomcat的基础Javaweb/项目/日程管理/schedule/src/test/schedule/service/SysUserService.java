package test.schedule.service;

import test.schedule.pojo.SysUser;

public interface SysUserService {

    /**
     * 注册用户的方法
     * @param sysUser 注册的用户名和密码以SysUser对象的形式接收
     * @return 成功1，失败0
     */
    int register(SysUser sysUser) throws Exception;

    /**
     * 服务层：根据用户名获取完整用户信息的方法
     * @param username 要查询的用户名
     * @return 找到了则返回SysUser
     */
    SysUser findByUsername(String username) throws Exception;
}
