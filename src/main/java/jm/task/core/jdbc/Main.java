package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userS = new UserServiceImpl();

        User user1 = new User("Leo","Drozd", (byte) 29);
        User user2 = new User("Kleo","Saytama",(byte) 20);
        User user3 = new User("Moleo","Mengo",(byte) 99);
        User user4 = new User("Galeo","Largonya",(byte) 10);


        userS.createUsersTable();
        userS.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userS.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userS.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userS.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        System.out.println("   ");

        for (User user : userS.getAllUsers()) {
            System.out.println(user.toString());
        }

        userS.cleanUsersTable();
        userS.dropUsersTable();

    }
}
