package org.example.university2.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.Containers.*;
import org.example.university2.Models.*;
import org.example.university2.View.AcademicHoursView;

import java.util.List;

public class AcademicHoursController {

    private final AcademicHourss academicHourssContainer;
    private final DistributionOfAcademicHourss distributionOfAcademicHourssContainer;
    private final TypeLessons typeLessonsContainer;
    private final Subjects subjectsContainer;
    private final Teachers teachersContainer;
    private final Groupas groupasContainer;
    private final AcademicHoursView view;
    private final boolean isReadOnly;

    private final ObservableList<CombinedHoursData> combinedHoursData = FXCollections.observableArrayList();

    public AcademicHoursController(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
        this.academicHourssContainer = new AcademicHourss();
        this.distributionOfAcademicHourssContainer = new DistributionOfAcademicHourss();
        this.typeLessonsContainer = new TypeLessons();
        this.subjectsContainer = new Subjects();
        this.teachersContainer = new Teachers();
        this.groupasContainer = new Groupas();
        this.view = new AcademicHoursView(academicHourssContainer, distributionOfAcademicHourssContainer,
                typeLessonsContainer, subjectsContainer, teachersContainer, groupasContainer, isReadOnly);
        load();
    }

    public void load() {
        combinedHoursData.clear();

        List<AcademicHours> hours = academicHourssContainer.getAcademicHoursList();
        List<DistributionOfAcademicHours> distributions = distributionOfAcademicHourssContainer.getDistributionList();
        List<Subject> subjectsList = subjectsContainer.getSubjects();
        List<TypeLesson> lessonTypes = typeLessonsContainer.getLessonTypes();
        List<Teacher> teachersList = teachersContainer.getAll();
        List<Groupa> groups = groupasContainer.getGroupas();

        for (DistributionOfAcademicHours dist : distributions) {
            AcademicHours hoursData = null;
            for (AcademicHours h : hours) {
                if (h.getSubjectId() == dist.getSubject_id() && h.getLessonTypeId() == dist.getLesson_type_id()) {
                    hoursData = h;
                    break;
                }
            }

            if (hoursData != null) {
                String subjectName = "";
                for (Subject s : subjectsList) {
                    if (s.getId() == dist.getSubject_id()) {
                        subjectName = s.getName();
                        break;
                    }
                }

                String lessonTypeName = "";
                for (TypeLesson lt : lessonTypes) {
                    if (lt.getId() == dist.getLesson_type_id()) {
                        lessonTypeName = lt.getName();
                        break;
                    }
                }

                String teacherName = "";
                for (Teacher t : teachersList) {
                    if (t.getId() == dist.getTeacher_id()) {
                        teacherName = t.getLastName() + " " + t.getFirstName().substring(0, 1) + "." + t.getMiddleName().substring(0, 1) + ".";
                        break;
                    }
                }

                String groupName = "";
                for (Groupa g : groups) {
                    if (g.getId() == dist.getGroupa_id()) {
                        groupName = g.getGroupaNumber();
                        break;
                    }
                }

                CombinedHoursData combined = new CombinedHoursData(
                        subjectName, lessonTypeName, hoursData.getNumAkHours(),
                        teacherName, groupName, dist.getSemester(), dist.getYear()
                );

                combinedHoursData.add(combined);
            }
        }
    }

    public ObservableList<CombinedHoursData> getCombinedHoursData() {
        return combinedHoursData;
    }

    public void addAcademicHours(AcademicHours academicHours) {
        academicHourssContainer.add(academicHours);
        load();
    }

    public void updateAcademicHours(AcademicHours academicHours) {
        academicHourssContainer.update(academicHours);
        load();
    }

    public void deleteAcademicHours(AcademicHours academicHours) {
        academicHourssContainer.delete(academicHours);
        load();
    }

    public void addDistribution(DistributionOfAcademicHours distribution) {
        distributionOfAcademicHourssContainer.add(distribution);
        load();
    }

    public void updateDistribution(DistributionOfAcademicHours distribution) {
        distributionOfAcademicHourssContainer.update(distribution);
        load();
    }

    public void deleteDistribution(DistributionOfAcademicHours distribution) {
        distributionOfAcademicHourssContainer.delete(distribution);
        load();
    }

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

    public AcademicHourss getAcademicHourssContainer() {
        return academicHourssContainer;
    }

    public DistributionOfAcademicHourss getDistributionOfAcademicHourssContainer() {
        return distributionOfAcademicHourssContainer;
    }

    public TypeLessons getTypeLessonsContainer() {
        return typeLessonsContainer;
    }

    public Subjects getSubjectsContainer() {
        return subjectsContainer;
    }

    public Teachers getTeachersContainer() {
        return teachersContainer;
    }

    public Groupas getGroupasContainer() {
        return groupasContainer;
    }

    public AcademicHoursView getView() {
        return view;
    }

    public static class CombinedHoursData {
        private final String subjectName;
        private final String lessonTypeName;
        private final int totalHours;
        private final String teacherName;
        private final String groupName;
        private final int semester;
        private final int year;

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

        public String getSubjectName() {
            return subjectName;
        }

        public String getLessonTypeName() {
            return lessonTypeName;
        }

        public int getTotalHours() {
            return totalHours;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public String getGroupName() {
            return groupName;
        }

        public int getSemester() {
            return semester;
        }

        public int getYear() {
            return year;
        }
    }
}
