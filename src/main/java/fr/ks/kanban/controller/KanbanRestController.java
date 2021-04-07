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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kanban")
public class KanbanRestController {

    private final DeveloppeurService developpeurService;
    private final ColonneService colonneService;
    private final TacheService tacheService;
    private final TypeTacheService typeTacheService;

    @Autowired
    public KanbanRestController(DeveloppeurService developpeurService, ColonneService colonneService, TacheService tacheService, TypeTacheService typeTacheService) {
        this.developpeurService = developpeurService;
        this.colonneService = colonneService;
        this.tacheService = tacheService;
        this.typeTacheService = typeTacheService;
    }

    //
    //##### Web services taches #####
    //

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

    @PutMapping(value = "/taches/{id}/{intitule}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tache mettreAjourTache(@PathVariable Long id, @PathVariable String intitule) {
        return tacheService.mettreAJourTache(id, intitule);
    }

    //
    //##### Web services Développeurs #####
    //

    @GetMapping(value = "/developpeurs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Developpeur> developpeursGet() {
        return developpeurService.recupererDeveloppeurs();
    }

    @PostMapping(value = "/developpeurs/{nomDev}/{prenomDev}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Developpeur ajouterDeveloppeur(@PathVariable String nomDev, @PathVariable String prenomDev) {
        return developpeurService.ajouterDeveloppeur(nomDev, prenomDev);
    }

    @PostMapping(value = "/developpeurs/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Developpeur ajouterDeveloppeur(@Valid  @RequestBody Developpeur developpeur) {
        return developpeurService.ajouterDeveloppeur(developpeur);
    }

    @DeleteMapping(value = "/developpeurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteDeveloppeur(@PathVariable Long id) {
        return developpeurService.supprimerDeveloppeur(id);
    }

    //
    //##### Web services Colonnes #####
    //

    @GetMapping(value = "/colonnes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Colonne> colonnesGet() {
        return colonneService.recupererColonnes();
    }

    @PostMapping(value = "/colonnes/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Colonne ajouterColonne(@RequestBody Colonne colonne) {
        return colonneService.ajouterColonne(colonne);
    }

    @DeleteMapping(value = "/colonnes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteColonne(@PathVariable Long id) {
        return colonneService.supprimerColonne(id);
    }

    //
    //##### Web services Types Taches #####
    //

    @GetMapping(value = "/typestaches", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeTache> typeTachesGet() {
        return typeTacheService.recupererTypeTaches();
    }

    @PostMapping(value = "/typestaches/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeTache ajouterTypeTache(@RequestBody TypeTache typeTache) {
        return typeTacheService.ajouterTypeTache(typeTache);
    }

    @DeleteMapping(value = "/typestaches/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteTypeTache(@PathVariable Long id) {
        return typeTacheService.supprimerTypeTache(id);
    }

    //##########################################################################################
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
