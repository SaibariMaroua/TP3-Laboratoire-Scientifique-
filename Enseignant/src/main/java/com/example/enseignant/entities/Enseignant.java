package com.example.enseignant.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "enseignants")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", length = 100)
    @NotNull(message = "Le nom est requis")
    private String nom;

    @Column(name = "prenom", length = 100)
    @NotNull(message = "Le prénom est requis")
    private String prenom;

    @Column(name = "cne", length = 20, unique = true)
    @NotNull(message = "Le CNE est requis")
    private String cne;

    @Column(name = "email", length = 100, unique = true)
    @Email(message = "L'email doit être valide")
    @NotNull(message = "L'email est requis")
    private String email;

    @Column(name = "mot_de_passe", length = 100)
    @NotNull(message = "Le mot de passe est requis")
    private String motDePasse;

    @Column(name = "thematique_recherche", length = 100)
    @NotNull(message = "La thématique de recherche est requise")
    private String thematiqueRecherche;

}
