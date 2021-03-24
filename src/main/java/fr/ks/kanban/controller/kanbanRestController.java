package fr.ks.kanban.controller;

import fr.ks.kanban.exceptions.DeveloppeurInconnuException;
import fr.ks.kanban.exceptions.TacheInconnueException;
import fr.ks.kanban.models.Colonne;
import fr.ks.kanban.models.Developpeur;
import fr.ks.kanban.models.Tache;
import fr.ks.kanban.models.TypeTache;
import fr.ks.kanban.service.ColonneService;
import fr.ks.kanban.service.DeveloppeurService;
import fr.ks.kanban.service.TacheService;
import fr.ks.kanban.service.TypeTacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kanban")
public class kanbanRestController {

    private final DeveloppeurService developpeurService;
    private final ColonneService colonneService;
    private final TacheService tacheService;
    private final TypeTacheService typeTacheService;

    @Autowired
    public kanbanRestController(DeveloppeurService developpeurService, ColonneService colonneService, TacheService tacheService, TypeTacheService typeTacheService) {
        this.developpeurService = developpeurService;
        this.colonneService = colonneService;
        this.tacheService = tacheService;
        this.typeTacheService = typeTacheService;
    }

    @GetMapping(value = "/taches", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tache> tachesGet() {
        return tacheService.recupererTaches();
    }

    @PostMapping(value = "/taches/{intitule}/{nomCol}/{nomTypeTache}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tache ajouterTache(@PathVariable String intitule, @PathVariable String nomCol,
                              @PathVariable String nomTypeTache) {
        Tache tache = tacheService.ajouterTache(intitule, nomCol, nomTypeTache);
        return tache;
    }

    @PostMapping(value = "/developpeurs/{nomDev}/{prenomDev}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Developpeur ajouterDeveloppeur(@PathVariable String nomDev, @PathVariable String prenomDev) {
        Developpeur developpeur = developpeurService.ajouterDeveloppeur(nomDev, prenomDev);
        return developpeur;
    }

    @DeleteMapping(value = "/developpeurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteDeveloppeur(@PathVariable Long id) {
        return developpeurService.supprimerDeveloppeur(id);
    }

    @PutMapping(value = "/taches/{id}/{intitule}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tache mettreAjourTache(@PathVariable Long id, @PathVariable String intitule) {
        return tacheService.mettreAJourTache(id, intitule);
    }

    //@PostConstruct
    public void init() {
        tacheService.effacerTaches();
        System.out.println("Lancement de la méthode init");

        // Les Devs
        Developpeur dev = developpeurService.ajouterDeveloppeur("SMIRI", "Khaled");

        // Colonnes par défaut
        Colonne colonneTODO = colonneService.ajouterColonne("TODO");
        colonneService.ajouterColonne("DOING");
        colonneService.ajouterColonne("TESTING");
        colonneService.ajouterColonne("DONE");

        // TacheType
        TypeTache bugType = typeTacheService.ajouterTypeTache("bug", "red");
        TypeTache taskType = typeTacheService.ajouterTypeTache("task", "yellow");
        TypeTache enablerType = typeTacheService.ajouterTypeTache("enabler", "blue");

        // Les taches
        Tache tacheEnabler1 = tacheService.ajouterTache("Developper Repository", colonneTODO, enablerType);
        System.out.println(tacheService.ajouterTache("Developper service", colonneTODO, enablerType));
        try {
            tacheService.affecterDevATache(dev.getId(), tacheEnabler1.getId());
        } catch (TacheInconnueException e) {
            System.exit(-1);
        } catch (DeveloppeurInconnuException e) {
            System.exit(-1);
        }
    }
}
