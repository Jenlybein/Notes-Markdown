package com.test.senior.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    //创建连接池引用，因为要提供给当前项目全局使用，所以创建为静态的。
    private static DataSource ds;

    //在项目启动时，即创建连接池对象，赋值给dataSource
    static{
        try {
            Properties ppt = new Properties();
            InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
            ppt.load(in);

            ds = DruidDataSourceFactory.createDataSource(ppt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //对外提供获取连接的静态方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //对外提供回收连接的静态方法
    public static void release(Connection conn) throws SQLException {
        conn.close();
    }




}
