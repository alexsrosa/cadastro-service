


# Projeto Cadastro e Login

[![Build Status](https://travis-ci.com/alexsrosa/cadastro-service.svg?branch=master)](https://travis-ci.com/alexsrosa/cadastro-service)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=alexsrosa%3Acadastro-service&metric=alert_status)](https://sonarcloud.io/dashboard?id=alexsrosa%3Acadastro-service)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=alexsrosa%3Acadastro-service&metric=coverage)](https://sonarcloud.io/component_measures?id=alexsrosa%3Acadastro-service&metric=coverage)



Aplicação que expõe uma API RESTful de criação de usuários e login.

Todos os endpoints aceitam somente JSON, inclusive ao responder mensagens de erro.

Todas as mensagens de erro tem o formato:

```json
    {"mensagem": "mensagem de erro"}
```

## Cadastro

* Esse endpoint deverá recebe um usuário com os campos "name", "email", "password", mais uma lista de objetos "phones", 
seguindo o formato abaixo:

```json
    {
        "name": "João da Silva",
        "email": "joao@silva.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "987654321",
                "ddd": "21"
            }
        ]
    }
```

* Respodem o código de status HTTP apropriado;

* Em caso de cadastro com sucesso, retorna o usuário, mais os campos:

    * `id`: id do usuário no formato UUID
    * `created`: data da criação do usuário
    * `modified`: data da última atualização do usuário
    * `last_login`: data do último login (no caso da criação, será a mesma que a criação)
    * `token`: token de acesso da API com JWT Token

* Caso o e-mail já exista, retorna erro com a mensagem "E-mail já existente".
* O token é persistido junto com o usuário

## Login

* Este endpoint recebe um objeto com e-mail e senha.
* Caso o e-mail e a senha correspondam a um usuário existente, retornar igual ao endpoint de Criação.
* Caso o e-mail não exista, retornar erro com status apropriado mais a mensagem "Usuário e/ou senha inválidos"
* Caso o e-mail exista mas a senha não bata, retornar o status apropriado 401 mais a mensagem "Usuário e/ou senha inválidos"

## Perfil do Usuário
* Caso o token não exista, retornar erro com status apropriado com a mensagem "Não autorizado".
* Caso o token exista, buscar o usuário pelo `id` passado no path e comparar se o token no modelo é igual ao token passado no header.
* Caso não seja o mesmo token, retornar erro com status apropriado e mensagem "Não autorizado"
* Caso seja o mesmo token, verificar se o último login foi a MENOS que 30 minutos atrás. Caso não seja a MENOS que 30 minutos atrás, retornar erro com status apropriado com mensagem "Sessão inválida".
* Caso tudo esteja ok, retornar o usuário no mesmo formato do retorno do Login.

## Detalhes técnicos
* Banco de dados em memória, como H2.

    <pre>http://localhost:8080/h2-console
    JDBC URL: jdbc:h2:mem:cadastrodb</pre>
    
* Processo de build via Gradle.
* Persistência com Hibernate.
* Framework Spring.
* API rodando no host - Heroku
* Servidor Tomcat (Spring Boot)
* Java 8
* JWT como token
* Testes unitários
* Criptogafia não reversível (hash) na senha

## Outras implementações

* Swagger
<pre>http://localhost:8080/v2/api-docs
http://localhost:8080/swagger-ui.html</pre>

## Endpoints

* Cadastro de novos usuários:
    
`http://localhost:8080/api/cadastro`

Payload:
```json
  {
         "name": "João da Silva",
         "email": "joao@silva.org",
         "password": "hunter2",
         "phones": [
             {
                 "number": "987654321",
                 "ddd": "21"
             }
         ]
     }
```
* Busca do perfil do usuário:

http://localhost:8080/api/cadastro/:id

* Efetuar login no sistema:

http://localhost:8080/api/login

Payload:
```json
{
        "email": "joao@silva.org",
        "password": "hunter2"
}
```
