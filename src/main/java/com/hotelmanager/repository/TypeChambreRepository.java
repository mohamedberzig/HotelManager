
package com.hotelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanager.model.TypeChambre;

@Repository
public interface TypeChambreRepository extends JpaRepository<TypeChambre, Long> {

}
