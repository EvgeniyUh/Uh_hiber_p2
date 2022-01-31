package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Подготовка");
        UserService sh = new UserServiceImpl();
        System.out.println("Соединение выполнено");
        sh.createUsersTable();
        sh.saveUser("Vasya", "Vasilev", (byte) 10);
        sh.saveUser("Ivan", "Ivanov", (byte) 20);
        sh.saveUser("Petr", "Petrov", (byte) 30);
        sh.saveUser("Mihail", "Mihailov", (byte) -1);
        sh.getAllUsers().forEach(System.out::println);
        sh.cleanUsersTable();
        sh.dropUsersTable();
    }
}
