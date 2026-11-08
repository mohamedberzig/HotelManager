
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

import com.hotelmanager.model.ServiceHotel;
import com.hotelmanager.service.ServiceHotelService;

@RestController
@RequestMapping("/api/services")
public class ServiceHotelController {

    private final ServiceHotelService serviceHotelService;

    public ServiceHotelController(ServiceHotelService serviceHotelService) {
        this.serviceHotelService = serviceHotelService;
    }

    // Afficher tous les services de l'hôtel
    @GetMapping
    public List<ServiceHotel> getAllServices() {
        return serviceHotelService.getAllServices();
    }

    // Rechercher un service par ID
    @GetMapping("/{id}")
    public ServiceHotel getServiceById(@PathVariable Long id) {
        return serviceHotelService.getServiceById(id);
    }

    // Ajouter un service
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceHotel createService(@Valid @RequestBody ServiceHotel service) {
        return serviceHotelService.createService(service);
    }

    // Modifier un service
    @PutMapping("/{id}")
    public ServiceHotel updateService(
            @PathVariable Long id,
            @Valid @RequestBody ServiceHotel service) {
        return serviceHotelService.updateService(id, service);
    }

    // Supprimer un service
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable Long id) {
        serviceHotelService.deleteService(id);
    }
}
