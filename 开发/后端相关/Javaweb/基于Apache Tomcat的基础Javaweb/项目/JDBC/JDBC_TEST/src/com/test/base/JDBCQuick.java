package com.test.base;

import java.sql.*;

public class JDBCQuick {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.注册驱动（将数据库厂商提供的驱动类进行加载）
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接对象
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String password = "86503358299";
        Connection conn = DriverManager.getConnection(url, user, password);

        // 3.获取执行SQL语句的对象
        Statement stmt = conn.createStatement();

        // 4.编写SQL语句并执行,接收返回的结果集
        String sql = "select t_id, t_name, t_salary, t_age from jdbc_test.jdbc_temp1";
        ResultSet rs = stmt.executeQuery(sql);

        // 5.处理结果：遍历结果集
        while (rs.next()) {
            int id = rs.getInt("t_id");
            String name = rs.getString("t_name");
            double salary = rs.getDouble("t_salary");
            int age = rs.getInt("t_age");
            System.out.println(id + "\t" + name + "\t" + salary + "\t" + age);
        }

        // 6.资源释放（原则：先开后关，后开先关)
        rs.close();
        stmt.close();
        conn.close();
    }
}
