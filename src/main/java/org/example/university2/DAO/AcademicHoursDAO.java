package org.example.university2.DAO;

import org.example.university2.Models.AcademicHours;
import org.example.university2.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcademicHoursDAO {
    private final Connection connection;

    public AcademicHoursDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<AcademicHours> findAll() {
        List<AcademicHours> list = new ArrayList<>();
        String sql = "SELECT * FROM AcademicHours ORDER BY subject_id, lesson_type_id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AcademicHours ah = new AcademicHours(
                        rs.getInt("subject_id"),
                        rs.getInt("lesson_type_id"),
                        rs.getInt("num_ak_hours")
                );
                list.add(ah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(AcademicHours ah) {
        String sql = "INSERT INTO AcademicHours(subject_id, lesson_type_id, num_ak_hours) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ah.getSubjectId());
            ps.setInt(2, ah.getLessonTypeId());
            ps.setInt(3, ah.getNumAkHours());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(AcademicHours ah) {
        String sql = "UPDATE AcademicHours SET num_ak_hours=? WHERE subject_id=? AND lesson_type_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ah.getNumAkHours());
            ps.setInt(2, ah.getSubjectId()); // id
            ps.setInt(3, ah.getLessonTypeId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id, int lessonTypeId) {
        String sql = "DELETE FROM AcademicHours WHERE subject_id=? AND lesson_type_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, lessonTypeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
