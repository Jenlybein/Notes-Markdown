package com.test.advance.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariTest {
    @Test
    public void testHardCodeHikari() throws SQLException {
        // 创建连接池对象
        HikariDataSource hds = new HikariDataSource();

        // 设置连接池的配置信息
            // 必须设置的配置
        hds.setDriverClassName("com.mysql.cj.jdbc.Driver"); //驱动类
        hds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_test");
        hds.setUsername("root");
        hds.setPassword("86503358299");
            // 非必须设置的配置
        hds.setMinimumIdle(10); // 初始创建的链接对象
        hds.setMaximumPoolSize(20); // 最大链接对象个数

        // 获取连接对象
        Connection conn = hds.getConnection();
        System.out.println(conn);

        // 回收
        conn.close();
    }

    @Test
    public void testSoftCodeHikari() throws SQLException, IOException {
        //创建Properties集合，用于存储外部配置文件key-value
        Properties ppt = new Properties();

        //读取外部配置文件，获取输入流，输到ppt集合
        InputStream is = HikariTest.class.getClassLoader().getResourceAsStream("hikari.properties");
        ppt.load(is);

        //创建Hikari连接池配置对象，将集合传入
        HikariConfig config = new HikariConfig(ppt);

        // 创建连接池对象
        HikariDataSource hds = new HikariDataSource(config);

        // 获取连接对象
        Connection conn = hds.getConnection();
        System.out.println(conn);

        // 回收
        conn.close();
    }
}
