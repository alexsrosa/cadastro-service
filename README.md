# Projeto Cadastro e Login

## Implementations

<h4>- Swagger Documentation. Access by:</h4>
    <pre>http://localhost:8080/v2/api-docs</pre>
    <pre>http://localhost:8080/swagger-ui.html</pre>

<h4>- H2. Access By: </h4>
    <pre>http://localhost:8080/h2-console
    JDBC URL: jdbc:h2:mem:cadastrodb
    </pre>

## API
http://localhost:8080/api/cadastro

Payload:
<pre>
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
</pre>

http://localhost:8080/api/cadastro/b2588575-59e5-448c-a1d1-b9c90518fbda

http://localhost:8080/api/login

Payload:
<pre>
{
        "email": "joao@silva.org",
        "password": "hunter2"
}
</pre>