package org.example.university2.Models;

public class Groupa {
    private int id;
    private String groupa_number;
    private int size;

    public Groupa() {}

    public Groupa(int id, String groupa_number, int size) {
        this.id = id;
        this.groupa_number = groupa_number;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupaNumber() {
        return groupa_number;
    }

    public void setGroupaNumber(String groupaNumber) {
        this.groupa_number = groupa_number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
