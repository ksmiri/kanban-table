package fr.ks.kanban.serviceImpl;

import fr.ks.kanban.models.TypeTache;
import fr.ks.kanban.repository.TypeTacheRepository;
import fr.ks.kanban.service.TypeTacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "typeTacheService")
public class TypeTacheServiceImpl implements TypeTacheService {

    @Autowired
    private TypeTacheRepository typeTacheRepository;

    @Override
    public TypeTache ajouterTypeTache(String libelle, String couleur) {
        TypeTache typeTache = new TypeTache(libelle, couleur);
        typeTacheRepository.save(typeTache);
        return typeTache;
    }

    @Override
    public List<TypeTache> recupererTypeTaches() {
        return typeTacheRepository.findAll();
    }

    @Override
    public TypeTache recupererTypeTacheById(Long id) {
        Optional<TypeTache> tt = typeTacheRepository.findById(id);
        if (tt.isPresent()) {
            return tt.get();
        }
        return null;
    }
}
