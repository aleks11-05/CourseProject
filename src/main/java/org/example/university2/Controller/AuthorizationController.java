package org.example.university2.Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.university2.Controller.AuthorizationController;


import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.university2.View.AuthorizationView;
import org.example.university2.View.AdminMainView;
import org.example.university2.View.UserMainView;

public class AuthorizationController {

    private final AuthorizationView view;
    private final Stage primaryStage;

    public AuthorizationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new AuthorizationView(this);
    }

    public void authenticate(String login, String password) {
        if (isAdmin(login, password)) {
            showAdminView();
        } else if (isUser(login, password)) {
            showUserView();
        } else {
            view.showError("Неверный логин или пароль");
        }
    }

    private boolean isAdmin(String login, String password) {
        return "admin".equals(login) && "admin123".equals(password);
    }

    private boolean isUser(String login, String password) {
        return "user".equals(login) && "user123".equals(password);
    }

    private void showAdminView() {
        AdminMainController adminController = new AdminMainController();
        AdminMainView adminView = new AdminMainView(adminController);

        Scene scene = new Scene(adminView.getView(), 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Административная панель");
        primaryStage.setMaximized(true);

        view.showSuccess("Добро пожаловать, администратор!");
    }

    private void showUserView() {
        UserMainController userController = new UserMainController();
        UserMainView userView = new UserMainView(userController);

        Scene scene = new Scene(userView.getView(), 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Пользовательская панель");
        primaryStage.setMaximized(true);

        view.showSuccess("Добро пожаловать, пользователь!");
    }

    public AuthorizationView getView() {
        return view;
    }
}