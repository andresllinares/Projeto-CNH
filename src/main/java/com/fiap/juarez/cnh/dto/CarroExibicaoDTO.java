package com.fiap.juarez.cnh.dto;

import com.fiap.juarez.cnh.model.Carro;

public record CarroExibicaoDTO(
        Long carroId,
        String marca,
        String modelo,
        String placa
) {
    public CarroExibicaoDTO(Carro carro) {
        this(
            carro.getCarroId(),
            carro.getMarca(),
            carro.getModelo(),
            carro.getPlaca());
    }
}