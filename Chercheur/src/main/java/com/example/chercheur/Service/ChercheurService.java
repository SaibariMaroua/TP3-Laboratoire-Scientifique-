package com.example.chercheur.Service;

import com.example.chercheur.Modele.Enseignant;
import com.example.chercheur.Modele.Projet;
import com.example.chercheur.Repository.ChercheurRepository;
import com.example.chercheur.entities.Chercheur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChercheurService {

    private final ChercheurRepository chercheurRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ChercheurService(ChercheurRepository chercheurRepository) {
        this.chercheurRepository = chercheurRepository;
        this.restTemplate = new RestTemplate();
    }

    // Créer un nouveau chercheur
    public Chercheur createChercheur(Chercheur chercheur) {
        return chercheurRepository.save(chercheur);
    }

    public List<Chercheur> getAllChercheurs() {
        List<Chercheur> chercheurList = chercheurRepository.findAll();
        if (chercheurList != null) {
            for (Chercheur c : chercheurList) {
                Enseignant enseignant = restTemplate.getForObject("http://localhost:8085/v1/Enseignants/" + c.getId_enseignant(), Enseignant.class);
                Projet projet = restTemplate.getForObject("http://localhost:8083/v1/projets/" + c.getId_projet(), Projet.class);
                c.setEnseignant(enseignant);
                c.setProjet(projet);
            }
        }
        return chercheurList;
    }


    // Obtenir un chercheur par son ID
    // Obtenir un chercheur par son ID
    public Chercheur getChercheurById(Long id) {
        Chercheur chercheur = chercheurRepository.findById(id).orElse(null);
        if (chercheur != null) {
            Enseignant enseignant = restTemplate.getForObject("http://localhost:8085/v1/Enseignants/" + chercheur.getId_enseignant(), Enseignant.class);
            Projet projet = restTemplate.getForObject("http://localhost:8083/v1/projets/" + chercheur.getId_projet(), Projet.class);

            // Log pour vérifier l'ID du projet
            System.out.println("ID Projet: " + chercheur.getId_projet());
            System.out.println("Projet récupéré: " + projet);  // Cela devrait afficher l'objet projet

            chercheur.setEnseignant(enseignant);
            chercheur.setProjet(projet);
        }
        return chercheur;
    }


    // Mettre à jour un chercheur
    public Chercheur updateChercheur(Long id, Chercheur newChercheur) {
        return chercheurRepository.findById(id).map(chercheur -> {
            chercheur.setNom(newChercheur.getNom());
            chercheur.setPrenom(newChercheur.getPrenom());
            chercheur.setNumeroInscription(newChercheur.getNumeroInscription());
            chercheur.setMotDePasse(newChercheur.getMotDePasse());
            chercheur.setId_enseignant(newChercheur.getId_enseignant());
            chercheur.setId_projet(newChercheur.getId_projet());
            return chercheurRepository.save(chercheur);
        }).orElseThrow(() -> new RuntimeException("Chercheur non trouvé"));
    }

    // Supprimer un chercheur
    public void deleteChercheur(Long id) {
        chercheurRepository.deleteById(id);
    }
}
