package test.schedule.service.impl;

import test.schedule.dao.SysUserDao;
import test.schedule.dao.impl.SysUserDaoImpl;
import test.schedule.pojo.SysUser;
import test.schedule.service.SysUserService;
import test.schedule.util.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao userDao = new SysUserDaoImpl();

    @Override
    public int register(SysUser sysUser) throws Exception {
        // 将明文密码转为密文密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        // 调用DAO层的方法，将信息存入数据库
        return userDao.addSysUser(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) throws Exception {
        return userDao.findByUsername(username);
    }
}
