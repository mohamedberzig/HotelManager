
package com.hotelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanager.model.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}
