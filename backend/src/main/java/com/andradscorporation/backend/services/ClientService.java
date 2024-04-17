package com.andradscorporation.backend.services;

import com.andradscorporation.backend.dto.ClientDTO;
import com.andradscorporation.backend.entities.Client;
import com.andradscorporation.backend.repositories.ClientRepository;
import com.andradscorporation.backend.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(Pageable pageable){
        Page<Client> clients = repository.findAll(pageable);
        return clients.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> clientObj = repository.findById(id);
        Client clientEntity = clientObj.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientDTO(clientEntity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client clientEntity = new Client();
        copyDtoToEntity(clientDTO, clientEntity);
        clientEntity = repository.save(clientEntity);
        return new ClientDTO(clientEntity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            Client clientEntity = repository.getOne(id);
            copyDtoToEntity(clientDTO, clientEntity);
            repository.save(clientEntity);
            return new ClientDTO(clientEntity);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    private void copyDtoToEntity(ClientDTO clientDTO, Client clientEntity) {
        clientEntity.setName(clientDTO.getName());
        clientEntity.setCpf(clientDTO.getCpf());
        clientEntity.setIncome(clientDTO.getIncome());
        clientEntity.setBirthDate(clientDTO.getBirthDate());
        clientEntity.setChildren(clientDTO.getChildren());
    }
}
