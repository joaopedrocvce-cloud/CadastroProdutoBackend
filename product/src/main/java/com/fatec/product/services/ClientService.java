package com.fatec.product.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.product.entities.Client;
import com.fatec.product.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repoC;

    public List<Client> findAll() {
        return repoC.findAll();
    }

    public Client findById(Long id) {
        return repoC.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Client save(Client client)
    {
        return repoC.save(client);
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

    public void update(Client client, Long id)
    {
        Client c = repoC.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        c.setName(client.getName());
        c.setEmail(client.getEmail());
        
        repoC.save(c);
    }
}
