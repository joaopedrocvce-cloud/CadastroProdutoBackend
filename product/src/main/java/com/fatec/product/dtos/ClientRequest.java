package com.fatec.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientRequest(

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 4, max = 40, message = "Nome deve ter entre 4 e 40 caracteres")
    String name,

    @NotBlank(message = "Email não pode ser vazio")
    @Size(min = 5, max = 100, message = "Email deve ter entre 5 e 100 caracteres")
    String email,
) {

}