package com.example.enseignant.Controller;

import com.example.enseignant.Service.EnseignantService;
import com.example.enseignant.entities.Enseignant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/Enseignants")
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Enseignants",
                description = "Gérer les enseignants et leurs informations",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8085/"
        )
)
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    @PostMapping
    @Operation(
            summary = "Ajouter un enseignant",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Enseignant.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Ajouté avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Enseignant.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur de validation des données"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )

    public ResponseEntity<Enseignant> add(@Valid @org.springframework.web.bind.annotation.RequestBody Enseignant enseignant) {
        // Vérifiez les données entrantes
        System.out.println("Enseignant reçu : " + enseignant);

        Enseignant savedEnseignant = enseignantService.createEnseignant(enseignant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEnseignant);
    }



    @GetMapping
    @Operation(
            summary = "Récupérer la liste des enseignants",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Enseignant.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur dans la requête")
            }
    )
    public ResponseEntity<List<Enseignant>> getAll() {
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        return ResponseEntity.ok(enseignants);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Récupérer un enseignant par son ID",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Enseignant récupéré avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Enseignant.class))),
                    @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
            }
    )
    public ResponseEntity<Enseignant> getById(@PathVariable Long id) {
        Enseignant enseignant = enseignantService.getEnseignantById(id);
        return enseignant == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(enseignant);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un enseignant par son ID",
            parameters = @Parameter(name = "id", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Enseignant supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Mettre à jour un enseignant par son ID",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Enseignant.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Enseignant mis à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Enseignant.class))),
                    @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
            }
    )
    public ResponseEntity<Enseignant> update(@PathVariable Long id, @Valid @org.springframework.web.bind.annotation.RequestBody Enseignant enseignant) {
        Enseignant updatedEnseignant = enseignantService.updateEnseignant(id, enseignant);
        return ResponseEntity.ok(updatedEnseignant);
    }
}
