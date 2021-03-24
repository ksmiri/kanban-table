package fr.ks.kanban.serviceImpl;

import fr.ks.kanban.exceptions.DeveloppeurInconnuException;
import fr.ks.kanban.exceptions.TacheInconnueException;
import fr.ks.kanban.models.Colonne;
import fr.ks.kanban.models.Developpeur;
import fr.ks.kanban.models.Tache;
import fr.ks.kanban.models.TypeTache;
import fr.ks.kanban.repository.ColonneRepository;
import fr.ks.kanban.repository.DeveloppeurRepository;
import fr.ks.kanban.repository.TacheRepository;
import fr.ks.kanban.repository.TypeTacheRepository;
import fr.ks.kanban.service.DeveloppeurService;
import fr.ks.kanban.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "tacheService")
public class TacheServiceImpl implements TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private DeveloppeurRepository developpeurRepository;

    @Autowired
    private ColonneRepository colonneRepository;

    @Autowired
    private TypeTacheRepository typeTacheRepository;

    @Autowired
    private DeveloppeurService developpeurService;

    @Autowired
    private TacheService tacheService;

    @Override
    public Tache ajouterTache(String intitule, Colonne colonne, TypeTache typeTache) {
        Tache tache = new Tache(intitule, colonne, typeTache);
        tacheRepository.save(tache);
        return tache;
    }

    @Override
    public Tache ajouterTache(String intitule, String nomColonne, String nomTypeTache) {
        Colonne colonne = colonneRepository.findByLibelleCol(nomColonne);
        TypeTache typeTache = typeTacheRepository.findByNom(nomTypeTache);
        return ajouterTache(intitule, colonne, typeTache);
    }

    @Override
    public List<Tache> recupererTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public void effacerTaches() {
        tacheRepository.deleteAll();
    }

    @Override
    public Tache affecterDevATache(Long idDev, Long idTache) throws TacheInconnueException, DeveloppeurInconnuException {
        Developpeur developpeur = developpeurService.getDevById(idDev);
        Tache tache = tacheService.getTache(idTache);
        if(developpeur.equals(null)){
            throw new DeveloppeurInconnuException("Développeur non trouvé");
        }
        if(tache.equals(null)){
            throw new TacheInconnueException("Tache non trouvé");
        }
        tache.getDeveloppeurs().add(developpeur);
        return tache;
    }

    @Override
    public Tache getTache(Long id) {
        Optional<Tache> t = tacheRepository.findById(id);
        if (t.isPresent()) {
            t.get();
        }
        return null;
    }

    @Override
    public boolean effacerTache(Long id) {
        Tache tache = getTache(id);
        if (!tache.equals(null)) {
            tacheRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Tache mettreAJourTache(Long id, String intitule) {
        Tache tache = getTache(id);
        if (tache.equals(null)) {
            return null;
        }
        tache.setIntitule(intitule);
        tacheRepository.save(tache);
        return tache;
    }
}
