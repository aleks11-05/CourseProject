package org.example.university2.Models;

public class DistributionOfAcademicHours {
    private int id;
    private int subject_id;
    private int groupa_id;
    private int teacher_id;
    private int lesson_type_id;
    private int semester;
    private int year;

    public DistributionOfAcademicHours() {}

    public DistributionOfAcademicHours(int id, int subject_id, int groupa_id, int teacher_id, int lesson_type_id, int semester, int year) {
        this.id = id;
        this.subject_id = subject_id;
        this.groupa_id = groupa_id;
        this.teacher_id = teacher_id;
        this.lesson_type_id = lesson_type_id;
        this.semester = semester;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getGroupa_id() {
        return groupa_id;
    }

    public void setGroupa_id(int groupa_id) {
        this.groupa_id = groupa_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getLesson_type_id() {
        return lesson_type_id;
    }

    public void setLesson_type_id(int lesson_type_id) {
        this.lesson_type_id = lesson_type_id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        if (semester < 1 || semester > 2) {
            throw new IllegalArgumentException("Semester must be 1 or 2");
        }
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 2000 || year > 2100) {
            throw new IllegalArgumentException("Year must be between 2000 and 2100");
        }
        this.year = year;
    }
}
