package org.example.university2.Controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.university2.DAO.UserDAO;
import org.example.university2.Models.User;
import org.example.university2.View.AuthorizationView;
import org.example.university2.View.UserMainView;
import java.util.List;

public class AuthorizationController {

    private final AuthorizationView view;
    private final Stage primaryStage;
    private final UserDAO userDAO;

    public AuthorizationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.userDAO = new UserDAO();
        this.view = new AuthorizationView(this);
    }

    public void authenticate(String login, String password) {
        System.out.println("Попытка авторизации: логин='" + login + "', пароль='" + password + "'");

        User user = findUser(login, password);

        if (user != null) {
            System.out.println("Авторизация успешна для пользователя: " + user.getUsername() + " с ролью: " + user.getRoleId());

            if (user.getRoleId() == 1) {
                view.setAdminLoggedIn(true);
                showAdminView();
            } else {
                view.setAdminLoggedIn(false);
                showUserView();
            }
        } else {
            System.out.println("Авторизация не удалась");
            view.showError("Неверный логин или пароль");
        }
    }

    private User findUser(String username, String password) {
        try {
            List<User> users = userDAO.findAll();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при поиске пользователя: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private void showUserView() {
        UserMainController userController = new UserMainController(true);
        UserMainView userView = new UserMainView(userController);

        Scene scene = new Scene(userView.getView(), 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Пользовательская панель");
    }

    private void showAdminView() {
        UserMainController userController = new UserMainController(false);
        UserMainView userView = new UserMainView(userController);

        Scene scene = new Scene(userView.getView(), 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Административная панель");
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public AuthorizationView getView() {
        return view;
    }
}