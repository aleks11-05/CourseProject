package org.example.university2.View;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.university2.Containers.*;
import org.example.university2.Models.*;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


public class AcademicHoursView {

    private final AcademicHourss academicHourss;
    private final DistributionOfAcademicHourss distributionOfAcademicHourss;
    private final TypeLessons typeLessons;
    private final Subjects subjects;
    private final Teachers teachers;
    private final Groupas groupas;

    private final TableView<CombinedHoursData> combinedTable;
    private final TableView<AcademicHours> academicHoursTable;
    private final TableView<DistributionOfAcademicHours> distributionTable;
    private final TableView<TypeLesson> typeLessonsTable;
    private final boolean isReadOnly;

    public AcademicHoursView(AcademicHourss academicHourss, DistributionOfAcademicHourss distributionOfAcademicHourss,
                             TypeLessons typeLessons, Subjects subjects, Teachers teachers, Groupas groupas, boolean isReadOnly) {
        this.academicHourss = academicHourss;
        this.distributionOfAcademicHourss = distributionOfAcademicHourss;
        this.typeLessons = typeLessons;
        this.subjects = subjects;
        this.teachers = teachers;
        this.groupas = groupas;
        this.isReadOnly = isReadOnly;

        this.combinedTable = new TableView<>();
        this.academicHoursTable = new TableView<>(academicHourss.getAcademicHoursList());
        this.distributionTable = new TableView<>(distributionOfAcademicHourss.getDistributionList());
        this.typeLessonsTable = new TableView<>(typeLessons.getLessonTypes());

        setupCombinedTable();
        setupAcademicHoursTable();
        setupDistributionTable();
        setupTypeLessonsTable();

        loadCombinedData();
    }


    private static class CombinedHoursData {
        private String subjectName;
        private String lessonTypeName;
        private int totalHours;
        private String teacherName;
        private String groupName;
        private int semester;
        private int year;

        public CombinedHoursData(String subjectName, String lessonTypeName, int totalHours,
                                 String teacherName, String groupName, int semester, int year) {
            this.subjectName = subjectName;
            this.lessonTypeName = lessonTypeName;
            this.totalHours = totalHours;
            this.teacherName = teacherName;
            this.groupName = groupName;
            this.semester = semester;
            this.year = year;
        }

        public String getSubjectName() { return subjectName; }
        public String getLessonTypeName() { return lessonTypeName; }
        public int getTotalHours() { return totalHours; }
        public String getTeacherName() { return teacherName; }
        public String getGroupName() { return groupName; }
        public int getSemester() { return semester; }
        public int getYear() { return year; }
    }

