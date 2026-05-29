package com.fatec.product.dtos;

public record ClientResponse(

    Long id,
    String name,
    String email,
    String cpf,
    String telefone

) {

}