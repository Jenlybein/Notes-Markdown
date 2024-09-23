package test.schedule.dao;

import test.schedule.pojo.SysUser;

public interface SysUserDao {

    /**
     * 向数据库中增加一条用户记录的方法
     * @param sysUser 要增加的username和pwd以SysUser实体类对象的形式接收
     * @return 成功1，失败0
     */
    int addSysUser(SysUser sysUser) throws Exception;

    /**
     * Dao层：根据用户名获取完整用户信息的方法
     * @param username 要查询的用户名
     * @return 找到了则返回SysUser
     */
    SysUser findByUsername(String username) throws Exception;
}
