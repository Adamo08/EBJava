package com.adamo.dljava.dao;

import com.adamo.dljava.model.Incident;

import java.util.List;
import java.util.Set;

public interface IncidentDao {

    void addIncident(Incident incident);

    void addSetIncident(Set<Incident> id);

    List<Incident> getAllIncidents();
}