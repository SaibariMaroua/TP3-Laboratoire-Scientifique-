package com.example.chercheur.Controller;

import com.example.chercheur.Service.ChercheurService;
import com.example.chercheur.entities.Chercheur;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/Chercheurs")

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Chercheurs",
                description = "Gérer les chercheurs et leurs informations",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8080/"
        )
)
public class ChercheurController {

    @Autowired
    private ChercheurService chercheurService;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_CHERCHEUR')")
    @PostMapping
    @Operation(
            summary = "Ajouter un chercheur",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Chercheur.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ajouté avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Chercheur.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur de validation des données"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )
    public ResponseEntity<Chercheur> add(@org.springframework.web.bind.annotation.RequestBody Chercheur chercheur) {
        Chercheur savedChercheur = chercheurService.createChercheur(chercheur);
        return ResponseEntity.ok(savedChercheur);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    @Operation(
            summary = "Récupérer la liste des chercheurs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Chercheur.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur dans la requête")
            }
    )
    public ResponseEntity<List<Chercheur>> getAll() {
        List<Chercheur> chercheurs = chercheurService.getAllChercheurs();
        return ResponseEntity.ok(chercheurs);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_CHERCHEUR')")
    @GetMapping("/{id}")
    @Operation(
            summary = "Récupérer un chercheur par son ID",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chercheur récupéré avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Chercheur.class))),
                    @ApiResponse(responseCode = "404", description = "Chercheur non trouvé")
            }
    )
    public ResponseEntity<Chercheur> getById(@PathVariable Long id) {
        Chercheur chercheur = chercheurService.getChercheurById(id);
        return chercheur == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(chercheur);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un chercheur par son ID",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chercheur supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Chercheur non trouvé")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chercheurService.deleteChercheur(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_CHERCHEUR')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Mettre à jour un chercheur par son ID",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chercheur.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chercheur mis à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Chercheur.class))),
                    @ApiResponse(responseCode = "404", description = "Chercheur non trouvé")
            }
    )
    public ResponseEntity<Chercheur> update(@PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody Chercheur chercheur) {
        Chercheur updatedChercheur = chercheurService.updateChercheur(id, chercheur);
        return ResponseEntity.ok(updatedChercheur);
    }
}
