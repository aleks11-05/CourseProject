package org.example.university2.Models;

public class Contact {
    private int teacherId;
    private String email;
    private String phone;

    public Contact() {}

    public Contact(int teacherId, String email, String phone) {
        this.teacherId = teacherId;
        this.email = email;
        this.phone = phone;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
