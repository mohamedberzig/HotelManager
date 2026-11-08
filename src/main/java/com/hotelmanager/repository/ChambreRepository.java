
package com.hotelmanager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelmanager.model.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    // Vérifier si un numéro de chambre existe déjà
    boolean existsByNumero(String numero);

    // Rechercher les chambres disponibles entre deux dates
    @Query("""
        SELECT c FROM Chambre c
        WHERE c.statut = com.hotelmanager.StatutChambre.DISPONIBLE
        AND NOT EXISTS (
            SELECT r FROM Reservation r
            WHERE r.chambre = c
            AND r.statut <> com.hotelmanager.StatutReservation.ANNULEE
            AND r.dateDebut < :dateFin
            AND r.dateFin > :dateDebut
        )
    """)
    List<Chambre> findChambresDisponibles(
        @Param("dateDebut") LocalDate dateDebut,
        @Param("dateFin") LocalDate dateFin
    );

}
