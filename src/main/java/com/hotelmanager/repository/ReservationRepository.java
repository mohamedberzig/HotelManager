
package com.hotelmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanager.model.Reservation;
import com.hotelmanager.model.StatutReservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Rechercher les réservations d'un client
    List<Reservation> findByClientId(Long clientId);

    // Rechercher les réservations selon leur statut
    List<Reservation> findByStatut(StatutReservation statut);

}
