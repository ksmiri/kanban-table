package fr.ks.kanban.service;

import fr.ks.kanban.exceptions.DeveloppeurInconnuException;
import fr.ks.kanban.exceptions.TacheInconnueException;
import fr.ks.kanban.models.Colonne;
import fr.ks.kanban.models.Tache;
import fr.ks.kanban.models.TypeTache;

import java.util.List;

public interface TacheService {

    Tache ajouterTache(String intitule, Colonne colonne, TypeTache typeTache);

    Tache ajouterTache(String intitule, String nomColonne, String nomTypeTache);

    List<Tache> recupererTaches();

    void effacerTaches();

    Tache affecterDevATache(Long idDev, Long idTache) throws TacheInconnueException, DeveloppeurInconnuException;

    Tache getTache(Long id);

    boolean effacerTache(Long id);

    Tache mettreAJourTache(Long id, String intitule);

}
