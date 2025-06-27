package org.example.university2;

public class AcademicHours {
    private int subjectId;
    private int lessonTypeId;
    private int numAkHours;

    public AcademicHours() {}

    public AcademicHours(int subjectId, int lessonTypeId, int numAkHours) {
        this.subjectId = subjectId;
        this.lessonTypeId = lessonTypeId;
        this.numAkHours = numAkHours;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public int getNumAkHours() {
        return numAkHours;
    }

    public void setNumAkHours(int numAkHours) {
        this.numAkHours = numAkHours;
    }
}
