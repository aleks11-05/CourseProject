package org.example.university2.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.UserDAO;
import org.example.university2.Models.User;

public class UserMainController {
    private final TeachersContactsController teachersContactsController;
    private final GroupTypeSubjectController groupTypeSubjectController;
    private final AcademicHoursController academicHoursController;
    private final ObservableList<User> usersList = FXCollections.observableArrayList();
    private final UserDAO userDAO = new UserDAO();
    private final boolean isReadOnly;

    public UserMainController(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
        this.teachersContactsController = new TeachersContactsController(isReadOnly);
        this.groupTypeSubjectController = new GroupTypeSubjectController(isReadOnly);
        this.academicHoursController = new AcademicHoursController(isReadOnly);
        loadUsers();
    }

    private void loadUsers() {
        usersList.setAll(userDAO.findAll());
    }

    public TeachersContactsController getTeachersContactsController() {
        return teachersContactsController;
    }

    public GroupTypeSubjectController getGroupTypeSubjectController() {
        return groupTypeSubjectController;
    }

    public AcademicHoursController getAcademicHoursController() {
        return academicHoursController;
    }

    public ObservableList<User> getUsersList() {
        return FXCollections.unmodifiableObservableList(usersList);
    }
}