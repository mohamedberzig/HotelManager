
package com.hotelmanager.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmanager.model.TypeChambre;
import com.hotelmanager.repository.TypeChambreRepository;

@Service
public class TypeChambreService {

    private final TypeChambreRepository typeChambreRepository;

    public TypeChambreService(TypeChambreRepository typeChambreRepository) {
        this.typeChambreRepository = typeChambreRepository;
    }

    // Afficher tous les types de chambres
    public List<TypeChambre> getAllTypesChambres() {
        return typeChambreRepository.findAll();
    }

    // Rechercher un type de chambre par ID
    public TypeChambre getTypeChambreById(Long id) {
        return typeChambreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Type de chambre introuvable"));
    }

    // Ajouter un type de chambre
    public TypeChambre createTypeChambre(TypeChambre typeChambre) {
        return typeChambreRepository.save(typeChambre);
    }

    // Modifier un type de chambre
    public TypeChambre updateTypeChambre(Long id, TypeChambre typeChambre) {

        TypeChambre existing = getTypeChambreById(id);

        existing.setNom(typeChambre.getNom());
        existing.setDescription(typeChambre.getDescription());
        existing.setPrixNuit(typeChambre.getPrixNuit());

        return typeChambreRepository.save(existing);
    }

    // Supprimer un type de chambre
    public void deleteTypeChambre(Long id) {
        TypeChambre typeChambre = getTypeChambreById(id);
        typeChambreRepository.delete(typeChambre);
    }
}
