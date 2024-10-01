package test.schedule.dao.impl;

import test.schedule.dao.BaseDao;
import test.schedule.dao.SysUserDao;
import test.schedule.pojo.SysUser;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    @Override
    public int addSysUser(SysUser sysUser) throws Exception {
        String sql = "INSERT INTO sys_user VALUES(DEFAULT,?,?)";
        return executeUpdate(sql,sysUser.getUsername(),sysUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) throws Exception {
        String sql = "select uid,username,user_pwd userPwd from sys_user where username = ?";
        return executeQueryBean(SysUser.class,sql,username);
    }
}
