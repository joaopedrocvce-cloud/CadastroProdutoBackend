package com.fatec.product.mappers;

import com.fatec.product.dtos.ClientResponse;
import com.fatec.product.dtos.ClientRequest;
import com.fatec.product.entities.Client;

public class ClientMapper {
    
    public static Client toEntity(ClientRequest request){
        Client c = new Client();
        c.setName(request.name());
        c.setEmail(request.email());
        
        return c;
    }
    
    public static ClientResponse toDTO(Client client){
        return new ClientResponse(
            client.getId(),
            client.getName(),
            client.getEmail(),
            client.getCPF(),
            client.getTelefone()
        );
    }
}
