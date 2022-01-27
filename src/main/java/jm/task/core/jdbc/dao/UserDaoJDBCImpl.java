package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.util.List;
import java.sql.Connection;
import jm.task.core.jdbc.util.Util;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class UserDaoJDBCImpl implements UserDao {

    private String SQLcreate = "CREATE TABLE IF NOT EXISTS `users` (\n" +
            "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NULL,\n" +
            "  `lastName` VARCHAR(45) NULL,\n" +
            "  `age` TINYINT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection conn = Util.getNewConnection(); Statement stt = conn.createStatement()) {
            stt.execute(SQLcreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getNewConnection(); Statement stt = conn.createStatement()) {
            stt.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getNewConnection(); Statement stt = conn.createStatement()) {
            stt.execute(String.format("INSERT users(name, lastName, age) VALUES ('%s', '%s', '%d')", name, lastName, age));
            System.out.printf("User с именем – %s добавлен в базу данных%n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getNewConnection(); Statement stt = conn.createStatement()) {
            stt.execute(String.format("DELETE FROM users WHERE id = %d", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection conn = Util.getNewConnection(); Statement stt = conn.createStatement(); ResultSet rs = stt.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                result.add(new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getNewConnection(); Statement stt = conn.createStatement()) {
            stt.execute("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
