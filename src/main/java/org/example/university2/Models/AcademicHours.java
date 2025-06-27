package org.example.university2.Models;

public class AcademicHours {
    private int subject_id;
    private int lesson_type_id;
    private int num_ak_hours;

    public AcademicHours() {}

    public AcademicHours(int subject_id, int lesson_type_id, int num_ak_hours) {
        this.subject_id = subject_id;
        this.lesson_type_id = lesson_type_id;
        this.num_ak_hours = num_ak_hours;
    }

    public int getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getLessonTypeId() {
        return lesson_type_id;
    }

    public void setLessonTypeId(int lesson_type_id) {
        this.lesson_type_id = lesson_type_id;
    }

    public int getNumAkHours() {
        return num_ak_hours;
    }

    public void setNumAkHours(int num_ak_hours) {
        this.num_ak_hours = num_ak_hours;
    }
}
