package org.example.university2.Containers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.TypeLessonDAO;
import org.example.university2.Models.TypeLesson;

import java.util.List;

public class TypeLessons {
    private final TypeLessonDAO dao;
    private final ObservableList<TypeLesson> lessonTypes = FXCollections.observableArrayList();

    public TypeLessons() {
        dao = new TypeLessonDAO();
        load();
    }

    public ObservableList<TypeLesson> getLessonTypes() {
        return lessonTypes;
    }

    public void load() {
        lessonTypes.clear();
        List<TypeLesson> all = dao.findAll();
        lessonTypes.addAll(all);
    }

    public void add(TypeLesson lt) {
        dao.create(lt);
        lessonTypes.add(lt);
    }

    public void update(TypeLesson lt) {
        dao.update(lt);
    }

    public void delete(TypeLesson lt) {
        dao.delete(lt.getId());
        lessonTypes.remove(lt);
    }
}
