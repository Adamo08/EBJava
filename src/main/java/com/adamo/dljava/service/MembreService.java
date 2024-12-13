package com.adamo.dljava.service;

import com.adamo.dljava.model.Membre;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MembreService {

    public List<Membre> chargerListeMembre(String nomFichier) {
        List<Membre> membres = new ArrayList<>();
        Set<String> uniqueIdentifiers = new HashSet<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(nomFichier))) {
            String[] line;
            // Skip the header if exists
            csvReader.readNext();

            // Read each line in the CSV file
            while ((line = csvReader.readNext()) != null) {
                if (line.length < 4) {
                    continue;  // Skip incomplete lines
                }

                String nom = line[0];
                String prenom = line[1];
                String email = line[2];
                String phone = line[3];

                // Create a unique identifier based on email and phone
                String uniqueIdentifier = email + "|" + phone;

                // Check if this identifier already exists
                if (!uniqueIdentifiers.contains(uniqueIdentifier)) {
                    // Add the unique identifier to the set
                    uniqueIdentifiers.add(uniqueIdentifier);

                    // Create a new Membre and add it to the list
                    Membre membre = new Membre(nom, prenom, email, phone);
                    membres.add(membre);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return membres;
    }
}
