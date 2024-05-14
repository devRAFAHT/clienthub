package com.andradscorporation.backend.services;

import com.andradscorporation.backend.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientDTO> findAllPaged(Pageable pageable);
    ClientDTO findById(Long id);
    ClientDTO insert(ClientDTO clientDTO);
    ClientDTO update(Long id, ClientDTO clientDTO);
    void delete(Long id);
}
