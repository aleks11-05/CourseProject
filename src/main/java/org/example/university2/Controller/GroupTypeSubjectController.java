package org.example.university2.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.Containers.*;
import org.example.university2.Models.*;
import org.example.university2.View.GroupTypeSubjectView;

import java.util.List;


public class GroupTypeSubjectController {
    private final boolean isReadOnly;

    private final Groupas groupasContainer;
    private final TypeLessons typeLessonsContainer;
    private final Subjects subjectsContainer;
    private final GroupTypeSubjectView view;


    private final ObservableList<GroupTypeSubjectData> groupTypeSubjectData = FXCollections.observableArrayList();

    public GroupTypeSubjectController(boolean isReadOnly) {
        this.groupasContainer = new Groupas();
        this.typeLessonsContainer = new TypeLessons();
        this.subjectsContainer = new Subjects();
        this.isReadOnly = isReadOnly;

        this.view = new GroupTypeSubjectView(groupasContainer, typeLessonsContainer, subjectsContainer, isReadOnly);

        load();
    }

    public void load() {
        groupTypeSubjectData.clear();

        List<Groupa> groups = groupasContainer.getGroupas();
        List<TypeLesson> typeLessons = typeLessonsContainer.getLessonTypes();
        List<Subject> subjects = subjectsContainer.getSubjects();

    }

    public ObservableList<GroupTypeSubjectData> getGroupTypeSubjectData() {
        return groupTypeSubjectData;
    }


    public void addGroup(Groupa group) {
        groupasContainer.add(group);
        load(); // Перезагружаем данные
    }

    public void updateGroup(Groupa group) {
        groupasContainer.update(group);
        load();
    }

    public void deleteGroup(Groupa group) {
        groupasContainer.delete(group);
        load();
    }

    // Методы для работы с типами занятий
    public void addTypeLesson(TypeLesson typeLesson) {
        typeLessonsContainer.add(typeLesson);
        load();
    }

    public void updateTypeLesson(TypeLesson typeLesson) {
        typeLessonsContainer.update(typeLesson);
        load();
    }

    public void deleteTypeLesson(TypeLesson typeLesson) {
        typeLessonsContainer.delete(typeLesson);
        load();
    }

    // Методы для работы с предметами
    public void addSubject(Subject subject) {
        subjectsContainer.add(subject);
        load();
    }

    public void updateSubject(Subject subject) {
        subjectsContainer.update(subject);
        load();
    }

    public void deleteSubject(Subject subject) {
        subjectsContainer.delete(subject);
        load();
    }

    // Геттеры для доступа к контейнерам
    public Groupas getGroupas() {
        return groupasContainer;
    }

    public TypeLessons getTypeLessons() {
        return typeLessonsContainer;
    }

    public Subjects getSubjects() {
        return subjectsContainer;
    }

    public GroupTypeSubjectView getView() {
        return view;
    }

    public static class GroupTypeSubjectData {
        private final Groupa group;
        private final TypeLesson typeLesson;
        private final Subject subject;

        public GroupTypeSubjectData(Groupa group, TypeLesson typeLesson, Subject subject) {
            this.group = group;
            this.typeLesson = typeLesson;
            this.subject = subject;
        }

        public Groupa getGroup() {
            return group;
        }

        public TypeLesson getTypeLesson() {
            return typeLesson;
        }

        public Subject getSubject() {
            return subject;
        }

        public String getGroupName() {
            return group != null ? group.getGroupaNumber() : "";
        }

        public String getTypeLessonName() {
            return typeLesson != null ? typeLesson.getName() : "";
        }

        public String getSubjectName() {
            return subject != null ? subject.getName() : "";
        }

        public int getGroupSize() {
            return group != null ? group.getSize() : 0;
        }
    }
}