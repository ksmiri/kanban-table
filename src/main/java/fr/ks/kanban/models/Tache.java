package fr.ks.kanban.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String intitule;

    private Date dateCreation;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Developpeur> developpeurs;

    @ManyToOne
    @NotNull(message = "merci de préciser la colonne")
    private Colonne colonne;

    @ManyToOne
    @NotNull(message = "merci de préciser le type de la tache")
    private TypeTache typeTache;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Tache() {
        setDateCreation(new Date());
    }

    public Tache(String intitule) {
        this();
        this.intitule = intitule;
    }

    public Tache(String intitule, Colonne colonne) {
        this(intitule);
        this.colonne = colonne;
    }

    public Tache(String intitule, Colonne colonne, TypeTache typeTache) {
        this(intitule, colonne);
        this.typeTache = typeTache;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @JsonIgnore // pour que ça n'apparaisse pas dans le resultat du web service en json
    public Date getDateCreation() {
        return dateCreation;
    }

    public String getDateCreationFormattee() {
        return dateFormat.format(dateCreation);

    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    // Synchroniser l'accees à la ressource dateFormat
    public static synchronized void setDateFormat(SimpleDateFormat dateFormat) {
        Tache.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Tache [id=" + id + ", intitule=" + intitule + ", getDateCreationFormattee()="
                + getDateCreationFormattee() + "]";
    }

    public List<Developpeur> getDeveloppeurs() {
        return developpeurs;
    }

    public void setDeveloppeurs(List<Developpeur> developpeurs) {
        this.developpeurs = developpeurs;
    }
}
