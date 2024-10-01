package com.test.senior;

import com.test.advance.pojo.Employee;
import com.test.senior.dao.EmployeeDAO;
import com.test.senior.dao.impl.EmployeeDAOimpl;
import org.junit.Test;

import java.util.List;

public class BaseDAOTest {
    @Test
    public void testEmployeeDaoSelectAll() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        List<Employee> list = employee.selectAll();
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void testEmployeeDaoSelectID() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        Employee emp = employee.selectById(10008);
        System.out.println(emp);
    }

    @Test
    public void testEmployeeDaoInsert() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        Employee emp = new Employee(null,"Tom",1900.0,22);
        int result = employee.insert(emp);
        System.out.println(result);
    }

    @Test
    public void testEmployeeDaoUpdate() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        Employee emp = new Employee(10008,"Tom",2200.0,22);
        int result = employee.update(emp);
        System.out.println(result);
    }

    @Test
    public void testEmployeeDaoDelete() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        int result = employee.delete(10008);
        System.out.println(result);
    }
}

