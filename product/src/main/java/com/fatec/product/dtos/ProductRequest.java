package com.fatec.product.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductRequest(
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 4, max = 40, message = "Nome deve ter entre 4 e 40 caracteres")
    String name,

    @NotBlank(message = "Descriçao não pode ser vazio")
    @Size(min = 5, max = 100, message = "Descrição deve ter entre 5 e 100 caracteres")
    String description,

    @Min(value=0, message = "Preço não poder menor que zero")
    Double price
    
) {

}
