package com.fiap.juarez.cnh;

import com.fiap.juarez.cnh.model.Usuario;
import com.fiap.juarez.cnh.model.UsuarioRole;
import com.fiap.juarez.cnh.repository.UsuarioRepository;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Ensures each test runs in its own transaction
public class UsuarioSteps {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuarioConsultado;
    private String emailFake;

    @Given("que existe um usuário com email {string} e senha {string}")
    public void que_existe_um_usuario_com_email_e_senha(String email, String senha) {
        // Remove existing user with the same email if it exists
        usuarioRepository.deleteByEmail(email);

        // Create a new user
        Usuario usuario = new Usuario();
        usuario.setNome("Nome do Usuário");
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setRole(UsuarioRole.USER);
        usuarioRepository.save(usuario);
        emailFake = email;
    }

    @When("eu consulto o usuário pelo email {string}")
    public void eu_consulto_o_usuario_pelo_email(String email) {
        usuarioConsultado = (Usuario) usuarioRepository.findByEmail(email);
    }

//    @Then("o usuário deve ter o nome {string} e a senha {string}")
//    public void o_usuario_deve_ter_o_nome_e_a_senha(String nomeEsperado, String senhaEsperada) {
//        assertEquals(nomeEsperado, usuarioConsultado.getNome());
//        assertEquals(senhaEsperada, usuarioConsultado.getSenha());
//    }

    @Then("o usuário deve ter o nome {string}")
    public void oUsuárioDeveTerONome(String nomeEsperado) {
        assertEquals(nomeEsperado, usuarioConsultado.getNome());
    }

    @After // This method will run after each scenario
    public void tearDown() {
        if (emailFake != null) {
            usuarioRepository.deleteByEmail(emailFake); // Clean up the fake user
        }

    }


}