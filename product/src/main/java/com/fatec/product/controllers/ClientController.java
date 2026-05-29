package com.fatec.product.controllers;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.product.entities.Client;
import com.fatec.product.services.ClientService;
import com.fatec.product.dtos.ClientRequest;
import com.fatec.product.dtos.ClientResponse;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientRequest Client client) {
        Client c = service.save(client);

        URI location = ServletUriComponentsBuilder //LOCATION DO RECURSO CRIADO (OBRIGATÓRIO NO POST)
            .fromCurrentRequest()
            .path("/{id}") //adiciona o ID no final do path
            .buildAndExpand(c.getId()) //pega o ID do cliente criado
            .toUri(); //converte para URI
        
        return ResponseEntity.created(location).body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ClientRequest client, @PathVariable Long id) {
        service.update(client, id);
        return ResponseEntity.noContent().build();
    }
    
}
