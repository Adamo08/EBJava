package com.adamo.dljava.dao;

import com.adamo.dljava.model.Membre;

import java.util.List;

public interface MembreDao {

    void addMembre(Membre membre);

    List<Membre> getAllMembres();
}
