package org.example.university2.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.university2.Controller.AdminMainController;
import javafx.stage.Stage;
import org.example.university2.Controller.AuthorizationController;

public class AdminMainView {

    private final AdminMainController controller;
    private final TabPane tabPane;
    private final Stage primaryStage;

    public AdminMainView(AdminMainController controller, Stage primaryStage) {
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.tabPane = new TabPane();

        setupTabs();
    }

    private void setupTabs() {

        Tab teachersContactsTab = new Tab("Преподаватели и контакты");
        teachersContactsTab.setContent(controller.getTeachersContactsController().getView().getView());
        teachersContactsTab.setClosable(false);

        Tab groupTypeSubjectTab = new Tab("Группы, типы и предметы");
        groupTypeSubjectTab.setContent(controller.getGroupTypeSubjectController().getView().getView());
        groupTypeSubjectTab.setClosable(false);

        Tab academicHoursTab = new Tab("Академические часы");
        academicHoursTab.setContent(controller.getAcademicHoursController().getView().getView());
        academicHoursTab.setClosable(false);

        Tab usersTab = new Tab("Управление пользователями");
        UserMainView userMainView = new UserMainView(controller.getUserMainController());
        usersTab.setContent(userMainView.getView());
        usersTab.setClosable(false);

        tabPane.getTabs().addAll(teachersContactsTab, groupTypeSubjectTab, academicHoursTab, usersTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    public VBox getView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        Button logoutButton = new Button("Выйти");
        logoutButton.setOnAction(e -> handleLogout());
        logoutButton.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.TOP_RIGHT);
        headerBox.getChildren().add(logoutButton);

        root.getChildren().addAll(headerBox, tabPane);
        return root;
    }

    private void handleLogout() {
        AuthorizationController authController = new AuthorizationController(primaryStage);
        AuthorizationView authView = authController.getView();
        authView.setAdminLoggedIn(false);

        Scene scene = new Scene(authView.getView(), 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Авторизация");
        primaryStage.setMaximized(false);
        primaryStage.centerOnScreen();
    }
}