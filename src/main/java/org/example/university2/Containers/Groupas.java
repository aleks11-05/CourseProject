package org.example.university2.Containers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.GroupaDAO;
import org.example.university2.Models.Groupa;

import java.util.List;

public class Groupas {
    private final GroupaDAO dao;
    private final ObservableList<Groupa> groupas = FXCollections.observableArrayList();

    public Groupas() {
        dao = new GroupaDAO();
        load();
    }

    public ObservableList<Groupa> getGroupas() {
        return groupas;
    }

    public void load() {
        groupas.clear();
        List<Groupa> all = dao.findAll();
        groupas.addAll(all);
    }

    public void add(Groupa g) {
        dao.create(g);
        groupas.add(g);
    }

    public void update(Groupa g) {
        dao.update(g);
    }

    public void delete(Groupa g) {
        dao.delete(g.getId());
        groupas.remove(g);
    }
}