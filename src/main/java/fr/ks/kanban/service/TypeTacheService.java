package fr.ks.kanban.service;

import fr.ks.kanban.models.TypeTache;

import java.util.List;

public interface TypeTacheService {

    TypeTache ajouterTypeTache(String libelle, String couleur);

    TypeTache ajouterTypeTache(TypeTache typeTache);

    List<TypeTache> recupererTypeTaches();

    TypeTache recupererTypeTacheById(Long id);

    public boolean supprimerTypeTache(Long id);

}
