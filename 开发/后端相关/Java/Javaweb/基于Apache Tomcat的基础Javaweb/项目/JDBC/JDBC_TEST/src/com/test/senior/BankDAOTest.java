package com.test.senior;

import com.test.senior.Util.JDBCUtil_v2;
import com.test.senior.dao.BankDao;
import com.test.senior.dao.impl.BankDAOimpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class BankDAOTest {
    @Test
    public void testTransaction() throws SQLException {
        BankDao bd = new BankDAOimpl();
        Connection conn = null;

        try{
            //获取连接，将连接的事务改为手动提交
            conn = JDBCUtil_v2.getConnection();
            conn.setAutoCommit(false);

            //操作打款
            bd.subMoney(1,100);
            // int i = 10/0; //手动添加异常，查看事务是否添加成功
            bd.addMoney(2,100);

            //前置多次的dao操作没有异常，提交事务
            conn.commit();
        }catch (Exception e){
            // 出现异常，回滚事务
            try{
                conn.rollback();
                throw new RuntimeException(e);
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }finally {
            JDBCUtil_v2.release();
        }
    }
}
