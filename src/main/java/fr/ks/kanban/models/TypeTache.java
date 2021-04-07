package fr.ks.kanban.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class TypeTache implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeTache;

    private String nom;

    private String couleur;

    @OneToMany(mappedBy = "typeTache")
    private List<Tache> taches;

    public TypeTache() {
    }

    public TypeTache(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    public Long getIdTypeTache() {
        return idTypeTache;
    }

    public void setIdTypeTache(Long idTypeTache) {
        this.idTypeTache = idTypeTache;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "TypeTache [idTypeTache=" + idTypeTache + ", nom=" + nom + ", couleur=" + couleur + "]";
    }
}
