package com.test.senior.dao;

import com.test.advance.pojo.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> selectAll() throws Exception;
    Employee selectById(int id) throws Exception;
    int insert(Employee employee) throws Exception;
    int update(Employee employee) throws Exception;
    int delete(int id) throws Exception;
}
