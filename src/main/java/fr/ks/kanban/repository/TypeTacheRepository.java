package fr.ks.kanban.repository;

import fr.ks.kanban.models.TypeTache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeTacheRepository extends JpaRepository<TypeTache, Long> {

    TypeTache findByNom(String nomTypeTache);

}
