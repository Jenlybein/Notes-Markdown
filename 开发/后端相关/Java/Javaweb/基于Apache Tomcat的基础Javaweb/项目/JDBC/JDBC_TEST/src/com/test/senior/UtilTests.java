package test.schdule.test;

import com.test.advance.pojo.Employee;
import com.test.senior.Util.JDBCUtil;
import com.test.senior.Util.JDBCUtil_v2;
import com.test.senior.dao.EmployeeDAO;
import com.test.senior.dao.impl.EmployeeDAOimpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UtilTests {
    @Test
    public void testJDBCUtil_v1() throws SQLException {
        Connection conn1 = JDBCUtil.getConnection();
        Connection conn2 = JDBCUtil.getConnection();
        Connection conn3 = JDBCUtil.getConnection();
        System.out.println(conn1);
        System.out.println(conn2);
        System.out.println(conn3);
        //CRUD...
        JDBCUtil.release(conn1);
        JDBCUtil.release(conn2);
        JDBCUtil.release(conn3);
    }

    @Test
    public void testJDBCUtil_v2() throws SQLException {
        Connection conn1 = JDBCUtil_v2.getConnection();
        Connection conn2 = JDBCUtil_v2.getConnection();
        Connection conn3 = JDBCUtil_v2.getConnection();
        System.out.println(conn1);
        System.out.println(conn2);
        System.out.println(conn3);
        //CRUD...
        JDBCUtil_v2.release();
    }
}
