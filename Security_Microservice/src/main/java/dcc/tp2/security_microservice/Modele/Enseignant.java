package dcc.tp2.security_microservice.Modele;

public class Enseignant {
    private Long id;
    private String nom;
    private String prenom;
    private String cne;
    private String email;
    private String motDePasse;
    private String thematiqueRecherche;

    public Enseignant(Long id, String nom, String prenom, String cne, String email, String motDePasse, String thematiqueRecherche) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.email = email;
        this.motDePasse = motDePasse;
        this.thematiqueRecherche = thematiqueRecherche;
    }

    public Enseignant() {
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

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getThematiqueRecherche() {
        return thematiqueRecherche;
    }

    public void setThematiqueRecherche(String thematiqueRecherche) {
        this.thematiqueRecherche = thematiqueRecherche;
    }
}
