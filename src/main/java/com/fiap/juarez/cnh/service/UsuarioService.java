package com.fiap.juarez.cnh.service;

import com.fiap.juarez.cnh.dto.UsuarioCadastroDTO;
import com.fiap.juarez.cnh.dto.UsuarioExibicaoDTO;
import com.fiap.juarez.cnh.exception.UsuarioNaoEncontradoException;
import com.fiap.juarez.cnh.model.Usuario;
import com.fiap.juarez.cnh.model.UsuarioRole;
import com.fiap.juarez.cnh.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);

        usuario.setSenha(senhaCriptografada);
        if (usuarioDTO.nome() == null) {
            usuario.setNome("Sem nome");
        } else {
            usuario.setNome(usuarioDTO.nome());
        }

        if (usuarioDTO.role() == null) {
            usuario.setRole(UsuarioRole.USER);
        } else {
            usuario.setRole(UsuarioRole.valueOf(usuarioDTO.role()));
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    public List<UsuarioExibicaoDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioExibicaoDTO::new).toList();
    }

    public UsuarioExibicaoDTO buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não existe no banco de dados");
        }
    }

    public void excluirUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getUsuarioId());
        if (usuarioOptional.isPresent()) {
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

}
