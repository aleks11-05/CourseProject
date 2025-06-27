package org.example.university2.Models;

public class Teacher {
    private int id;
    private String position;
    private String degree;
    private String firstName;
    private String middleName;
    private String lastName;
    private String residence;
    private int experience;

    public Teacher() {}

    public Teacher(int id, String position, String degree, String firstName, String middleName, String lastName, String residence, int experience) {
        this.id = id;
        this.position = position;
        this.degree = degree;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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