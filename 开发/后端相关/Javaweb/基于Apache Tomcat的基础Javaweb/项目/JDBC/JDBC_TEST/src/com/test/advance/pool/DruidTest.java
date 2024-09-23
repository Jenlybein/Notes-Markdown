package com.test.advance.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
    @Test
    public void testHardCodeDruid() throws SQLException {
        // 创建DruidDataSource 连接池对象
        DruidDataSource ds = new DruidDataSource();

        //设置连接池的配置信息
            // 必须设置的配置
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); //驱动类
        ds.setUrl("jdbc:mysql://localhost:3306/jdbc_test");
        ds.setUsername("root");
        ds.setPassword("86503358299");
            // 非必须设置的配置
        ds.setInitialSize(10); // 初始创建的链接对象
        ds.setMaxActive(20); // 最大链接对象个数

        // 通过连接池获取连接对象
        Connection conn = ds.getConnection();
        System.out.println(conn);
        // CRUD... ...

        // 回收连接
        conn.close();
    }

    @Test
    public void testSoftCodeDruid() throws Exception {
        // 创建Properties集合，用于存储外部配置文件的key和value值
        Properties ppt = new Properties();

        // 读取外部配置文件，获取输入流，加载到集合
        InputStream is = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
        ppt.load(is);

        // 基于Properties集合构建DruidDataSource连接池
        DataSource ds = DruidDataSourceFactory.createDataSource(ppt);

        // 通过连接池获取连接对象
        Connection conn = ds.getConnection();
        System.out.println(conn);
        // CRUD... ...

        // 回收连接
        conn.close();
    }
}
