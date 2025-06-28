package org.example.university2.View;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.university2.Models.Role;
import org.example.university2.Models.User;
import org.example.university2.Containers.Users;

public class UserView {
    private final Users usersModel;
    private final BorderPane view;
    private final TableView<User> userTable;
    private final ComboBox<Role> roleComboBox;

    public UserView(Users usersModel) {
        this.usersModel = usersModel;

        userTable = new TableView<>();
        setupUserTable();

        roleComboBox = new ComboBox<>();
        Button addButton = new Button("Добавить");
        Button deleteButton = new Button("Удалить");
        Button refreshButton = new Button("Обновить");

        setupEventHandlers(addButton, deleteButton, refreshButton);

        HBox toolbar = new HBox(10, roleComboBox, addButton, deleteButton, refreshButton);
        toolbar.setPadding(new Insets(10, 10, 10, 10));

        view = new BorderPane();
        view.setCenter(userTable);
        view.setBottom(toolbar);

        refreshData();
    }

    private void setupUserTable() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Логин");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Роль");
        roleColumn.setCellValueFactory(cellData -> {
            User user = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(
                    String.valueOf(user.getRoleId()) // Или название роли, если есть связь
            );
        });

        userTable.getColumns().addAll(idColumn, usernameColumn, roleColumn);
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setupEventHandlers(Button addButton, Button deleteButton, Button refreshButton) {
        addButton.setOnAction(e -> {
            User newUser = new User(0, "newuser", "password", 2); // role_id=2 (например, для 'user')
            usersModel.addUser(newUser);
            refreshData();
        });

        deleteButton.setOnAction(e -> {
            User selectedUser = userTable.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                usersModel.deleteUser(selectedUser);
                refreshData();
            } else {
                showAlert("Не выбран пользователь", "Пожалуйста, выберите пользователя для удаления");
            }
        });

        refreshButton.setOnAction(e -> refreshData());
    }

    private void refreshData() {
        ObservableList<User> users = usersModel.getUsers();
        userTable.setItems(users);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public BorderPane getView() {
        return view;
    }
}