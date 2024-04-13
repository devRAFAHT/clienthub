package com.andradscorporation.backend.services;

import com.andradscorporation.backend.dto.ClientDTO;
import com.andradscorporation.backend.entities.Client;
import com.andradscorporation.backend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll(){
        List<Client> clients = repository.findAll();
        List<ClientDTO> clientsDto = clients.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return clientsDto;
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> clientObj = repository.findById(id);
        Client clientEntity = clientObj.get();
        return new ClientDTO(clientEntity);
    }
}
