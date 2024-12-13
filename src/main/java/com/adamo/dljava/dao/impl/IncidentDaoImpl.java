package com.adamo.dljava.dao.impl;

import com.adamo.dljava.dao.IncidentDao;
import com.adamo.dljava.model.Incident;
import com.adamo.dljava.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IncidentDaoImpl implements IncidentDao {

    @Override
    public void addIncident(Incident incident) {
        String query = "INSERT INTO Incident (id, description, date, membreId) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, incident.getId());
            preparedStatement.setString(2, incident.getDescription());
            preparedStatement.setDate(3, Date.valueOf(incident.getDate()));
            preparedStatement.setString(4, incident.getMembreId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding incident", e);
        }
    }

    @Override
    public void addSetIncident(Set<Incident> incidents) {
        String query = "INSERT INTO Incident (id, description, date, membreId) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Disable auto-commit for batch processing
            connection.setAutoCommit(false);

            // Loop through the set of incidents and add them to the batch
            for (Incident incident : incidents) {
                preparedStatement.setString(1, incident.getId());
                preparedStatement.setString(2, incident.getDescription());
                preparedStatement.setDate(3, Date.valueOf(incident.getDate()));
                preparedStatement.setString(4, incident.getMembreId());
                preparedStatement.addBatch();
            }

            // Execute the batch
            preparedStatement.executeBatch();
            connection.commit();  // Commit the transaction
        } catch (SQLException e) {
            throw new RuntimeException("Error adding set of incidents", e);
        } finally {
            try {
                // Reset auto-commit to true
                DatabaseConnection.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Incident> getAllIncidents() {
        String query = "SELECT * FROM Incident";
        List<Incident> incidents = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                incidents.add(new Incident(
                        resultSet.getString("id"),
                        resultSet.getString("description"),
                        resultSet.getDate("date").toString(),
                        resultSet.getString("membreId")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all incidents", e);
        }
        return incidents;
    }
}
