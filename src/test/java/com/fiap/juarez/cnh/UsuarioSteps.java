package com.fiap.juarez.cnh;

import com.fiap.juarez.cnh.model.Usuario;
import com.fiap.juarez.cnh.model.UsuarioRole;
import com.fiap.juarez.cnh.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioSteps {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuarioConsultado;

    @Given("que existe um usuário com email {string} e senha {string}")
    public void que_existe_um_usuario_com_email_e_senha(String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome do Usuário");
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setRole(UsuarioRole.USER);
        usuarioRepository.save(usuario);
    }

    @When("eu consulto o usuário pelo email {string}")
    public void eu_consulto_o_usuario_pelo_email(String email) {
        usuarioConsultado = (Usuario) usuarioRepository.findByEmail(email);
    }

    @Then("o usuário deve ter o nome {string} e a senha {string}")
    public void o_usuario_deve_ter_o_nome_e_a_senha(String nomeEsperado, String senhaEsperada) {
        assertEquals(nomeEsperado, usuarioConsultado.getNome());
        assertEquals(senhaEsperada, usuarioConsultado.getPassword());
    }
}
