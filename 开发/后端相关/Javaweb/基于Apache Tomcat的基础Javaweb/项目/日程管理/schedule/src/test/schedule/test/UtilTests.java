package test.schedule.test;

import org.junit.Test;
import test.schedule.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class UtilTests {
    @Test
    public void testJDBCUtil() throws SQLException {
        Connection conn1 = JDBCUtil.getConnection();
        Connection conn2 = JDBCUtil.getConnection();
        Connection conn3 = JDBCUtil.getConnection();
        System.out.println(conn1);
        System.out.println(conn2);
        System.out.println(conn3);
        //CRUD...
        JDBCUtil.release();
    }
}
