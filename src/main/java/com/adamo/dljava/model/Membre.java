package com.adamo.dljava.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Membre {

    @EqualsAndHashCode.Include
    private String id;

    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    public Membre(String nom, String prenom, String email, String phone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
    }
}
