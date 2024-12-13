package com.adamo.dljava.dao.impl;

import com.adamo.dljava.dao.MembreDao;
import com.adamo.dljava.model.Membre;
import com.adamo.dljava.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao {

    @Override
    public void addMembre(Membre membre) {
        String sql = "INSERT INTO Membre (id, nom, prenom, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membre.getId());
            stmt.setString(2, membre.getNom());
            stmt.setString(3, membre.getPrenom());
            stmt.setString(4, membre.getEmail());
            stmt.setString(5, membre.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Membre> getAllMembres() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM Membre";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                membres.add(new Membre(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }
}
