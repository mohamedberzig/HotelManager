
package com.hotelmanager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.hotelmanager.model.Chambre;
import com.hotelmanager.service.ChambreService;

@RestController
@RequestMapping("/api/chambres")
public class ChambreController {

    private final ChambreService chambreService;

    public ChambreController(ChambreService chambreService) {
        this.chambreService = chambreService;
    }

    // Afficher toutes les chambres
    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    // Rechercher les chambres disponibles entre deux dates
    @GetMapping("/disponibles")
    public List<Chambre> getChambresDisponibles(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateDebut,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateFin) {

        return chambreService.getChambresDisponibles(dateDebut, dateFin);
    }

    // Rechercher une chambre par ID
    @GetMapping("/{id}")
    public Chambre getChambreById(@PathVariable Long id) {
        return chambreService.getChambreById(id);
    }

    // Ajouter une chambre
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chambre createChambre(@Valid @RequestBody Chambre chambre) {
        return chambreService.createChambre(chambre);
    }

    // Modifier une chambre
    @PutMapping("/{id}")
    public Chambre updateChambre(
            @PathVariable Long id,
            @Valid @RequestBody Chambre chambre) {

        return chambreService.updateChambre(id, chambre);
    }

    // Supprimer une chambre
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }
}
