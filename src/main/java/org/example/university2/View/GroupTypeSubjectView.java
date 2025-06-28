package org.example.university2.View;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.university2.Containers.*;
import org.example.university2.Models.*;

import java.util.Optional;

public class GroupTypeSubjectView {

    private final Groupas groupas;
    private final TypeLessons typeLessons;
    private final Subjects subjects;

    private final TableView<Groupa> groupTable;
    private final TableView<TypeLesson> typeLessonsTable;
    private final TableView<Subject> subjectTable;
    private final boolean isReadOnly;

    public GroupTypeSubjectView(Groupas groupas, TypeLessons typeLessons, Subjects subjects, boolean isReadOnly) {
        this.groupas = groupas;
        this.typeLessons = typeLessons;
        this.subjects = subjects;

        this.groupTable = new TableView<>(groupas.getGroupas());
        this.typeLessonsTable = new TableView<>(typeLessons.getLessonTypes());
        this.subjectTable = new TableView<>(subjects.getSubjects());

        this.isReadOnly = isReadOnly;

        setupGroupTable();
        setupTypeLessonsTable();
        setupSubjectTable();
    }

    private void setupGroupTable() {
        TableColumn<Groupa, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));

        TableColumn<Groupa, String> numberCol = new TableColumn<>("Номер группы");
        numberCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getGroupaNumber()));

        TableColumn<Groupa, Number> sizeCol = new TableColumn<>("Размер");
        sizeCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getSize()));

        groupTable.getColumns().addAll(idCol, numberCol, sizeCol);
    }

    private void setupTypeLessonsTable() {
        TableColumn<TypeLesson, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));

        TableColumn<TypeLesson, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));

        typeLessonsTable.getColumns().addAll(idCol, nameCol);
    }

    private void setupSubjectTable() {
        TableColumn<Subject, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));

        TableColumn<Subject, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));

        subjectTable.getColumns().addAll(idCol, nameCol);
    }

    public VBox getView() {
        Button addGroup = new Button("Добавить группу");
        Button updateGroup = new Button("Обновить");
        Button deleteGroup = new Button("Удалить");

        if (isReadOnly) {

            addGroup.setVisible(false);
            updateGroup.setVisible(false);
            deleteGroup.setVisible(false);
        } else {
            addGroup.setOnAction(e -> {
                Dialog<Groupa> dialog = createGroupDialog();
                Optional<Groupa> result = dialog.showAndWait();
                result.ifPresent(groupas::add);
            });

            updateGroup.setOnAction(e -> {
                Groupa selected = groupTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setSize(selected.getSize() + 1);
                    groupas.update(selected);
                }
            });

            deleteGroup.setOnAction(e -> {
                Groupa selected = groupTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    groupas.delete(selected);
                }
            });
        }


        HBox groupButtons = new HBox(5, addGroup, updateGroup, deleteGroup);

        VBox groupsBox = new VBox(5, groupTable, groupButtons);
        groupsBox.setPadding(new Insets(10));
        groupsBox.setPrefWidth(400);

        // Кнопки для типов занятий
        Button addTypeLesson = new Button("Добавить тип занятия");
        Button updateTypeLesson = new Button("Обновить");
        Button deleteTypeLesson = new Button("Удалить");

        if (isReadOnly) {

            addTypeLesson.setVisible(false);
            updateTypeLesson.setVisible(false);
            deleteTypeLesson.setVisible(false);
        } else {
            addTypeLesson.setOnAction(e -> {
                Dialog<TypeLesson> dialog = createTypeLessonDialog();
                Optional<TypeLesson> result = dialog.showAndWait();
                result.ifPresent(typeLessons::add);
            });

            updateTypeLesson.setOnAction(e -> {
                TypeLesson selected = typeLessonsTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setName("Обновленный " + selected.getName());
                    typeLessons.update(selected);
                }
            });

            deleteTypeLesson.setOnAction(e -> {
                TypeLesson selected = typeLessonsTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    typeLessons.delete(selected);
                }
            });
        }


        HBox typeLessonsButtons = new HBox(5, addTypeLesson, updateTypeLesson, deleteTypeLesson);

        VBox typeLessonsBox = new VBox(5, typeLessonsTable, typeLessonsButtons);
        typeLessonsBox.setPadding(new Insets(10));
        typeLessonsBox.setPrefWidth(400);

        // Кнопки для предметов
        Button addSubject = new Button("Добавить предмет");
        Button updateSubject = new Button("Обновить");
        Button deleteSubject = new Button("Удалить");

        if (isReadOnly) {

            addSubject.setVisible(false);
            updateSubject.setVisible(false);
            deleteSubject.setVisible(false);
        } else {
            addSubject.setOnAction(e -> {
                Dialog<Subject> dialog = createSubjectDialog();
                Optional<Subject> result = dialog.showAndWait();
                result.ifPresent(subjects::add);
            });

            updateSubject.setOnAction(e -> {
                Subject selected = subjectTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setName("Обновленный " + selected.getName());
                    subjects.update(selected);
                }
            });

            deleteSubject.setOnAction(e -> {
                Subject selected = subjectTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    subjects.delete(selected);
                }
            });
        }



        HBox subjectButtons = new HBox(5, addSubject, updateSubject, deleteSubject);

        VBox subjectsBox = new VBox(5, subjectTable, subjectButtons);
        subjectsBox.setPadding(new Insets(10));
        subjectsBox.setPrefWidth(400);

        HBox root = new HBox(10, groupsBox, typeLessonsBox, subjectsBox);
        root.setPadding(new Insets(10));

        return new VBox(root);
    }


    private Dialog<Groupa> createGroupDialog() {
        Dialog<Groupa> dialog = new Dialog<>();
        dialog.setTitle("Новая группа");
        dialog.setHeaderText("Введите данные");

        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        TextField numberField = new TextField();
        TextField sizeField = new TextField();

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Номер группы:"), numberField,
                new Label("Размер:"), sizeField
        );
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                try {
                    return new Groupa(
                            0, // ID будет сгенерирован
                            numberField.getText(),
                            Integer.parseInt(sizeField.getText())
                    );
                } catch (NumberFormatException e) {
                    showError("Ошибка", "Размер должен быть числом");
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    private Dialog<TypeLesson> createTypeLessonDialog() {
        Dialog<TypeLesson> dialog = new Dialog<>();
        dialog.setTitle("Новый тип занятия");
        dialog.setHeaderText("Введите данные");

        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        TextField nameField = new TextField();

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Название:"), nameField
        );
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                return new TypeLesson(0, nameField.getText());
            }
            return null;
        });

        return dialog;
    }

    private Dialog<Subject> createSubjectDialog() {
        Dialog<Subject> dialog = new Dialog<>();
        dialog.setTitle("Новый предмет");
        dialog.setHeaderText("Введите данные");

        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        TextField nameField = new TextField();

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Название:"), nameField
        );
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                return new Subject(0, nameField.getText());
            }
            return null;
        });

        return dialog;
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
