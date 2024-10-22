package dcc.tp2.security_microservice.Modele;

public class Chercheur {
    private Long id;
    private String nom;
    private String prenom;
    private String numeroInscription; // Correspond à "numero_inscription" dans la base de données
    private String motDePasse;
    private Long id_enseignant; // Référence à l'enseignant
    private Long id_projet; // Référence au projet

    public Chercheur(Long id, String nom, String prenom, String numeroInscription, String motDePasse, Long id_enseignant, Long id_projet) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroInscription = numeroInscription;
        this.motDePasse = motDePasse;
        this.id_enseignant = id_enseignant;
        this.id_projet = id_projet;
    }

    public Chercheur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroInscription() {
        return numeroInscription;
    }

    public void setNumeroInscription(String numeroInscription) {
        this.numeroInscription = numeroInscription;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Long getId_enseignant() {
        return id_enseignant;
    }

    public void setId_enseignant(Long id_enseignant) {
        this.id_enseignant = id_enseignant;
    }

    public Long getId_projet() {
        return id_projet;
    }

    public void setId_projet(Long id_projet) {
        this.id_projet = id_projet;
    }
}
