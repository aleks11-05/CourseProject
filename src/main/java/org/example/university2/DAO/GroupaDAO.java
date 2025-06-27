package org.example.university2.DAO;

import org.example.university2.Models.Groupa;
import org.example.university2.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupaDAO {
    private final Connection connection;

    public GroupaDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<Groupa> findAll() {
        List<Groupa> list = new ArrayList<>();
        String sql = "SELECT * FROM Groupa ORDER BY id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Groupa g = new Groupa(
                        rs.getInt("id"),
                        rs.getString("groupa_number"),
                        rs.getInt("size")
                );
                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void create(Groupa g) {
        String sql = "INSERT INTO Groupa(groupa_number, size) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, g.getGroupaNumber());
            ps.setInt(2, g.getSize());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    g.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Groupa g) {
        String sql = "UPDATE Groupa SET groupa_number=?, size=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, g.getGroupaNumber());
            ps.setInt(2, g.getSize());
            ps.setInt(3, g.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Groupa WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
