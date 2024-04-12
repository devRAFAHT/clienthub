package com.andradscorporation.backend.resources;

import com.andradscorporation.backend.entities.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){
        List<Client> clients = new ArrayList<>();
        Instant birthDate = Instant.now();
        clients.add(new Client(1L, "Maria Silva", "123654789", 3000.00, birthDate, 2));
        clients.add(new Client(2L, "João Barros", "741258963", 5000.00, birthDate, 0));
        return ResponseEntity.ok().body(clients);
    }

}
