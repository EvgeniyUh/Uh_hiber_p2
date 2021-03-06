package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory factory = createSessionFactory();

    public static Session getSession() {
        return factory.openSession();
    }

    public static SessionFactory createSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "volga1364!");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/sakila");
        Configuration cfg = new Configuration();
        cfg.setProperties(properties);
        cfg.addAnnotatedClass(User.class);
        return cfg.buildSessionFactory();
    }



    public static Connection getNewConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/sakila?useSSL=false";

        String user = "root";

        String passwd = "volga1364!";

        return DriverManager.getConnection(url, user, passwd);

    }

}
