
package com.hotelmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmanager.model.Client;
import com.hotelmanager.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Client introuvable"));
    }

    public Client createClient(Client client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Client existing = getClientById(id);

        if (!existing.getEmail().equals(client.getEmail())
                && clientRepository.existsByEmail(client.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setEmail(client.getEmail());
        existing.setTelephone(client.getTelephone());

        return clientRepository.save(existing);
    }

    public void deleteClient(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }
}
