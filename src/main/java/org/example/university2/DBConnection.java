package org.example.university2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static DBConnection myInstance;
    private Connection conn;


    private final String URL = "jdbc:mysql://localhost:3306/CourseProject3";
    private final String USER = "root";
    private final String PASSWORD = "";


    DBConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Успешное подключение к базе данных.");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе: " + e.getMessage());
        }
    }

    public static DBConnection getInstance() {
        if (myInstance == null) {
            myInstance = new DBConnection();
        }
        return myInstance;
    }

    public Connection getConnection() {
        return conn;
    }
}
