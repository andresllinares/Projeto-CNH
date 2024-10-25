package com.fiap.juarez.cnh.dto;

import com.fiap.juarez.cnh.model.Carro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CarroCadastroDTO(
        Long carroId,

        @NotBlank(message = "A Marca do carro é obrigatória")
        String marca,

        @NotBlank(message = "O Modelo do carro é obrigatório")
        String modelo,

        @NotBlank(message = "A Placa do carro é obrigatória")
        @Size(min = 8, max = 8, message = "A Placa do carro deve ter 8 caracteres")
        String placa
) {
}