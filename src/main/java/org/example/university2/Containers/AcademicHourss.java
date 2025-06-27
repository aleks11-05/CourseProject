package org.example.university2.Containers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.AcademicHoursDAO;
import org.example.university2.Models.AcademicHours;

import java.util.List;

public class AcademicHourss {
    private final AcademicHoursDAO dao;
    private final ObservableList<AcademicHours> academicHoursList = FXCollections.observableArrayList();

    public AcademicHourss() {
        dao = new AcademicHoursDAO();
        load();
    }

    public ObservableList<AcademicHours> getAcademicHoursList() {
        return academicHoursList;
    }

    public void load() {
        academicHoursList.clear();
        List<AcademicHours> all = dao.findAll();
        academicHoursList.addAll(all);
    }

    public void add(AcademicHours ah) {
        dao.create(ah);
        academicHoursList.add(ah);
    }

    public void update(AcademicHours ah) {
        dao.update(ah);
        load();
    }

    public void delete(AcademicHours ah) {
        dao.delete(ah.getSubjectId(), ah.getLessonTypeId());
        academicHoursList.remove(ah);
    }
}