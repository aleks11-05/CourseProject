package org.example.university2.View;


import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.example.university2.Controller.UserMainController;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class UserMainView {
    private final UserMainController controller;
    private final TabPane tabPane;

    public UserMainView(UserMainController controller) {
        this.controller = controller;
        this.tabPane = new TabPane();

        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.FIXED);

        setupTabs();
    }

    private void setupTabs() {
        VBox teachersView = controller.getTeachersContactsController().getView().getView();
        VBox groupsView = controller.getGroupTypeSubjectController().getView().getView();
        VBox hoursView = controller.getAcademicHoursController().getView().getView();

        disableEditingInChildren(teachersView);
        disableEditingInChildren(groupsView);
        disableEditingInChildren(hoursView);

        Tab teachersContactsTab = createReadOnlyTab("Преподаватели и контакты", teachersView);
        Tab groupTypeSubjectTab = createReadOnlyTab("Группы, типы и предметы", groupsView);
        Tab academicHoursTab = createReadOnlyTab("Академические часы", hoursView);

        tabPane.getTabs().addAll(teachersContactsTab, groupTypeSubjectTab, academicHoursTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    private Tab createReadOnlyTab(String title, Node content) {
        Tab tab = new Tab(title);
        tab.setContent(content);
        tab.setClosable(false);
        return tab;
    }

    private void disableEditingInChildren(Parent parent) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof TableView) {
                TableView<?> tableView = (TableView<?>) node;
                tableView.setEditable(false);
                tableView.getColumns().forEach(col -> col.setEditable(false));
            } else if (node instanceof TextInputControl) {
                ((TextInputControl) node).setEditable(false);
            } else if (node instanceof ComboBoxBase) {
                ((ComboBoxBase<?>) node).setEditable(false);
            } else if (node instanceof Parent) {
                disableEditingInChildren((Parent) node);
            }
        }
    }

    public VBox getView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(tabPane);
        return root;
    }
}