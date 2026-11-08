
package com.hotelmanager.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmanager.model.ServiceHotel;
import com.hotelmanager.repository.ServiceHotelRepository;

@Service
public class ServiceHotelService {

    private final ServiceHotelRepository serviceHotelRepository;

    public ServiceHotelService(ServiceHotelRepository serviceHotelRepository) {
        this.serviceHotelRepository = serviceHotelRepository;
    }

    // Afficher tous les services
    public List<ServiceHotel> getAllServices() {
        return serviceHotelRepository.findAll();
    }

    // Rechercher un service par ID
    public ServiceHotel getServiceById(Long id) {
        return serviceHotelRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Service introuvable"));
    }

    // Ajouter un service
    public ServiceHotel createService(ServiceHotel service) {
        if (service.getPrix() == null || service.getPrix().signum() < 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le prix doit être positif ou égal à zéro");
        }

        return serviceHotelRepository.save(service);
    }

    // Modifier un service
    public ServiceHotel updateService(Long id, ServiceHotel service) {
        ServiceHotel existing = getServiceById(id);

        if (service.getPrix() == null || service.getPrix().signum() < 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le prix doit être positif ou égal à zéro");
        }

        existing.setNom(service.getNom());
        existing.setDescription(service.getDescription());
        existing.setPrix(service.getPrix());

        return serviceHotelRepository.save(existing);
    }

    // Supprimer un service
    public void deleteService(Long id) {
        ServiceHotel service = getServiceById(id);
        serviceHotelRepository.delete(service);
    }
}
