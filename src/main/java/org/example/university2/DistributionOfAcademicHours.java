package org.example.university2;

public class DistributionOfAcademicHours {
    private int id;
    private int subjectId;
    private int groupaId;
    private int teacherId;
    private int lessonTypeId;
    private int semester;
    private int year;

    public DistributionOfAcademicHours() {}

    public DistributionOfAcademicHours(int id, int subjectId, int groupaId, int teacherId, int lessonTypeId, int semester, int year) {
        this.id = id;
        this.subjectId = subjectId;
        this.groupaId = groupaId;
        this.teacherId = teacherId;
        this.lessonTypeId = lessonTypeId;
        this.semester = semester;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGroupaId() {
        return groupaId;
    }

    public void setGroupaId(int groupaId) {
        this.groupaId = groupaId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}