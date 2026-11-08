
package com.hotelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanager.model.ServiceHotel;

@Repository
public interface ServiceHotelRepository extends JpaRepository<ServiceHotel, Long> {

}
