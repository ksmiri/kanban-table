package fr.ks.kanban.serviceImpl;

import fr.ks.kanban.models.Colonne;
import fr.ks.kanban.repository.ColonneRepository;
import fr.ks.kanban.service.ColonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "colonneService")
public class ColonneServiceImpl implements ColonneService {

    @Autowired
    private ColonneRepository colonneRepository;

    @Override
    public Colonne ajouterColonne(String libelle) {
        Colonne colonne = new Colonne(libelle);
        colonneRepository.save(colonne);
        return colonne;
    }

    @Override
    public Colonne ajouterColonne(Colonne colonne) {
        colonneRepository.save(colonne);
        return colonne;
    }

    @Override
    public List<Colonne> recupererColonnes() {
        return colonneRepository.findAll();
    }

    @Override
    public Colonne getColById(Long id) {
        Optional<Colonne> col = colonneRepository.findById(id);
        if(col.isPresent()){
            return col.get();
        }
        return null;
    }

    @Override
    public boolean supprimerColonne(Long id) {
        Colonne colonne = getColById(id);
        if(!colonne.equals(null)){
            colonneRepository.delete(colonne);
            return true;
        }
        return false;
    }
}
