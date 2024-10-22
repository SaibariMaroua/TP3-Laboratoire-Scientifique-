package com.example.projetderecherche.Controller;

import com.example.projetderecherche.entities.ProjetDeRecherche;
import com.example.projetderecherche.Service.ProjetDeRechercheService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/v1/projets")

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Projets de Recherche",
                description = "Gérer les projets de recherche",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8080/"
        )
)
public class ProjetDeRechercheController {

    @Autowired
    private ProjetDeRechercheService projetService;
    @PostMapping
    @Operation(
            summary = "Ajouter un projet de recherche",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetDeRecherche.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Projet ajouté avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur de validation des données"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )


    public ResponseEntity<ProjetDeRecherche> add(@org.springframework.web.bind.annotation.RequestBody ProjetDeRecherche projet) {
        ProjetDeRecherche savedProjet = projetService.createProjet(projet);
        return ResponseEntity.ok(savedProjet);
    }

    @GetMapping
    @Operation(
            summary = "Récupérer la liste des projets de recherche",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur dans la requête")
            }
    )
    public ResponseEntity<List<ProjetDeRecherche>> getAll() {
        List<ProjetDeRecherche> projets = projetService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Récupérer un projet de recherche par son ID",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Projet récupéré avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class))),
                    @ApiResponse(responseCode = "404", description = "Projet non trouvé")
            }
    )
    public ResponseEntity<ProjetDeRecherche> getById(@PathVariable Long id) {
        ProjetDeRecherche projet = projetService.getProjetById(id);
        return projet == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(projet);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un projet de recherche par son ID",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Projet supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Projet non trouvé")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Mettre à jour un projet de recherche par son ID",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjetDeRecherche.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Projet mis à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetDeRecherche.class))),
                    @ApiResponse(responseCode = "404", description = "Projet non trouvé")
            }
    )
    public ResponseEntity<ProjetDeRecherche> update(@PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody ProjetDeRecherche projet) {
        ProjetDeRecherche updatedProjet = projetService.updateProjet(id, projet);
        return ResponseEntity.ok(updatedProjet);
    }
}
