package fr.ks.kanban.serviceImpl;

import fr.ks.kanban.models.Developpeur;
import fr.ks.kanban.repository.DeveloppeurRepository;
import fr.ks.kanban.service.DeveloppeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "developpeurServiceImpl")
public class DeveloppeurServiceImpl implements DeveloppeurService {

    @Autowired
    private DeveloppeurRepository developpeurRepository;

    @Override
    public Developpeur ajouterDeveloppeur(String nom, String prenom) {
        Developpeur developpeur = new Developpeur(nom, prenom);
        developpeurRepository.save(developpeur);
        return developpeur;
    }

    @Override
    public List<Developpeur> recupererDeveloppeurs() {
        return developpeurRepository.findAll();
    }

    @Override
    public Developpeur getDevById(Long id) {
        Optional<Developpeur> dev = developpeurRepository.findById(id);
        if (dev.isPresent()) {
            return dev.get();
        }
        return null;
    }

    @Override
    public boolean supprimerDeveloppeur(Long id) {
        Developpeur developpeur = getDevById(id);
        if (!developpeur.equals(null)) {
            developpeurRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
