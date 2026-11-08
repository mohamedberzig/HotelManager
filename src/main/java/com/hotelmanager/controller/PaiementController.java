
package com.hotelmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.hotelmanager.model.Paiement;
import com.hotelmanager.service.PaiementService;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // Afficher tous les paiements
    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    // Rechercher un paiement par ID
    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    // Ajouter un paiement
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paiement createPaiement(@Valid @RequestBody Paiement paiement) {
        return paiementService.createPaiement(paiement);
    }

    // Modifier un paiement
    @PutMapping("/{id}")
    public Paiement updatePaiement(
            @PathVariable Long id,
            @Valid @RequestBody Paiement paiement) {
        return paiementService.updatePaiement(id, paiement);
    }

    // Supprimer un paiement
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
    }
}
