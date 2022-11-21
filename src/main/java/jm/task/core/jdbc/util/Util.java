package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/mysqlbase?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mysqlbase";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";
    private static SessionFactory sessionFactory = null;
    public static SessionFactory getConnectionHib() {

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", HOST)
                    .setProperty("hibernate.connection.username", DB_USERNAME)
                    .setProperty("hibernate.connection.password", DB_PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.c3p0.min_size","5")
                    .setProperty("hibernate.c3p0.max_size","200")
                    .setProperty("hibernate.c3p0.max_statements","200");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        Connection connection ;
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.println("Подключилось");
        } catch (SQLException e) {
            System.out.println("Не подключён");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
