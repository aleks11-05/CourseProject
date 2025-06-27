package org.example.university2.Models;

public class Teacher {
    private int id;
    private String position;
    private String degree;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String residence;
    private int experience;

    public Teacher() {}

    public Teacher(int id, String position, String degree, String first_name, String middle_name, String last_name, String residence, int experience) {
        this.id = id;
        this.position = position;
        this.degree = degree;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.residence = residence;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getMiddleName() {
        return middle_name;
    }

    public void setMiddleName(String middleName) {
        this.middle_name = middleName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
