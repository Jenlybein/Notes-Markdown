package com.test.base;

import org.junit.Test;

import java.sql.*;

public class JDBCQuery {
    @Test
    public void querySingleRowAndCol() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);
        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "select count(*) as count from jdbc_temp1"
        );
        //执行语句，获取结果
        ResultSet rs = ps.executeQuery();
        //处理结果
        while(rs.next()){
            int count = rs.getInt("count");
            System.out.println("count = "+ count);
        }
        //释放资源（掀开后关)
        rs.close();
        ps.close();
        conn.close();
    }

    @Test
    public void querySingleRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "select * from jdbc_temp1 where t_id = ?"
        );

        //执行语句，获取结果
        ps.setInt(1,2);
        ResultSet rs = ps.executeQuery();

        //处理结果
        while(rs.next()){
            int t_id = rs.getInt("t_id");
            String t_name = rs.getString("t_name");
            String t_salary = rs.getString("t_salary");
            int t_age = rs.getInt("t_age");
            System.out.println("id = " + t_id + ", name = " + t_name+ ", salary = " + t_salary + ", age = " + t_age);
        }

        //释放资源（掀开后关)
        rs.close();
        ps.close();
        conn.close();
    }
    @Test
    public void queryMultiRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "select * from jdbc_temp1"
        );

        //执行语句，获取结果
        ResultSet rs = ps.executeQuery();

        //处理结果
        System.out.println("id\tname\tsalary\tage");
        while(rs.next()){
            int t_id = rs.getInt("t_id");
            String t_name = rs.getString("t_name");
            String t_salary = rs.getString("t_salary");
            int t_age = rs.getInt("t_age");
            System.out.println(t_id + "\t" + t_name+ "\t" + t_salary + "\t" + t_age);
        }

        //释放资源（掀开后关)
        rs.close();
        ps.close();
        conn.close();
    }

    @Test
    public void insertRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "Insert into jdbc_temp1(t_name,t_salary,t_age) values(?,?,?)"
        );

        //执行语句，获取结果
        ps.setString(1,"员工6");
        ps.setDouble(2,321.0);
        ps.setInt(3,22);
        int rs = ps.executeUpdate();

        //处理结果
        if(rs > 0){
            System.out.println("新增数据成功");
        }
        else{
            System.out.println("新增数据失败");
        }

        //释放资源（掀开后关)
        ps.close();
        conn.close();
    }

    @Test
    public void setRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE jdbc_temp1 set t_salary = ? where t_name = ?"
        );

        //执行语句，获取结果
        ps.setDouble(1,3000);
        ps.setString(2,"员工6");
        int rs = ps.executeUpdate();

        //处理结果
        if(rs > 0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");

        //释放资源（掀开后关)
        ps.close();
        conn.close();
    }

    @Test
    public void deleteRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "DELETE from jdbc_temp1 where t_name = ?"
        );

        //执行语句，获取结果
        ps.setString(1,"员工6");
        int rs = ps.executeUpdate();

        //处理结果
        if(rs > 0)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");

        //释放资源（掀开后关)
        ps.close();
        conn.close();
    }
}