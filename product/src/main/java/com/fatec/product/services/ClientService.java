package com.fatec.product.services;

import java.util.stream.Collectors;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.product.entities.Client;
import com.fatec.product.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

import com.fatec.product.dtos.ClientRequest;
import com.fatec.product.dtos.ClientResponse;
import com.fatec.product.mappers.ClientMapper;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repoC;

    public List<ClientResponse> findAll() {
        return repoC.findAll().stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }

    public Client findById(Long id) {
        return repoC.findById(id).map(ClientMapper::toDTO).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public ClientResponse save(ClientRequest client)
    {
        Client c = repoC.save(ClientMapper.toEntity(client));
        return ClientMapper.toDTO(c);
    }

    public void deleteById(Long id)
    {
        if(repoC.existsById(id))
        {
            repoC.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
    }

    public void update(ClientRequest client, Long id)
    {
        Client c = repoC.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        c.setName(client.getName());
        c.setEmail(client.getEmail());
        
        repoC.save(c); 
    }
}
