<h1>Bycoders - ParserTransation</h1>

### Desafio programação - para vaga desenvolvedor. 💻
É uma aplicação que consiste em carregar e parsear um arquivo de texto(CNAB) e salvar suas informações(transações financeiras).
De seguida a aplicação lista todas as transações e filtra pelo nome da loja com o seu respectivo saldo resultante das transações.

## Campos do Modelo de Transacao: 📜

+ id
+ data
+ hora
+ valor
+ cpf
+ donoLoja
+ nomeLoja
+ cartao
+ tipo

## Tipos de Transações: 📊

+ Débito
+ Boleto
+ Financiamento
+ Crédito	Entrada
+ Recebimento Empréstimo
+ Vendas	Entrada
+ Recebimento TED
+ Recebimento DOC
+ Aluguel

## Naturezas de Transação: 📉

+ Entrada
+ Saida

## Tecnologias usadas: ⚒️️

<table>
    <tr>
        <td>Java</td>
        <td>Docker Composer</td>
        <td>PostgreSQL</td>
    </tr>
    <tr>
         <td>8</td>
         <td>1.25.0</td>
         <td>13</td>
    </tr>
</table>

# Acesso a aplicação:
```bash

#Clonar o projecto
    https://github.com/routerrouter/parserTransations

# Como rodar a aplicação:
   1) gerar o jar da aplicacao
      1- mvn package -Dmaven.test.skip=true
   2) iniciar o docker-compose
      1- docker-compose up --build

# Consumo da API
   Carregar e parsear o ficheiro de Transações.
   1) upload_process(POST)
      http://localhost:8080/transacao
        payload: {file: CNAB.txt} ficheiro carregado.
        
   Retorna a listagem completa de Transações de forma paginada.
   2) findAll
      http://localhost:8080/transacao
   
   Retorna uma lista paginada das Transações de uma dada loja e o seu saldo resultante das Transações.
   3) findByLoja
      http://localhost:8080/transacao/byLoja?nomeLoja=MERCADO DA AVENIDA