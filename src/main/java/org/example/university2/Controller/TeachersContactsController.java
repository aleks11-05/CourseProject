package org.example.university2.Controller;


import org.example.university2.Containers.Contacts;
import org.example.university2.Containers.Teachers;
import org.example.university2.View.TeachersContactsView;

public class TeachersContactsController {
    private final Teachers teachers;
    private final Contacts contacts;
    private final TeachersContactsView view;
    private final boolean isReadOnly;

    public TeachersContactsController(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
        this.teachers = new Teachers();
        this.contacts = new Contacts();
        this.view = new TeachersContactsView(teachers, contacts, isReadOnly);
    }

    public void initialize() {
        teachers.load();
        contacts.load();
    }

    public TeachersContactsView getView() {
        return view;
    }
}