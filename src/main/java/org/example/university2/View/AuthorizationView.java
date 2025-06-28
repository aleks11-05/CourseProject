package org.example.university2.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.university2.Controller.AuthorizationController;
import org.example.university2.DAO.UserDAO;
import org.example.university2.DAO.RoleDAO;
import org.example.university2.Models.User;
import org.example.university2.Models.Role;
import java.util.Optional;

public class AuthorizationView {

    private final AuthorizationController controller;
    private final TextField usernameField;
    private final PasswordField passwordField;
    private final Button loginButton;
    private final Button registerButton;
    private final Label statusLabel;
    private boolean isAdminLoggedIn = false;

    public AuthorizationView(AuthorizationController controller) {
        this.controller = controller;
        this.usernameField = new TextField();
        this.passwordField = new PasswordField();
        this.loginButton = new Button("Войти");
        this.registerButton = new Button("Зарегистрироваться");
        this.statusLabel = new Label();

        setupUI();
        setupEventHandlers();
    }

    private void setupUI() {
        usernameField.setPromptText("Введите логин");
        usernameField.setPrefWidth(250);

        passwordField.setPromptText("Введите пароль");
        passwordField.setPrefWidth(250);

        loginButton.setPrefWidth(120);
        loginButton.setPrefHeight(40);
        registerButton.setPrefWidth(120);
        registerButton.setPrefHeight(40);

        statusLabel.setStyle("-fx-text-fill: red;");
    }

    private void setupEventHandlers() {
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegister());

        usernameField.setOnAction(e -> handleLogin());
        passwordField.setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (!validateInput(username, password)) {
            return;
        }

        controller.authenticate(username, password);
    }

    private boolean validateInput(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showError("Пожалуйста, заполните все поля");
            return false;
        }

        if (username.length() < 3) {
            showError("Логин должен содержать минимум 3 символа");
            return false;
        }

        if (password.length() < 3) {
            showError("Пароль должен содержать минимум 3 символа");
            return false;
        }

        return true;
    }

    private void handleRegister() {
        showRegisterDialog();
    }

    private void showRegisterDialog() {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Регистрация нового пользователя");
        dialog.setHeaderText("Введите данные для регистрации");

        ButtonType registerButtonType = new ButtonType("Зарегистрироваться", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Введите логин");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Введите пароль");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Подтвердите пароль");

        ComboBox<Role> roleCombo = new ComboBox<>();
        roleCombo.setPromptText("Выберите роль");
        loadRolesToComboBox(roleCombo);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        grid.add(new Label("Логин:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Пароль:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("Подтвердите пароль:"), 0, 2);
        grid.add(confirmPasswordField, 1, 2);
        grid.add(new Label("Роль:"), 0, 3);
        grid.add(roleCombo, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == registerButtonType) {
                return validateAndCreateUser(
                        usernameField.getText().trim(),
                        passwordField.getText().trim(),
                        confirmPasswordField.getText().trim(),
                        roleCombo.getValue()
                );
            }
            return null;
        });

        Optional<User> result = dialog.showAndWait();
        result.ifPresent(this::registerUser);
    }

    private void loadRolesToComboBox(ComboBox<Role> comboBox) {
        try {
            RoleDAO roleDAO = new RoleDAO();
            comboBox.getItems().addAll(roleDAO.findAll().stream()
                    .filter(role -> isAdminLoggedIn || !role.getRole().equalsIgnoreCase("Администратор"))
                    .filter(role -> !role.getRole().equalsIgnoreCase("Учитель"))
                    .toList());

            comboBox.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Role item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item != null ? item.getRole() : null);
                }
            });

            comboBox.setButtonCell(comboBox.getCellFactory().call(null));
        } catch (Exception e) {
            showError("Ошибка загрузки ролей: " + e.getMessage());
        }
    }

    private User validateAndCreateUser(String username, String password, String confirmPassword, Role role) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Заполните все поля");
            return null;
        }

        if (username.length() < 3) {
            showError("Логин должен содержать минимум 3 символа");
            return null;
        }

        if (password.length() < 3) {
            showError("Пароль должен содержать минимум 3 символа");
            return null;
        }

        if (!password.equals(confirmPassword)) {
            showError("Пароли не совпадают");
            return null;
        }

        if (role == null) {
            showError("Выберите роль");
            return null;
        }

        if (role.getRole().equalsIgnoreCase("Администратор") && !isAdminLoggedIn) {
            showError("Только администраторы могут создавать других администраторов");
            return null;
        }

        return new User(username, password, role.getId());
    }

    private void registerUser(User user) {
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.create(user);
            showSuccess("Пользователь успешно зарегистрирован!");
            clearFields();
        } catch (Exception e) {
            showError("Ошибка регистрации: " + e.getMessage());
        }
    }

    public void setAdminLoggedIn(boolean isAdmin) {
        this.isAdminLoggedIn = isAdmin;
    }

    public void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
    }

    public void showSuccess(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    public void clearFields() {
        usernameField.clear();
        passwordField.clear();
        statusLabel.setText("");
    }

    public VBox getView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: #f5f5f5;");

        Label titleLabel = new Label("Авторизация");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setAlignment(Pos.CENTER);

        formGrid.add(new Label("Логин:"), 0, 0);
        formGrid.add(usernameField, 1, 0);
        formGrid.add(new Label("Пароль:"), 0, 1);
        formGrid.add(passwordField, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(loginButton, registerButton);

        root.getChildren().addAll(titleLabel, formGrid, buttonBox, statusLabel);
        return root;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }
}