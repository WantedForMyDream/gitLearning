package jdbc01;

import java.sql.*;

public class jdbcUtils {
    public static final String connectionURL ="jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";//解决时区错误
    public static final String username="root";
    public static final String password="190516";

    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//使用什么驱动连接数据库

            return DriverManager.getConnection(connectionURL,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet rs, PreparedStatement stmt,Connection con)
    {
        try {
            if(rs!=null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(stmt!=null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(con!=null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
