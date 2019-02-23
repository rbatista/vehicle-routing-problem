package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(final Client client) {
        return clientRepository.save(client);
    }

    public Client find(final Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public Client update(final Client client) {
        final Client persistedClient = find(client.getId());
        persistedClient.setLatitude(client.getLatitude());
        persistedClient.setLongitude(client.getLongitude());
        return clientRepository.save(persistedClient);
    }

}
