package com.fiap.juarez.cnh.controller;

import com.fiap.juarez.cnh.config.security.TokenService;
import com.fiap.juarez.cnh.dto.LoginDTO;
import com.fiap.juarez.cnh.dto.TokenDTO;
import com.fiap.juarez.cnh.dto.UsuarioCadastroDTO;
import com.fiap.juarez.cnh.dto.UsuarioExibicaoDTO;
import com.fiap.juarez.cnh.model.Usuario;
import com.fiap.juarez.cnh.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager auhtenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody @Valid LoginDTO usuarioDto) {
        System.out.println("Login");
        System.out.println(usuarioDto);
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(usuarioDto.email(), usuarioDto.senha());
        Authentication auth = auhtenticationManager.authenticate(usernamePassword);

        String token = tokenService.createToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDto) {
        UsuarioExibicaoDTO usuarioSalvo = null;
        usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDto);

        return ResponseEntity.ok(usuarioSalvo);
    }
}
