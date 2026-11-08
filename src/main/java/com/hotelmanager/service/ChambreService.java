
package com.hotelmanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmanager.model.Chambre;
import com.hotelmanager.repository.ChambreRepository;

@Service
public class ChambreService {

    private final ChambreRepository chambreRepository;

    public ChambreService(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    // Afficher toutes les chambres
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    // Rechercher une chambre par ID
    public Chambre getChambreById(Long id) {
        return chambreRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Chambre introuvable"));
    }

    // Ajouter une chambre
    public Chambre createChambre(Chambre chambre) {

        if (chambreRepository.existsByNumero(chambre.getNumero())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Numéro de chambre déjà utilisé");
        }

        return chambreRepository.save(chambre);
    }

    // Modifier une chambre
    public Chambre updateChambre(Long id, Chambre chambre) {

        Chambre existing = getChambreById(id);

        if (!existing.getNumero().equals(chambre.getNumero())
                && chambreRepository.existsByNumero(chambre.getNumero())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Numéro de chambre déjà utilisé");
        }

        existing.setNumero(chambre.getNumero());
        existing.setEtage(chambre.getEtage());
        existing.setStatut(chambre.getStatut());
        existing.setTypeChambre(chambre.getTypeChambre());

        return chambreRepository.save(existing);
    }

    // Supprimer une chambre
    public void deleteChambre(Long id) {
        Chambre chambre = getChambreById(id);
        chambreRepository.delete(chambre);
    }

    // Rechercher les chambres disponibles entre deux dates
    public List<Chambre> getChambresDisponibles(
            LocalDate dateDebut, LocalDate dateFin) {

        if (dateDebut == null || dateFin == null
                || !dateFin.isAfter(dateDebut)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La date de fin doit être après la date de début");
        }

        return chambreRepository.findChambresDisponibles(
            dateDebut, dateFin);
    }
}
