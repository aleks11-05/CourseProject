package org.example.university2.Models;

import javafx.beans.property.*;

public class User {
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;
    private IntegerProperty role_id;

    public User(int id, String username, String password, int role_id) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role_id = new SimpleIntegerProperty(role_id);
    }
    public User(String username, String password, int role_id) {
        this.id = new SimpleIntegerProperty(0); // пока 0, назначится после вставки
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role_id = new SimpleIntegerProperty(role_id);
    }

    // Геттеры и сеттеры для JavaFX
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getUsername() { return username.get(); }
    public void setUsername(String username) { this.username.set(username); }
    public StringProperty usernameProperty() { return username; }

    public String getPassword() { return password.get(); }
    public void setPassword(String password) { this.password.set(password); }
    public StringProperty passwordProperty() { return password; }

    public int getRoleId() { return role_id.get(); }
    public void setRoleId(int roleId) { this.role_id.set(roleId); }
    public IntegerProperty roleIdProperty() { return role_id; }
}
