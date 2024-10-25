package com.fiap.juarez.cnh.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
        Long usuarioId,

        String nome,
        @NotBlank(message = "O Email do usuário é obrigatório")
        @Email(message = "O Email do usuário é inválido")
        String email,
        @NotBlank(message = "A Senha do usuário é obrigatória")
        @Size(min = 6, message = "A Senha do usuário deve ter no mínimo 6 caracteres")
        String senha,
        String role
) {
}
