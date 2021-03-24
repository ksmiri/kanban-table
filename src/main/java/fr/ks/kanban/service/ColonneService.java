package fr.ks.kanban.service;

import fr.ks.kanban.models.Colonne;

import java.util.List;

public interface ColonneService {

    public Colonne ajouterColonne(String libelle);

    public List<Colonne> recupererColonnes();

    public Colonne getColById(Long id);
}
