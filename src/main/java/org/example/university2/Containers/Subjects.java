package org.example.university2.Containers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.SubjectDAO;
import org.example.university2.Models.Subject;

import java.util.ArrayList;
import java.util.List;

public class Subjects {
    private final SubjectDAO dao;
    private final ObservableList<Subject> subjects = FXCollections.observableArrayList();

    public Subjects() {
        dao = new SubjectDAO();
        load();
    }

    public List<Subject> getAll() {
        return new ArrayList<>(subjects);
    }

    public ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public void load() {
        subjects.clear();
        List<Subject> all = dao.getAllSubjectsSortedById();
        subjects.addAll(all);
    }

    public void add(Subject subject) {
        dao.create(subject);
        subjects.add(subject);
    }

    public void update(Subject subject) {
        dao.update(subject);
        load();
    }

    public void delete(Subject subject) {
        dao.delete(subject.getId());
        subjects.remove(subject);
    }

    public void delete(int id) {
        dao.delete(id);
        subjects.removeIf(subject -> subject.getId() == id);
    }
}