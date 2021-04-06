package fr.ks.kanban.repository;

import fr.ks.kanban.models.Developpeur;
import fr.ks.kanban.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Long> {

    // Méthode bien nommée, Spring Data va générer pour nous la requête JPQL
    // qui va remplacer @Query et @Param
    List<Tache> findTacheByIntituleStartingWith(String intitule);

    /**
     * cette methode retourne toutes les taches dont la liste des developpeurs contient un dev passé en paramètre
     */
    List<Tache> findAllByDeveloppeursContaining(Developpeur developpeur);

    /**
     * cette methode renvoie toutes les tâches créées entere deux dates
     */
    List<Tache> findAllByDateCreationBetween (Date dateDebut, Date dateFin);

    /**
     * cette méthode renvoie toutes les tâches créées entre deux dates et dans la liste des développeurs contient le développeur donné en param
     */
    List<Tache> findAllByDeveloppeursContainingAndDateCreationBetween (Developpeur developpeur, Date dateDebut, Date dateFin);

    /**
     * cette methode renvoie toutes les tâches dont la liste de dev ne contient pas le dev passé en param
     */
    List<Tache> findAllByDeveloppeursNotContaining(Developpeur developpeur);
}
