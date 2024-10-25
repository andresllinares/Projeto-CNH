package com.fiap.juarez.cnh.controller;

import com.fiap.juarez.cnh.dto.UsuarioCadastroDTO;
import com.fiap.juarez.cnh.dto.UsuarioExibicaoDTO;
import com.fiap.juarez.cnh.model.Usuario;
import com.fiap.juarez.cnh.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody UsuarioCadastroDTO usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario);
    }
}
