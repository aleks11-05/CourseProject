package org.example.university2.Containers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.TeacherDAO;
import org.example.university2.Models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Teachers {
    private final TeacherDAO dao;
    private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();

    public Teachers() {
        dao = new TeacherDAO();
        load();
    }

    public List<Teacher> getAll() {
        return new ArrayList<>(teachers); // Возвращаем копию списка
    }

    public ObservableList<Teacher> getTeachers() {
        return teachers;
    }

    public void load() {
        teachers.clear();
        List<Teacher> all = dao.findAll();
        teachers.addAll(all);
    }

    public void add(Teacher t) {
        dao.create(t);
        teachers.add(t);
    }

    public void update(Teacher t) {
        dao.update(t);
    }

    public void delete(Teacher t) {
        dao.delete(t.getId());
        teachers.remove(t);
    }
}
