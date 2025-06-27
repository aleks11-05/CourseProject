package org.example.university2.DAO;

import org.example.university2.Models.Subject;
import org.example.university2.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private final Connection connection;

    public SubjectDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<Subject> getAllSubjectsSortedById() {
        List<Subject> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Subject ORDER BY id ASC")) {
            while (rs.next()) {
                list.add(new Subject(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Subject> findAll() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject ORDER BY id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Subject subject = new Subject(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                subjects.add(subject);
            }
        } catch (SQLException e) {
            throw new SQLException("Ошибка при получении списка предметов", e);
        }

        return subjects;
    }

    public void create(Subject s) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Subject(name) VALUES(?)")) {
            ps.setString(1, s.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Subject s) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE Subject SET name=? WHERE id=?")) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Subject WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}