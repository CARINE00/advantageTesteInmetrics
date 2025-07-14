# language: pt

Funcionalidade: Checkout no site Advantage Online Shopping

  @login
  Cenario: Realizar checkout de um produto
  Quando pesquisa por "LAPTOPS"
  E adiciona o produto ao carrinho "HP Chromebook 14 G1(ENERGY STAR)"
  E acessa o carrinho
  E finaliza a compra
  Entao o sistema deve finalizar a compra com sucesso

  @login
  Cenário: Validar Carrinho vazio
    E  acessa o carrinho
    Então o sistema deve exibir uma mensagem informando que o carrinho está vazio

  @login
  Cenário: Exibir resumo do pedido antes da confirmação
    Quando pesquisa por "LAPTOPS"
    E adiciona o produto ao carrinho "HP Chromebook 14 G1(ENERGY STAR)"
    E acessa o carrinho
    Entao o sistema deve exibir o resumo do pedido com produto, quantidade e valor total


  Cenário: Solicitar login ao tentar finalizar compra sem autenticação
    Quando pesquisa por "LAPTOPS"
    E adiciona o produto ao carrinho "HP Chromebook 14 G1(ENERGY STAR)"
    E acessa o carrinho
    Então o sistema deve solicitar que o usuário realize login antes de continuar

  @login
  Cenário: Exibir erro ao deixar campos obrigatórios de pagamento em branco
    Quando pesquisa por "LAPTOPS"
    E adiciona o produto ao carrinho "HP Chromebook 14 G1(ENERGY STAR)"
    E acessa o carrinho
    E tenta finalizar a compra sem preencher os campos obrigatórios de pagamento
    Então o sistema deve exibir mensagens de erro nos campos obrigatórios

  @login
  Cenário: Exibir e permitir seleção do método de pagamento
    E pesquisa por "MICE"
    E adiciona o produto ao carrinho "HP Z3600 Wireless Mouse"
    E acessa o carrinho
    Quando acessa a etapa de pagamento
    Então o sistema deve exibir as opções disponíveis
    E permitir a seleção de um dos métodos


