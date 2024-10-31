Feature: Consulta de Usuário

  Scenario: Consultar um usuário existente
    Given que existe um usuário com email "emaildoidooo2024@gmail.com" e senha "1234"
    When eu consulto o usuário pelo email "emaildoidooo2024@gmail.com"
    Then o usuário deve ter o nome "Nome do Usuário"
