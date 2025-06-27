package org.example.university2.DAO;


import org.example.university2.Models.DistributionOfAcademicHours;
import org.example.university2.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DistributionOfAcademicHoursDAO {
    private final Connection connection;

    public DistributionOfAcademicHoursDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<DistributionOfAcademicHours> findAll() {
        List<DistributionOfAcademicHours> list = new ArrayList<>();
        String sql = "SELECT * FROM DistributionOfAcademicHours ORDER BY id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DistributionOfAcademicHours d = new DistributionOfAcademicHours(
                        rs.getInt("id"),
                        rs.getInt("subject_id"),
                        rs.getInt("groupa_id"),
                        rs.getInt("teacher_id"),
                        rs.getInt("lesson_type_id"),
                        rs.getInt("semester"),
                        rs.getInt("year")
                );
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(DistributionOfAcademicHours d) {
        String sql = "INSERT INTO DistributionOfAcademicHours(subject_id, groupa_id, teacher_id, lesson_type_id, semester, year) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, d.getSubject_id());
            ps.setInt(2, d.getGroupa_id());
            ps.setInt(3, d.getTeacher_id());
            ps.setInt(4, d.getLesson_type_id());
            ps.setInt(5, d.getSemester());
            ps.setInt(6, d.getYear());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    d.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(DistributionOfAcademicHours d) {
        String sql = "UPDATE DistributionOfAcademicHours SET subject_id=?, groupa_id=?, teacher_id=?, lesson_type_id=?, semester=?, year=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, d.getSubject_id());
            ps.setInt(2, d.getGroupa_id());
            ps.setInt(3, d.getTeacher_id());
            ps.setInt(4, d.getLesson_type_id());
            ps.setInt(5, d.getSemester());
            ps.setInt(6, d.getYear());
            ps.setInt(7, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM DistributionOfAcademicHours WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DistributionOfAcademicHours findByTeacherId(int teacherId) {
        String sql = "SELECT * FROM DistributionOfAcademicHours WHERE teacher_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DistributionOfAcademicHours(
                            rs.getInt("id"),
                            rs.getInt("subject_id"),
                            rs.getInt("groupa_id"),
                            rs.getInt("teacher_id"),
                            rs.getInt("lesson_type_id"),
                            rs.getInt("semester"),
                            rs.getInt("year")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
