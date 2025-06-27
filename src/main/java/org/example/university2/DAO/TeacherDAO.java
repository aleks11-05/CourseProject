package org.example.university2.DAO;

import org.example.university2.Models.Teacher;
import org.example.university2.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private final Connection connection;

    public TeacherDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<Teacher> findAll() {
        List<Teacher> list = new ArrayList<>();
        String sql = "SELECT * FROM Teacher ORDER BY id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Teacher t = new Teacher(
                        rs.getInt("id"),
                        rs.getString("position"),
                        rs.getString("degree"),
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        rs.getString("residence"),
                        rs.getInt("experience")
                );
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(Teacher t) {
        String sql = "INSERT INTO Teacher(position, degree, first_name, middle_name, last_name, residence, experience) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getPosition());
            ps.setString(2, t.getDegree());
            ps.setString(3, t.getFirstName());
            ps.setString(4, t.getMiddleName());
            ps.setString(5, t.getLastName());
            ps.setString(6, t.getResidence());
            ps.setInt(7, t.getExperience());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    t.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Teacher t) {
        String sql = "UPDATE Teacher SET position=?, degree=?, first_name=?, middle_name=?, last_name=?, residence=?, experience=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, t.getPosition());
            ps.setString(2, t.getDegree());
            ps.setString(3, t.getFirstName());
            ps.setString(4, t.getMiddleName());
            ps.setString(5, t.getLastName());
            ps.setString(6, t.getResidence());
            ps.setInt(7, t.getExperience());
            ps.setInt(8, t.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Teacher WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Teacher findByTeacherId(int teacherId) {
        String sql = "SELECT * FROM Teacher WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Teacher(
                        rs.getInt("id"),
                        rs.getString("position"),
                        rs.getString("degree"),
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        rs.getString("residence"),
                        rs.getInt("experience")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
