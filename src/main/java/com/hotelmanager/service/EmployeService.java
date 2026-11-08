
package com.hotelmanager.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmanager.model.Employe;
import com.hotelmanager.repository.EmployeRepository;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Employé introuvable"));
    }

    public Employe createEmploye(Employe employe) {
        if (employeRepository.existsByEmail(employe.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        return employeRepository.save(employe);
    }

    public Employe updateEmploye(Long id, Employe employe) {
        Employe existing = getEmployeById(id);

        if (!existing.getEmail().equals(employe.getEmail())
                && employeRepository.existsByEmail(employe.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        existing.setNom(employe.getNom());
        existing.setPrenom(employe.getPrenom());
        existing.setEmail(employe.getEmail());
        existing.setPoste(employe.getPoste());

        return employeRepository.save(existing);
    }

    public void deleteEmploye(Long id) {
        Employe employe = getEmployeById(id);
        employeRepository.delete(employe);
    }
}
