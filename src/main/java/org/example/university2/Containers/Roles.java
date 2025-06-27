package org.example.university2.Containers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.RoleDAO;
import org.example.university2.Models.Role;

import java.util.List;

public class Roles {
    private final RoleDAO dao;
    private final ObservableList<Role> roles = FXCollections.observableArrayList();

    public Roles() {
        dao = new RoleDAO();
        load();
    }

    public ObservableList<Role> getRoles() {
        return roles;
    }

    public void load() {
        roles.clear();
        List<Role> all = dao.findAll();
        roles.addAll(all);
    }

    public void add(Role role) {
        dao.create(role);
        roles.add(role);
    }

    public void update(Role role) {
        dao.update(role);
    }

    public void delete(Role role) {
        dao.delete(role.getId());
        roles.remove(role);
    }
}
