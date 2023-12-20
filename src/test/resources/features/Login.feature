#language:pt
Funcionalidade: Realizar login no sistema Saucedemo
  Para validar a autenticação do usuário no sistema

  @test
  Cenário: Realizar login no sistema com usuário válido
    Dado possa acessar a tela de login do sistema
    Quando informar um "standard_user" válido
    E inserir uma "secret_sauce" válida
    E clicar no botão login
    Então o sistema deve exibir os produtos