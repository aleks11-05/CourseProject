package org.example.university2.Models;

public class Contact {
    private int teacher_id;
    private String email;
    private String phone;

    public Contact() {}

    public Contact(int teacher_id, String email, String phone) {
        this.teacher_id = teacher_id;
        this.email = email;
        this.phone = phone;
    }

    public int getTeacherId() { return teacher_id; }
    public void setTeacherId(int teacherId) { this.teacher_id = teacherId; }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

}

