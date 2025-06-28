package org.example.university2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.university2.Controller.AuthorizationController;
import java.sql.Connection;
import java.sql.SQLException;
import org.example.university2.DAO.UserDAO;
import org.example.university2.Models.User;
import java.util.List;


public class Main extends Application {
    private Connection connection;
    private UserDAO userDAO;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        try {
            connection = DBConnection.getInstance().getConnection();
            userDAO = new UserDAO();

            List<User> users = userDAO.findAll();
            boolean adminExists = users.stream().anyMatch(u -> "admin".equals(u.getUsername()));

            if (!adminExists) {
                User adminUser = new User("admin", "admin123", 1);
                userDAO.create(adminUser);
                System.out.println("Создан тестовый администратор: admin/admin123");
            } else {
                System.out.println("Администратор уже существует в базе данных");
            }
        } catch (Exception e) {
            System.err.println("Ошибка инициализации базы данных: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        AuthorizationController authController = new AuthorizationController(primaryStage);

        Scene scene = new Scene(authController.getView().getView(), 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Авторизация - Университетская система");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с базой данных закрыто");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при закрытии соединения с БД: " + e.getMessage());
        }
    }
}