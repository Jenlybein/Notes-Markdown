package com.test.senior.dao.impl;

import com.test.advance.pojo.Employee;
import com.test.senior.dao.EmployeeDAO;
import com.test.senior.dao.BaseDAO;
import java.util.List;


public class EmployeeDAOimpl extends BaseDAO implements EmployeeDAO {
    @Override
    public List<Employee> selectAll() throws Exception {
        String sql = "SELECT t_id as id, t_name as name, t_salary as salary, t_age as age FROM jdbc_test.jdbc_temp1";
        return executeQuery(Employee.class, sql, null);
    }

    @Override
    public Employee selectById(int id) throws Exception {
        String sql = "SELECT t_id as id, t_name as name, t_salary as salary, t_age as age FROM jdbc_test.jdbc_temp1 WHERE t_id = ?";
        return executeQueryBean(Employee.class, sql, id);
    }

    @Override
    public int insert(Employee employee) throws Exception {
        String sql = "INSERT INTO jdbc_test.jdbc_temp1(t_name, t_salary, t_age) VALUES(?,?,?)";
        return executeUpdate(sql,employee.getName(),employee.getSalary(),employee.getAge());
    }

    @Override
    public int update(Employee employee) throws Exception {
        String sql = "UPDATE jdbc_test.jdbc_temp1 SET t_salary = ? WHERE t_id = ?";
        return executeUpdate(sql,employee.getSalary(),employee.getId());
    }

    @Override
    public int delete(int id) throws Exception{
        String sql = "DELETE FROM jdbc_test.jdbc_temp1 WHERE t_id = ?";
        return executeUpdate(sql, id);
    }
}

