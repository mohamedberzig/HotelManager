
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

import com.hotelmanager.model.Reservation;
import com.hotelmanager.model.StatutReservation;
import com.hotelmanager.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Afficher toutes les réservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Rechercher les réservations par client
    @GetMapping("/client/{clientId}")
    public List<Reservation> getReservationsByClient(
            @PathVariable Long clientId) {
        return reservationService.getReservationsByClient(clientId);
    }

    // Rechercher les réservations par statut
    @GetMapping("/statut/{statut}")
    public List<Reservation> getReservationsByStatut(
            @PathVariable StatutReservation statut) {
        return reservationService.getReservationsByStatut(statut);
    }

    // Rechercher une réservation par ID
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    // Ajouter une réservation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(
            @Valid @RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // Modifier une réservation
    @PutMapping("/{id}")
    public Reservation updateReservation(
            @PathVariable Long id,
            @Valid @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    // Supprimer une réservation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
