package fr.ks.kanban.repository;

import fr.ks.kanban.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Long> {

}
