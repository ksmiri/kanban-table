package fr.ks.kanban.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Colonne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCol;

    private String libelleCol;

    @Transient
    private String couleur;

    @OneToMany(mappedBy = "colonne")
    private List<Tache> taches;

    public Colonne() {
    }

    public Colonne(String libelle) {
        this.libelleCol = libelle;
    }

    public Long getIdCol() {
        return idCol;
    }

    public void setIdCol(Long idCol) {
        this.idCol = idCol;
    }

    public String getLibelleCol() {
        return libelleCol;
    }

    public void setLibelleCol(String libelleCol) {
        this.libelleCol = libelleCol;
    }

    @Override
    public String toString() {
        return "Colonne [idCol=" + idCol + ", libelleCol=" + libelleCol + ", couleur=" + couleur + ", taches=" + taches
                + "]";
    }
}
