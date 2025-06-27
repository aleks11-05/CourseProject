package org.example.university2.Containers;

import org.example.university2.DAO.UserDAO;
import org.example.university2.Models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Users {
    private final UserDAO userDAO;
    private final ObservableList<User> users;

    public Users() {
        userDAO = new UserDAO();
        users = FXCollections.observableArrayList();
        loadUsersFromDB();
    }

    private void loadUsersFromDB() {
        users.clear();
        users.addAll(userDAO.findAll());
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        userDAO.create(user);
        users.add(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user.getId());
        users.remove(user);
    }
}

