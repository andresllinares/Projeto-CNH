package com.fiap.juarez.cnh;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class CadastroUsuarioSteps {

    private String nome;
    private String email;
    private String mensagem;

    @Given("que o usuário preencheu todos os campos obrigatórios")
    public void usuarioPreencheuCampos() {
        nome = "Andres Mello Llinares";
        email = "andres@example.com";
    }

    @When("o usuário clica no botão {string}")
    public void usuarioClicaBotao(String botao) {
        // Aqui você simula a ação de clicar no botão e executar a lógica de cadastro.
        if (botao.equals("Cadastrar")) {
            if (nome != null && !nome.isEmpty() && email != null && !email.isEmpty()) {
                mensagem = "Cadastro realizado com sucesso";
            } else {
                mensagem = "O campo Nome é obrigatório"; // Simulação de erro
            }
        }
    }

    @Then("o sistema deve mostrar uma mensagem {string}")
    public void sistemaDeveMostrarMensagem(String mensagemEsperada) {
        assertEquals(mensagemEsperada, mensagem);
    }

    @Given("que o usuário não preencheu o campo {string}")
    public void usuarioNaoPreencheuCampo(String campo) {
        if (campo.equals("Nome")) {
            nome = null; // Simula que o campo nome não foi preenchido
        } else if (campo.equals("E-mail")) {
            email = null; // Simula que o campo email não foi preenchido
        }
    }

    @Given("que o usuário preencheu o campo {string} com um valor inválido")
    public void usuarioPreencheuCampoInvalido(String campo) {
        if (campo.equals("E-mail")) {
            email = "invalido"; // Simula um e-mail inválido
        }
    }
}
