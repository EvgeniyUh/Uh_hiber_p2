package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getNewConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/sakila?useSSL=false";

        String user = "root";

        String passwd = "volga1364!";

        return DriverManager.getConnection(url, user, passwd);

    }

}
