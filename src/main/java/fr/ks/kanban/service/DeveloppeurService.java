package fr.ks.kanban.service;

import fr.ks.kanban.models.Developpeur;

import java.util.List;

public interface DeveloppeurService {

    public Developpeur ajouterDeveloppeur(String nom, String prenom);

    public Developpeur ajouterDeveloppeur(Developpeur developpeur);

    public List<Developpeur> recupererDeveloppeurs();

    public Developpeur getDevById(Long id);

    public boolean supprimerDeveloppeur(Long id);

}
