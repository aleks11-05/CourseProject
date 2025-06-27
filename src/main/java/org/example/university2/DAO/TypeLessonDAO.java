package org.example.university2.DAO;



import org.example.university2.Models.TypeLesson;
import org.example.university2.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeLessonDAO {
    private final Connection connection;

    public TypeLessonDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<TypeLesson> findAll() {
        List<TypeLesson> list = new ArrayList<>();
        String sql = "SELECT * FROM TypeLesson ORDER BY id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TypeLesson tl = new TypeLesson(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                list.add(tl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(TypeLesson tl) {
        String sql = "INSERT INTO TypeLesson(name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tl.getName());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    tl.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(TypeLesson tl) {
        String sql = "UPDATE TypeLesson SET name=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tl.getName());
            ps.setInt(2, tl.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM TypeLesson WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
