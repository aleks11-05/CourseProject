package org.example.university2.Containers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.DistributionOfAcademicHoursDAO;
import org.example.university2.Models.DistributionOfAcademicHours;

import java.util.List;

public class DistributionOfAcademicHourss{
    private final DistributionOfAcademicHoursDAO dao;
    private final ObservableList<DistributionOfAcademicHours> distributionList = FXCollections.observableArrayList();

    public DistributionOfAcademicHourss() {
        dao = new DistributionOfAcademicHoursDAO();
        load();
    }

    public ObservableList<DistributionOfAcademicHours> getDistributionList() {
        return distributionList;
    }

    public void load() {
        distributionList.clear();
        List<DistributionOfAcademicHours> all = dao.findAll();
        distributionList.addAll(all);
    }

    public void add(DistributionOfAcademicHours d) {
        dao.create(d);
        distributionList.add(d);
    }

    public void update(DistributionOfAcademicHours d) {
        dao.update(d);
    }

    public void delete(DistributionOfAcademicHours d) {
        dao.delete(d.getId());
        distributionList.remove(d);
    }
}
