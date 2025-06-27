package org.example.university2.Containers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.university2.DAO.ContactDAO;
import org.example.university2.Models.Contact;

import java.util.List;

public class Contacts {
    private final ContactDAO dao;
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public Contacts() {
        dao = new ContactDAO();
        load();
    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public void load() {
        contacts.clear();
        List<Contact> all = dao.findAll();
        contacts.addAll(all);
    }

    public void add(Contact contact) {
        dao.create(contact);
        contacts.add(contact);
    }

    public void update(Contact contact) {
        dao.update(contact);
    }

    public void delete(Contact contact) {
        dao.delete(contact.getTeacherId());
        contacts.remove(contact);
    }
}
