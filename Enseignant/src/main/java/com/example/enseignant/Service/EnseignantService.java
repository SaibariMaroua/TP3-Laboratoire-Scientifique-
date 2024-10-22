package com.example.enseignant.Service;

import com.example.enseignant.Repository.EnseignantRepository;
import com.example.enseignant.entities.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    // Méthode pour créer un nouvel enseignant
    public Enseignant createEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    // Méthode pour obtenir tous les enseignants
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    // Méthode pour obtenir un enseignant par son ID
    public Enseignant getEnseignantById(Long id) {
        return enseignantRepository.findById(id).orElse(null);
    }

    // Méthode pour supprimer un enseignant
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }

    // Méthode pour mettre à jour un enseignant
    public Enseignant updateEnseignant(Long id, Enseignant newEnseignant) {
        return enseignantRepository.findById(id).map(enseignant -> {
            enseignant.setNom(newEnseignant.getNom());
            enseignant.setPrenom(newEnseignant.getPrenom());
            enseignant.setCne(newEnseignant.getCne());
            enseignant.setEmail(newEnseignant.getEmail());
            enseignant.setMotDePasse(newEnseignant.getMotDePasse());
            enseignant.setThematiqueRecherche(newEnseignant.getThematiqueRecherche());
            return enseignantRepository.save(enseignant);
        }).orElseThrow(() -> new RuntimeException("Enseignant non trouvé"));
    }
}
