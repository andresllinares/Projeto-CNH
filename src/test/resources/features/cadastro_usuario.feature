Feature: Cadastro de Usuário

  Scenario: Cadastro com sucesso
    Given que o usuário preencheu todos os campos obrigatórios
    When o usuário clica no botão "Cadastrar"
    Then o sistema deve mostrar uma mensagem "Cadastro realizado com sucesso"

  Scenario: Cadastro sem nome
    Given que o usuário não preencheu o campo "Nome"
    When o usuário clica no botão "Cadastrar"
    Then o sistema deve mostrar uma mensagem de erro "O campo Nome é obrigatório"

  Scenario: Cadastro sem e-mail
    Given que o usuário não preencheu o campo "E-mail"
    When o usuário clica no botão "Cadastrar"
    Then o sistema deve mostrar uma mensagem de erro "O campo E-mail é obrigatório"

  Scenario: Cadastro com e-mail inválido
    Given que o usuário preencheu o campo "E-mail" com um valor inválido
    When o usuário clica no botão "Cadastrar"
    Then o sistema deve mostrar uma mensagem de erro "E-mail inválido"
