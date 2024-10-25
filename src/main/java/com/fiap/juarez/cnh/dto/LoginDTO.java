package com.fiap.juarez.cnh.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "O Email do usuário é obrigatório")
        @Email(message = "O Email do usuário é inválido")
        String email,

        @NotBlank(message = "A Senha do usuário é obrigatória")
        String senha
) {

}
