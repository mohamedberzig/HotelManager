
package com.hotelmanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmanager.model.Reservation;
import com.hotelmanager.model.StatutReservation;
import com.hotelmanager.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Réservation introuvable"));
    }

    public Reservation createReservation(Reservation reservation) {

        if (reservation.getDateDebut() == null
                || reservation.getDateFin() == null
                || !reservation.getDateFin().isAfter(reservation.getDateDebut())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La date de fin doit être après la date de début");
        }

        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existing = getReservationById(id);

        if (reservation.getDateDebut() == null
                || reservation.getDateFin() == null
                || !reservation.getDateFin().isAfter(reservation.getDateDebut())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La date de fin doit être après la date de début");
        }

        existing.setDateDebut(reservation.getDateDebut());
        existing.setDateFin(reservation.getDateFin());
        existing.setStatut(reservation.getStatut());
        existing.setClient(reservation.getClient());
        existing.setEmploye(reservation.getEmploye());
        existing.setChambre(reservation.getChambre());
        existing.setServices(reservation.getServices());

        return reservationRepository.save(existing);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }

    public List<Reservation> getReservationsByClient(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }

    public List<Reservation> getReservationsByStatut(
            StatutReservation statut) {
        return reservationRepository.findByStatut(statut);
    }
}
