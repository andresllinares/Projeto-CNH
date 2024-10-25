package com.fiap.juarez.cnh.dto;

import com.fiap.juarez.cnh.model.Usuario;

public record UsuarioExibicaoDTO(
        Long usuarioId,
        String email) {
    public UsuarioExibicaoDTO(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getEmail());
    }
}
