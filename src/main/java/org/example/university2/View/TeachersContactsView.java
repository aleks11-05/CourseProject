package org.example.university2.View;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.Optional;
import org.example.university2.Containers.Contacts;
import org.example.university2.Containers.Teachers;
import org.example.university2.Models.Contact;
import org.example.university2.Models.Teacher;

public class TeachersContactsView {

    private final Teachers teachers;
    private final Contacts contacts;

    private final TableView<Teacher> teacherTable;
    private final TableView<Contact> contactTable;
    private final boolean isUserView;

    public TeachersContactsView(Teachers teachers, Contacts contacts, boolean isUserView) {
        this.teachers = teachers;
        this.contacts = contacts;
        this.isUserView = isUserView;

        this.teacherTable = new TableView<>(teachers.getTeachers());
        this.contactTable = new TableView<>(contacts.getContacts());

        setupTeacherTable();
        setupContactTable();
    }

    private void setupTeacherTable() {
        TableColumn<Teacher, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));

        TableColumn<Teacher, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getFirstName()));

        TableColumn<Teacher, String> middleNameCol = new TableColumn<>("Отчество");
        middleNameCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getMiddleName()));

        TableColumn<Teacher, String> lastNameCol = new TableColumn<>("Фамилия");
        lastNameCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLastName()));

        TableColumn<Teacher, String> positionCol = new TableColumn<>("Должность");
        positionCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getPosition()));

        TableColumn<Teacher, String> degreeCol = new TableColumn<>("Ученая степень");
        degreeCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getDegree()));

        TableColumn<Teacher, String> residenceCol = new TableColumn<>("Проживание");
        residenceCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getResidence()));

        TableColumn<Teacher, Number> experienceCol = new TableColumn<>("Опыт (лет)");
        experienceCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getExperience()));

        teacherTable.getColumns().addAll(idCol, firstNameCol, middleNameCol, lastNameCol,
                positionCol, degreeCol, residenceCol, experienceCol);
    }

    private void setupContactTable() {
        TableColumn<Contact, Number> teacherIdCol = new TableColumn<>("ID преподавателя");
        teacherIdCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getTeacherId()));

        TableColumn<Contact, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getEmail()));

        TableColumn<Contact, String> phoneCol = new TableColumn<>("Телефон");
        phoneCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getPhone()));

        contactTable.getColumns().addAll(teacherIdCol, emailCol, phoneCol);
    }

    public VBox getView() {
        // Кнопки преподавателя
        Button addTeacher = new Button("Добавить преподавателя");
        Button updateTeacher = new Button("Обновить");
        Button deleteTeacher = new Button("Удалить");
        if (isUserView) {

            addTeacher.setVisible(false);
            updateTeacher.setVisible(false);
            deleteTeacher.setVisible(false);
        } else {
            addTeacher.setOnAction(e -> {
                Dialog<Teacher> dialog = createTeacherDialog();
                Optional<Teacher> result = dialog.showAndWait();
                result.ifPresent(teachers::add);
            });

            updateTeacher.setOnAction(e -> {
                Teacher selected = teacherTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setExperience(selected.getExperience() + 1);
                    teachers.update(selected);
                } else {
                    showError("Ошибка", "Выберите преподавателя");
                }
            });

            deleteTeacher.setOnAction(e -> {
                Teacher selected = teacherTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    teachers.delete(selected);
                } else {
                    showError("Ошибка", "Выберите преподавателя");
                }
            });
        }



        HBox teacherButtons = new HBox(5, addTeacher, updateTeacher, deleteTeacher);
        VBox teachersBox = new VBox(5, teacherTable, teacherButtons);
        teachersBox.setPadding(new Insets(10));
        teachersBox.setPrefWidth(600);

        // Кнопки контакта
        Button addContact = new Button("Добавить контакт");
        Button updateContact = new Button("Обновить");
        Button deleteContact = new Button("Удалить");

        if (isUserView) {

            addContact.setVisible(false);
            updateContact.setVisible(false);
            deleteContact.setVisible(false);
        } else {
            addContact.setOnAction(e -> {
                Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
                if (selectedTeacher == null) {
                    showError("Ошибка", "Выберите преподавателя");
                    return;
                }

                Dialog<Contact> dialog = createContactDialog(selectedTeacher);
                Optional<Contact> result = dialog.showAndWait();
                result.ifPresent(contacts::add);
            });

            updateContact.setOnAction(e -> {
                Contact selected = contactTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setEmail("Обновлено " + selected.getEmail());
                    contacts.update(selected);
                } else {
                    showError("Ошибка", "Выберите контакт");
                }
            });

            deleteContact.setOnAction(e -> {
                Contact selected = contactTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    contacts.delete(selected);
                } else {
                    showError("Ошибка", "Выберите контакт");
                }
            });
        }



        HBox contactButtons = new HBox(5, addContact, updateContact, deleteContact);
        VBox contactsBox = new VBox(5, contactTable, contactButtons);
        contactsBox.setPadding(new Insets(10));
        contactsBox.setPrefWidth(300);

        HBox root = new HBox(10, teachersBox, contactsBox);
        root.setPadding(new Insets(10));

        // Обновляем таблицу контактов — показываем всех
        contactTable.setItems(contacts.getContacts());

        return new VBox(root);
    }

    private Dialog<Teacher> createTeacherDialog() {
        Dialog<Teacher> dialog = new Dialog<>();
        dialog.setTitle("Новый преподаватель");
        dialog.setHeaderText("Введите данные");

        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        TextField firstName = new TextField();
        TextField middleName = new TextField();
        TextField lastName = new TextField();
        TextField position = new TextField();
        TextField degree = new TextField();
        TextField residence = new TextField();
        TextField experience = new TextField();

        VBox content = new VBox(10,
                new Label("Имя:"), firstName,
                new Label("Отчество:"), middleName,
                new Label("Фамилия:"), lastName,
                new Label("Должность:"), position,
                new Label("Ученая степень:"), degree,
                new Label("Проживание:"), residence,
                new Label("Опыт (лет):"), experience
        );
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(button -> {
            if (button == addButton) {
                try {
                    Teacher t = new Teacher();
                    t.setFirstName(firstName.getText());
                    t.setMiddleName(middleName.getText());
                    t.setLastName(lastName.getText());
                    t.setPosition(position.getText());
                    t.setDegree(degree.getText());
                    t.setResidence(residence.getText());
                    t.setExperience(Integer.parseInt(experience.getText()));
                    return t;
                } catch (NumberFormatException ex) {
                    showError("Ошибка", "Опыт должен быть числом");
                }
            }
            return null;
        });

        return dialog;
    }

    private Dialog<Contact> createContactDialog(Teacher teacher) {
        Dialog<Contact> dialog = new Dialog<>();
        dialog.setTitle("Новый контакт");
        dialog.setHeaderText("Введите данные");

        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        TextField email = new TextField();
        TextField phone = new TextField();

        VBox content = new VBox(10,
                new Label("Email:"), email,
                new Label("Телефон:"), phone
        );
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(button -> {
            if (button == addButton) {
                return new Contact(teacher.getId(), email.getText(), phone.getText());
            }
            return null;
        });

        return dialog;
    }

    private void showError(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
