package fr.ks.kanban.repository;

import fr.ks.kanban.models.Colonne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColonneRepository extends JpaRepository<Colonne, Long> {

    Colonne findByLibelleCol(String nomColonne);

}
