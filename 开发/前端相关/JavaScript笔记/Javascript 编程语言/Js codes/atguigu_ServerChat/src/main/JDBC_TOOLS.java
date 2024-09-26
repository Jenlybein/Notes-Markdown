package src.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class JDBC_TOOLS {
    // JDBC驱动程序和数据库URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  // MySQL驱动程序
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_sql"; // 数据库URL

    // 数据库用户和密码
    static final String USER = "root";
    static final String PASS = "86503358299";

    // 获取数据库连接方法
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // 查询方法通用处理
    private static JSONArray executeQuery(String sql, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // 设置查询参数
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            // 执行查询
            try (ResultSet rs = stmt.executeQuery()) {
                JSONArray jsonArray = new JSONArray();
                while (rs.next()) {
                    jsonArray.put(rs.getObject(1));
                }
                return jsonArray;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    // 获取指定用户的好友列表
    public static JSONArray getUserFriends(int user_id) {
        String sql = "SELECT Username FROM user_friends x,users y WHERE x.user_id = ? and x.friend_id = y.UserID";
        return executeQuery(sql, user_id);
    }

    // 获取指定用户的群组列表
    public static JSONArray getUserGroups(int user_id) {
        String sql = "SELECT group_name FROM user_groups WHERE user_id = ?";
        return executeQuery(sql, user_id);
    }

    // 获取指定用户好友的状态列表
    public static JSONArray getUserFriendsStates(JSONArray friends) {
        JSONArray friendStates = new JSONArray();
        String sql = "SELECT state FROM user_state WHERE user_id = (SELECT UserID FROM users WHERE Username = ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < friends.length(); i++) {
                String friendUsername = friends.getString(i);
                stmt.setString(1, friendUsername);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        friendStates.put(rs.getString("state"));
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return friendStates;
    }

    // 检查用户登录信息
    public static int checkUserCredentials(String username, String password) {
        String sql = "SELECT UserID FROM users WHERE username = ? AND password = ? LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt("UserID") : -1;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        }
    }
}
