package com.fiap.juarez.cnh;

import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jackson.JsonLoader;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fiap.juarez.cnh.model.Usuario;
import com.fiap.juarez.cnh.model.UsuarioRole;
import com.fiap.juarez.cnh.repository.UsuarioRepository;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioSteps {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuarioConsultado;
    private String emailFake; // Variable to hold the fake email

    @Given("que existe um usuário com email {string} e senha {string}")
    public void que_existe_um_usuario_com_email_e_senha(String email, String senha) {
        // Remove existing user with the same email if it exists
        usuarioRepository.deleteByEmail(email);

        // Create a new user with the provided email and senha
        Usuario usuario = new Usuario();
        usuario.setNome("Nome do Usuário");
        usuario.setEmail(email);  // Use the email from the feature
        usuario.setSenha(senha);
        usuario.setRole(UsuarioRole.USER);
        usuarioRepository.save(usuario);

        // Store the email for cleanup later
        emailFake = email;
    }

    @When("eu consulto o usuário pelo email {string}")
    public void eu_consulto_o_usuario_pelo_email(String email) {
        UserDetails userDetails = usuarioRepository.findByEmail(email); // This line might need modification based on your repo
        if (userDetails instanceof Usuario) {
            usuarioConsultado = (Usuario) userDetails; // Casting if you are sure about the instance type
        } else {
            // Handle the case where the user is not of type Usuario
            throw new IllegalArgumentException("User not found or not of expected type");
        }
    }


    @Then("o usuário deve ter o nome {string} e a senha {string}")
    public void o_usuario_deve_ter_o_nome_e_a_senha(String nomeEsperado, String senhaEsperada) {
        assertEquals(nomeEsperado, usuarioConsultado.getNome());
        assertEquals(senhaEsperada, usuarioConsultado.getSenha());
    }

    @Then("o usuário deve ter o nome {string}")
    public void o_usuario_deve_ter_o_nome(String nomeEsperado) {
        // Check if the usuarioConsultado is not null
        if (usuarioConsultado == null) {
            throw new IllegalStateException("No user consulted. Please ensure a user was queried first.");
        }
        // Assert that the consulted user's name matches the expected name
        assertEquals(nomeEsperado, usuarioConsultado.getNome(), "The user's name does not match the expected name.");
    }




    @After // This method will run after each scenario
    public void tearDown() {
        // Clean up the user created for this test scenario
        if (emailFake != null) {
            usuarioRepository.deleteByEmail(emailFake); // Remove the fake user
        }
    }
}
