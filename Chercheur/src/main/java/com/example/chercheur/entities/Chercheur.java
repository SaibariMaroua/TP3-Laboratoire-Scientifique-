package com.example.chercheur.entities;

import com.example.chercheur.Modele.Enseignant;
import com.example.chercheur.Modele.Projet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chercheurs")
public class Chercheur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "numero_inscription", unique = true)
    private String numeroInscription;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "id_enseignant")
    private Long id_enseignant;

    @JsonIgnoreProperties({"cne", "motDePasse"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Enseignant enseignant;

    @Column(name = "id_projet")
    private Long id_projet;

    @JsonIgnoreProperties({"id"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Projet projet;
}