    private void setupCombinedTable() {
        TableColumn<CombinedHoursData, String> subjectCol = new TableColumn<>("Предмет");
        subjectCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getSubjectName()));

        TableColumn<CombinedHoursData, String> lessonTypeCol = new TableColumn<>("Тип занятия");
        lessonTypeCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLessonTypeName()));

        TableColumn<CombinedHoursData, Number> totalHoursCol = new TableColumn<>("Всего часов");
        totalHoursCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getTotalHours()));

        TableColumn<CombinedHoursData, String> teacherCol = new TableColumn<>("Преподаватель");
        teacherCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getTeacherName()));

        TableColumn<CombinedHoursData, String> groupCol = new TableColumn<>("Группа");
        groupCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getGroupName()));

        TableColumn<CombinedHoursData, Number> semesterCol = new TableColumn<>("Семестр");
        semesterCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getSemester()));

        TableColumn<CombinedHoursData, Number> yearCol = new TableColumn<>("Год");
        yearCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getYear()));

        combinedTable.getColumns().addAll(subjectCol, lessonTypeCol, totalHoursCol, teacherCol, groupCol, semesterCol, yearCol);
    }

    private void setupAcademicHoursTable() {
        TableColumn<AcademicHours, String> subjectCol = new TableColumn<>("Предмет");
        subjectCol.setCellValueFactory(cell -> {
            int subjectId = cell.getValue().getSubjectId();
            String subjectName = subjects.getSubjects().stream()
                    .filter(s -> s.getId() == subjectId)
                    .findFirst().map(Subject::getName).orElse("Неизвестно");
            return new ReadOnlyStringWrapper(subjectName);
        });

        TableColumn<AcademicHours, String> lessonTypeCol = new TableColumn<>("Тип занятия");
        lessonTypeCol.setCellValueFactory(cell -> {
            int lessonTypeId = cell.getValue().getLessonTypeId();
            String lessonTypeName = typeLessons.getLessonTypes().stream()
                    .filter(lt -> lt.getId() == lessonTypeId)
                    .findFirst().map(TypeLesson::getName).orElse("Неизвестно");
            return new ReadOnlyStringWrapper(lessonTypeName);
        });

        TableColumn<AcademicHours, Number> hoursCol = new TableColumn<>("Количество часов");
        hoursCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getNumAkHours()));

        academicHoursTable.getColumns().addAll(subjectCol, lessonTypeCol, hoursCol);
    }

    private void setupDistributionTable() {
        TableColumn<DistributionOfAcademicHours, String> subjectCol = new TableColumn<>("Предмет");
        subjectCol.setCellValueFactory(cell -> {
            int subjectId = cell.getValue().getSubject_id();
            String subjectName = subjects.getSubjects().stream()
                    .filter(s -> s.getId() == subjectId)
                    .findFirst().map(Subject::getName).orElse("Неизвестно");
            return new ReadOnlyStringWrapper(subjectName);
        });

        TableColumn<DistributionOfAcademicHours, String> groupCol = new TableColumn<>("Группа");
        groupCol.setCellValueFactory(cell -> {
            int groupId = cell.getValue().getGroupa_id();
            String groupName = groupas.getGroupas().stream()
                    .filter(g -> g.getId() == groupId)
                    .findFirst().map(Groupa::getGroupaNumber).orElse("Неизвестно");
            return new ReadOnlyStringWrapper(groupName);
        });

        TableColumn<DistributionOfAcademicHours, String> teacherCol = new TableColumn<>("Преподаватель");
        teacherCol.setCellValueFactory(cell -> {
            int teacherId = cell.getValue().getTeacher_id();
            Teacher teacher = teachers.getTeachers().stream()
                    .filter(t -> t.getId() == teacherId)
                    .findFirst().orElse(null);

            if (teacher != null) {
                return new ReadOnlyStringWrapper(teacher.getLastName() + " " +
                        teacher.getFirstName() + " " + teacher.getMiddleName());
            }
            return new ReadOnlyStringWrapper("Неизвестно");
        });

        TableColumn<DistributionOfAcademicHours, String> lessonTypeCol = new TableColumn<>("Тип занятия");
        lessonTypeCol.setCellValueFactory(cell -> {
            int lessonTypeId = cell.getValue().getLesson_type_id();
            String lessonTypeName = typeLessons.getLessonTypes().stream()
                    .filter(lt -> lt.getId() == lessonTypeId)
                    .findFirst().map(TypeLesson::getName).orElse("Неизвестно");
            return new ReadOnlyStringWrapper(lessonTypeName);
        });

        TableColumn<DistributionOfAcademicHours, Number> semesterCol = new TableColumn<>("Семестр");
        semesterCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getSemester()));

        TableColumn<DistributionOfAcademicHours, Number> yearCol = new TableColumn<>("Год");
        yearCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getYear()));

        distributionTable.getColumns().addAll(subjectCol, groupCol, teacherCol, lessonTypeCol, semesterCol, yearCol);
    }

    private void setupTypeLessonsTable() {
        TableColumn<TypeLesson, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getId()));

        TableColumn<TypeLesson, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));

        typeLessonsTable.getColumns().addAll(idCol, nameCol);
    }

    private void loadCombinedData() {
        combinedTable.getItems().clear();

        List<AcademicHours> hours = academicHourss.getAcademicHoursList();
        List<DistributionOfAcademicHours> distributions = distributionOfAcademicHourss.getDistributionList();
        List<Subject> subjectsList = subjects.getSubjects();
        List<TypeLesson> lessonTypes = typeLessons.getLessonTypes();
        List<Teacher> teachersList = teachers.getTeachers();
        List<Groupa> groups = groupas.getGroupas();

        System.out.println("Загрузка объединенных данных:");
        System.out.println("Часов: " + hours.size());
        System.out.println("Распределений: " + distributions.size());
        System.out.println("Предметов: " + subjectsList.size());
        System.out.println("Типов занятий: " + lessonTypes.size());
        System.out.println("Преподавателей: " + teachersList.size());
        System.out.println("Групп: " + groups.size());

        for (DistributionOfAcademicHours dist : distributions) {

            AcademicHours hoursData = hours.stream()
                    .filter(h -> h.getSubjectId() == dist.getSubject_id() && h.getLessonTypeId() == dist.getLesson_type_id())
                    .findFirst().orElse(null);

            if (hoursData != null) {
                String subjectName = subjectsList.stream()
                        .filter(s -> s.getId() == dist.getSubject_id())
                        .findFirst().map(Subject::getName).orElse("Неизвестно");

                String lessonTypeName = lessonTypes.stream()
                        .filter(lt -> lt.getId() == dist.getLesson_type_id())
                        .findFirst().map(TypeLesson::getName).orElse("Неизвестно");

                String teacherName = teachersList.stream()
                        .filter(t -> t.getId() == dist.getTeacher_id())
                        .findFirst().map(t -> t.getLastName() + " " + t.getFirstName().substring(0, 1) + "." + t.getMiddleName().substring(0, 1) + ".")
                        .orElse("Неизвестно");

                String groupName = groups.stream()
                        .filter(g -> g.getId() == dist.getGroupa_id())
                        .findFirst().map(Groupa::getGroupaNumber).orElse("Неизвестно");

                CombinedHoursData combined = new CombinedHoursData(
                        subjectName, lessonTypeName, hoursData.getNumAkHours(),
                        teacherName, groupName, dist.getSemester(), dist.getYear()
                );

                combinedTable.getItems().add(combined);
            }
        }

        System.out.println("Загружено записей в объединенную таблицу: " + combinedTable.getItems().size());
    }

    private void showTeacherFilterDialog() {
        Dialog<Teacher> dialog = new Dialog<>();
        dialog.setTitle("Выберите преподавателя");
        dialog.setHeaderText("Выберите преподавателя для фильтрации");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        ComboBox<Teacher> teacherCombo = new ComboBox<>();
        teacherCombo.setItems(teachers.getTeachers());
        teacherCombo.setCellFactory(param -> new ListCell<Teacher>() {
            @Override
            protected void updateItem(Teacher item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getLastName() + " " + item.getFirstName() + " " + item.getMiddleName());
                } else {
                    setText(null);
                }
            }
        });
        teacherCombo.setButtonCell(teacherCombo.getCellFactory().call(null));

        dialog.getDialogPane().setContent(new VBox(10, new Label("Преподаватель:"), teacherCombo));

        dialog.setResultConverter(buttonType -> {
            if (buttonType == okButton) {
                return teacherCombo.getValue();
            }
            return null;
        });

        Optional<Teacher> result = dialog.showAndWait();
        result.ifPresent(this::filterByTeacher);
    }

    private void filterByTeacher(Teacher selectedTeacher) {
        combinedTable.getItems().clear();

        List<AcademicHours> hours = academicHourss.getAcademicHoursList();
        List<DistributionOfAcademicHours> distributions = distributionOfAcademicHourss.getDistributionList();
        List<Subject> subjectsList = subjects.getSubjects();
        List<TypeLesson> lessonTypes = typeLessons.getLessonTypes();
        List<Groupa> groups = groupas.getGroupas();

        List<DistributionOfAcademicHours> filteredDistributions = distributions.stream()
                .filter(d -> d.getTeacher_id() == selectedTeacher.getId())
                .collect(Collectors.toList());

        for (DistributionOfAcademicHours dist : filteredDistributions) {
            AcademicHours hoursData = hours.stream()
                    .filter(h -> h.getSubjectId() == dist.getSubject_id() && h.getLessonTypeId() == dist.getLesson_type_id())
                    .findFirst().orElse(null);

            if (hoursData != null) {
                String subjectName = subjectsList.stream()
                        .filter(s -> s.getId() == dist.getSubject_id())
                        .findFirst().map(Subject::getName).orElse("Неизвестно");

                String lessonTypeName = lessonTypes.stream()
                        .filter(lt -> lt.getId() == dist.getLesson_type_id())
                        .findFirst().map(TypeLesson::getName).orElse("Неизвестно");

                String teacherName = selectedTeacher.getLastName() + " " +
                        selectedTeacher.getFirstName().substring(0, 1) + "." +
                        selectedTeacher.getMiddleName().substring(0, 1) + ".";

                String groupName = groups.stream()
                        .filter(g -> g.getId() == dist.getGroupa_id())
                        .findFirst().map(Groupa::getGroupaNumber).orElse("Неизвестно");

                CombinedHoursData combined = new CombinedHoursData(
                        subjectName, lessonTypeName, hoursData.getNumAkHours(),
                        teacherName, groupName, dist.getSemester(), dist.getYear()
                );

                combinedTable.getItems().add(combined);
            }
        }
    }

    public VBox getView() {

        TabPane tabPane = new TabPane();


        Button loadData = new Button("Загрузить данные");
        Button filterByTeacher = new Button("Фильтр по преподавателю");
        Button clearFilter = new Button("Сбросить фильтр");

        loadData.setOnAction(e -> loadCombinedData());
        filterByTeacher.setOnAction(e -> showTeacherFilterDialog());
        clearFilter.setOnAction(e -> loadCombinedData());

        VBox combinedLayout = new VBox(10, combinedTable, new HBox(10, loadData, filterByTeacher, clearFilter));
        combinedLayout.setPadding(new Insets(10));

        Tab combinedTab = new Tab("Часы и распределение", combinedLayout);
        combinedTab.setClosable(false);


        Button addAcademicHours = new Button("Добавить");
        Button updateAcademicHours = new Button("Обновить");
        Button deleteAcademicHours = new Button("Удалить");

        if (isReadOnly) {

            addAcademicHours.setVisible(false);
            updateAcademicHours.setVisible(false);
            deleteAcademicHours.setVisible(false);
        } else {
            addAcademicHours.setOnAction(e -> {
                Dialog<AcademicHours> dialog = createAcademicHoursDialog();
                Optional<AcademicHours> result = dialog.showAndWait();
                result.ifPresent(academicHourss::add);
            });

            updateAcademicHours.setOnAction(e -> {
                AcademicHours selected = academicHoursTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setNumAkHours(selected.getNumAkHours() + 1);
                    academicHourss.update(selected);
                }
            });

            deleteAcademicHours.setOnAction(e -> {
                AcademicHours selected = academicHoursTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    academicHourss.delete(selected);
                }
            });

        }


        VBox academicHoursLayout = new VBox(10, academicHoursTable, new HBox(10, addAcademicHours, updateAcademicHours, deleteAcademicHours));
        academicHoursLayout.setPadding(new Insets(10));

        Tab academicHoursTab = new Tab("Академические часы", academicHoursLayout);
        academicHoursTab.setClosable(false);


        Button addDistribution = new Button("Добавить");
        Button updateDistribution = new Button("Обновить");
        Button deleteDistribution = new Button("Удалить");

        if (isReadOnly) {

            addDistribution.setVisible(false);
            updateDistribution.setVisible(false);
            deleteDistribution.setVisible(false);
        } else {
            addDistribution.setOnAction(e -> {
                Dialog<DistributionOfAcademicHours> dialog = createDistributionDialog();
                Optional<DistributionOfAcademicHours> result = dialog.showAndWait();
                result.ifPresent(distributionOfAcademicHourss::add);
            });

            updateDistribution.setOnAction(e -> {
                DistributionOfAcademicHours selected = distributionTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setYear(2025);
                    distributionOfAcademicHourss.update(selected);
                }
            });

            deleteDistribution.setOnAction(e -> {
                DistributionOfAcademicHours selected = distributionTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    distributionOfAcademicHourss.delete(selected);
                }
            });

        }

        VBox distributionLayout = new VBox(10, distributionTable, new HBox(10, addDistribution, updateDistribution, deleteDistribution));
        distributionLayout.setPadding(new Insets(10));

        Tab distributionTab = new Tab("Распределение часов", distributionLayout);
        distributionTab.setClosable(false);


        Button addTypeLesson = new Button("Добавить");
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

        VBox typeLessonsLayout = new VBox(10, typeLessonsTable, new HBox(10, addTypeLesson, updateTypeLesson, deleteTypeLesson));
        typeLessonsLayout.setPadding(new Insets(10));

        Tab typeLessonsTab = new Tab("Типы занятий", typeLessonsLayout);
        typeLessonsTab.setClosable(false);

        tabPane.getTabs().addAll(combinedTab, academicHoursTab, distributionTab, typeLessonsTab);

        return new VBox(tabPane);
    }


    private Dialog<AcademicHours> createAcademicHoursDialog() {
        Dialog<AcademicHours> dialog = new Dialog<>();
        dialog.setTitle("Новые академические часы");
        dialog.setHeaderText("Введите данные");

        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        ComboBox<Subject> subjectCombo = new ComboBox<>(subjects.getSubjects());
        subjectCombo.setCellFactory(param -> new ListCell<Subject>() {
            @Override
            protected void updateItem(Subject item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                } else {
                    setText(null);
                }
            }
        });
        subjectCombo.setButtonCell(subjectCombo.getCellFactory().call(null));

        ComboBox<TypeLesson> lessonTypeCombo = new ComboBox<>(typeLessons.getLessonTypes());
        lessonTypeCombo.setCellFactory(param -> new ListCell<TypeLesson>() {
            @Override
            protected void updateItem(TypeLesson item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                } else {
                    setText(null);
                }
            }
        });
        lessonTypeCombo.setButtonCell(lessonTypeCombo.getCellFactory().call(null));

        TextField hoursField = new TextField();

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Предмет:"), subjectCombo,
                new Label("Тип занятия:"), lessonTypeCombo,
                new Label("Количество часов:"), hoursField
        );
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                try {
                    Subject selectedSubject = subjectCombo.getValue();
                    TypeLesson selectedLessonType = lessonTypeCombo.getValue();

                    if (selectedSubject != null && selectedLessonType != null) {
                        return new AcademicHours(
                                selectedSubject.getId(),
                                selectedLessonType.getId(),
                                Integer.parseInt(hoursField.getText())
                        );
                    }
                } catch (NumberFormatException e) {
                    showError("Ошибка", "Введите корректное число часов");
                }
            }
            return null;
        });

        return dialog;
    }

    private Dialog<DistributionOfAcademicHours> createDistributionDialog() {
        Dialog<DistributionOfAcademicHours> dialog = new Dialog<>();
        dialog.setTitle("Новое распределение часов");
        dialog.setHeaderText("Введите данные");

        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        ComboBox<Subject> subjectCombo = new ComboBox<>(subjects.getSubjects());
        subjectCombo.setCellFactory(param -> new ListCell<Subject>() {
            @Override
            protected void updateItem(Subject item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                } else {
                    setText(null);
                }
            }
        });
        subjectCombo.setButtonCell(subjectCombo.getCellFactory().call(null));

        ComboBox<Groupa> groupCombo = new ComboBox<>(groupas.getGroupas());
        groupCombo.setCellFactory(param -> new ListCell<Groupa>() {
            @Override
            protected void updateItem(Groupa item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getGroupaNumber());
                } else {
                    setText(null);
                }
            }
        });
        groupCombo.setButtonCell(groupCombo.getCellFactory().call(null));

        ComboBox<Teacher> teacherCombo = new ComboBox<>(teachers.getTeachers());
        teacherCombo.setCellFactory(param -> new ListCell<Teacher>() {
            @Override
            protected void updateItem(Teacher item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getLastName() + " " + item.getFirstName() + " " + item.getMiddleName());
                } else {
                    setText(null);
                }
            }
        });
        teacherCombo.setButtonCell(teacherCombo.getCellFactory().call(null));

        ComboBox<TypeLesson> lessonTypeCombo = new ComboBox<>(typeLessons.getLessonTypes());
        lessonTypeCombo.setCellFactory(param -> new ListCell<TypeLesson>() {
            @Override
            protected void updateItem(TypeLesson item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                } else {
                    setText(null);
                }
            }
        });
        lessonTypeCombo.setButtonCell(lessonTypeCombo.getCellFactory().call(null));

        TextField semesterField = new TextField();
        TextField yearField = new TextField();

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Предмет:"), subjectCombo,
                new Label("Группа:"), groupCombo,
                new Label("Преподаватель:"), teacherCombo,
                new Label("Тип занятия:"), lessonTypeCombo,
                new Label("Семестр (1-2):"), semesterField,
                new Label("Год:"), yearField
        );
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                try {
                    Subject selectedSubject = subjectCombo.getValue();
                    Groupa selectedGroup = groupCombo.getValue();
                    Teacher selectedTeacher = teacherCombo.getValue();
                    TypeLesson selectedLessonType = lessonTypeCombo.getValue();

                    if (selectedSubject != null && selectedGroup != null &&
                            selectedTeacher != null && selectedLessonType != null) {
                        return new DistributionOfAcademicHours(
                                0, // ID будет сгенерирован
                                selectedSubject.getId(),
                                selectedGroup.getId(),
                                selectedTeacher.getId(),
                                selectedLessonType.getId(),
                                Integer.parseInt(semesterField.getText()),
                                Integer.parseInt(yearField.getText())
                        );
                    }
                } catch (NumberFormatException e) {
                    showError("Ошибка", "Введите корректные числа для семестра и года");
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

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}