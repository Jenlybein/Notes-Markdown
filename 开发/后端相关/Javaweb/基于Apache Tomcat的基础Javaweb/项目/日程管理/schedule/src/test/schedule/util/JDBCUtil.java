package test.schedule.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil{
    //创建连接池引用，因为要提供给当前项目全局使用，所以创建为静态的。
    private static final DataSource ds;
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //在项目启动时，即创建连接池对象，赋值给dataSource
    static{
        try {
            Properties ppt = new Properties();
            InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
            ppt.load(in);

            ds = DruidDataSourceFactory.createDataSource(ppt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 向外提供连接池的方法
    public static DataSource getDataSource(){
        return ds;
    }

    //对外提供获取连接的静态方法
    public static Connection getConnection() throws SQLException {
        // ThreadLocal中获取Connection
        Connection conn = threadLocal.get();
        // ThreadLocal中无conn，即第一次获取
        if(conn == null){
            conn = ds.getConnection();
            threadLocal.set(conn);
        }
        return conn;
    }
    //对外提供回收连接的静态方法
    public static void release() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn != null){
            threadLocal.remove();
            // 如果开启了事务的手动提交，操作完毕后，归还给连接池之前，要将事务自动提交改为true
            conn.setAutoCommit(true);
            conn.close();
        }
    }
}
