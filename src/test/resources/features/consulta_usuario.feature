Feature: Consulta de Usuário

  Scenario: Consultar um usuário existente
    Given que existe um usuário com email "usuario@example.com" e senha "senha123"
    When eu consulto o usuário pelo email "usuario@example.com"
    Then o usuário deve ter o nome "Nome do Usuário" e a senha "senha123"
