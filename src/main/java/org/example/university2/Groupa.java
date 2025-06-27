package org.example.university2;

public class Groupa {
    private int id;
    private String groupaNumber;
    private int size;

    public Groupa() {}

    public Groupa(int id, String groupaNumber, int size) {
        this.id = id;
        this.groupaNumber = groupaNumber;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupaNumber() {
        return groupaNumber;
    }

    public void setGroupaNumber(String groupaNumber) {
        this.groupaNumber = groupaNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
