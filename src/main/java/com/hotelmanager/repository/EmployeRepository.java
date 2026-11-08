
package com.hotelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanager.model.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    boolean existsByEmail(String email);

}
