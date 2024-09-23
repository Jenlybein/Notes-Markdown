package com.test.advance;

import com.test.advance.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvanceTest {
    @Test
    public void querySingleRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        PreparedStatement ps = conn.prepareStatement("select * from jdbc_test.jdbc_temp1 where t_id = ?");

        //为占位符赋值，获取结果
        ps.setInt(1, 1);
        ResultSet rs = ps.executeQuery();
        //预先创建实体类变量
        Employee employee = null;
        //处理结果
        while (rs.next()) {
            int empId = rs.getInt("t_id");
            String empName = rs.getString("t_name");
            Double empSalary = Double.valueOf(rs.getString("t_salary"));
            int empAge = rs.getInt("t_age");
            employee = new Employee(empId,empName,empSalary,empAge);
        }

        System.out.println("employee = " + employee);

        //释放资源(先开后关原则)
        rs.close();
        ps.close();
        conn.close();
    }

    @Test
    public void queryRowList() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url, user, pasw);

        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        PreparedStatement ps = conn.prepareStatement("select * from jdbc_test.jdbc_temp1");

        //为占位符赋值，获取结果
        ResultSet rs = ps.executeQuery();

        //预先创建实体类变量
        Employee employee = null;
        List<Employee> employeeList = new ArrayList<>();

        //处理结果
        while (rs.next()) {
            int empId = rs.getInt("t_id");
            String empName = rs.getString("t_name");
            Double empSalary = Double.valueOf(rs.getString("t_salary"));
            int empAge = rs.getInt("t_age");
            employee = new Employee(empId, empName, empSalary, empAge);
            employeeList.add(employee);
        }

        for (Employee e : employeeList) {
            System.out.println(e);
        }

        //释放资源(先开后关原则)
        rs.close();
        ps.close();
        conn.close();
    }

    @Test
    public void ReturnPK() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url, user, pasw);

        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        String sql = "INSERT INTO jdbc_test.jdbc_temp1(t_name,t_salary,t_age) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //创建对象，将对象的属性值，填充在占位符
        Employee employee = new Employee(null,"Jack",231.0,23);
        ps.setString(1, employee.getName());
        ps.setDouble(2,employee.getSalary());
        ps.setInt(3,employee.getAge());

        // 获取结果
        int rs = ps.executeUpdate();

        // 处理结果
        if (rs > 0) {
            System.out.println("成功");
            // 获取新增的主键列，回显到employee对象的属性上
            ResultSet rsSet = ps.getGeneratedKeys();
            if(rsSet.next())
                employee.setId(rsSet.getInt(1));
            rsSet.close();
            System.out.println(employee);
        }else {
            System.out.println("失败");
        }

        //释放资源(先开后关原则)
        ps.close();
        conn.close();
    }

    @Test
    public void testBatch() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test?rewriteBatchedStatements=true"; //修改1
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url, user, pasw);

        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        String sql = "INSERT INTO jdbc_test.jdbc_temp1(t_name,t_salary,t_age) VALUES(?,?,?)"; //修改2
        PreparedStatement ps = conn.prepareStatement(sql);

        //获取当前代码的执行时间(ms)
        long start = System.currentTimeMillis();

        //创建对象，将对象的属性值，填充在占位符
        for(int i = 0; i < 10000; i++){
            ps.setString(1, "abc"+i);
            ps.setInt(3,100+i);
            ps.setDouble(2,20+i);

            ps.addBatch(); //修改3
        }

        // 获取结果
        ps.executeBatch(); //修改4

        long end = System.currentTimeMillis();

        System.out.println("消耗时间: "+(end-start));

        //释放资源(先开后关原则)
        ps.close();
        conn.close();
    }
}
