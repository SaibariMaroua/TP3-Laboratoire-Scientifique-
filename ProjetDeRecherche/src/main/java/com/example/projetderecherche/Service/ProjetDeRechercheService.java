package com.example.projetderecherche.Service;

import com.example.projetderecherche.Modele.Enseignant; // Importer le modèle Enseignant
import com.example.projetderecherche.Repository.ProjetDeRechercheRepository;
import com.example.projetderecherche.entities.ProjetDeRecherche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjetDeRechercheService {

    private ProjetDeRechercheRepository projetRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ProjetDeRechercheService(RestTemplate restTemplate, ProjetDeRechercheRepository projetRepository) {
        this.restTemplate = restTemplate;
        this.projetRepository = projetRepository;
    }

    // Créer un nouveau projet de recherche
    public ProjetDeRecherche createProjet(ProjetDeRecherche projet) {
        return projetRepository.save(projet);
    }

    // Obtenir tous les projets de recherche
    public List<ProjetDeRecherche> getAllProjets() {
        List<ProjetDeRecherche> projets = projetRepository.findAll();
        if (projets != null) {
            for (ProjetDeRecherche projet : projets) {
                Enseignant enseignant = restTemplate.getForObject("http://localhost:8085/v1/Enseignants/" + projet.getId_enseignant(), Enseignant.class);
                projet.setEnseignant(enseignant);
            }
        }
        return projets;
    }

    // Obtenir un projet de recherche par son ID
    public ProjetDeRecherche getProjetById(Long id) {
        ProjetDeRecherche projet = projetRepository.findById(id).orElse(null);
        if (projet != null) {
            Enseignant enseignant = restTemplate.getForObject("http://localhost:8085/v1/Enseignants/" + projet.getId_enseignant(), Enseignant.class);
            projet.setEnseignant(enseignant);
        }
        return projet;
    }

    // Mettre à jour un projet de recherche
    public ProjetDeRecherche updateProjet(Long id, ProjetDeRecherche newProjet) {
        return projetRepository.findById(id).map(projet -> {
            projet.setTitre(newProjet.getTitre());
            projet.setDescription(newProjet.getDescription());
            projet.setId_enseignant(newProjet.getId_enseignant());
            return projetRepository.save(projet);
        }).orElseThrow(() -> new RuntimeException("Projet non trouvé"));
    }

    // Supprimer un projet de recherche
    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
