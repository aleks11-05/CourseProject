package org.example.university2.DAO;

import org.example.university2.Models.Contact;
import org.example.university2.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private final Connection connection;

    public ContactDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<Contact> findAll() {
        List<Contact> list = new ArrayList<>();
        String sql = "SELECT * FROM Contact ORDER BY teacher_id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contact c = new Contact(
                        rs.getInt("teacher_id"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(Contact contact) {
        String sql = "INSERT INTO Contact(teacher_id, email, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, contact.getTeacherId());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Contact contact) {
        String sql = "UPDATE Contact SET email=?, phone=? WHERE teacher_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getEmail());
            ps.setString(2, contact.getPhone());
            ps.setInt(3, contact.getTeacherId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int teacherId) {
        String sql = "DELETE FROM Contact WHERE teacher_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contact findByTeacherId(int teacherId) {
        String sql = "SELECT * FROM Contact WHERE teacher_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Contact(
                        rs.getInt("teacher_id"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

