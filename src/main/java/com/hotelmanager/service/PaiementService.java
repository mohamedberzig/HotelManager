
package com.hotelmanager.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmanager.model.Paiement;
import com.hotelmanager.repository.PaiementRepository;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    // Afficher tous les paiements
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    // Rechercher un paiement par ID
    public Paiement getPaiementById(Long id) {
        return paiementRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Paiement introuvable"));
    }

    // Ajouter un paiement
    public Paiement createPaiement(Paiement paiement) {
        if (paiement.getMontant() == null
                || paiement.getMontant().signum() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le montant doit être supérieur à zéro");
        }

        return paiementRepository.save(paiement);
    }

    // Modifier un paiement
    public Paiement updatePaiement(Long id, Paiement paiement) {
        Paiement existing = getPaiementById(id);

        if (paiement.getMontant() == null
                || paiement.getMontant().signum() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le montant doit être supérieur à zéro");
        }

        existing.setMontant(paiement.getMontant());
        existing.setDatePaiement(paiement.getDatePaiement());
        existing.setModePaiement(paiement.getModePaiement());
        existing.setReservation(paiement.getReservation());

        return paiementRepository.save(existing);
    }

    // Supprimer un paiement
    public void deletePaiement(Long id) {
        Paiement paiement = getPaiementById(id);
        paiementRepository.delete(paiement);
    }
}
