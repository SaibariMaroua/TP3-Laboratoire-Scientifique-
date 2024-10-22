package com.example.projetderecherche.entities;

import com.example.projetderecherche.Modele.Enseignant; // Importer la classe Enseignant
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projets_de_recherche")
public class ProjetDeRecherche {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", length = 200, nullable = false)
    private String titre;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "id_enseignant")
    private Long id_enseignant;

    @JsonIgnoreProperties({"cne", "motDePasse"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Enseignant enseignant;
}
