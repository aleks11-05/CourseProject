package org.example.university2.Controller;

import javafx.stage.Stage;

public class AdminMainController {
    private final TeachersContactsController teachersContactsController;
    private final GroupTypeSubjectController groupTypeSubjectController;
    private final AcademicHoursController academicHoursController;
    private final UserMainController userMainController;
    private final Stage primaryStage;

    public AdminMainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.teachersContactsController = new TeachersContactsController(false);
        this.groupTypeSubjectController = new GroupTypeSubjectController(false);
        this.academicHoursController = new AcademicHoursController(false);
        this.userMainController = new UserMainController(false);
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

    public UserMainController getUserMainController() {
        return userMainController;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}