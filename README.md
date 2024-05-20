## BoardCamp

O BoardCamp é uma API construída em Java que permite gerenciar o aluguel de gameboards.

## Deploy URL

O deploy da aplicação foi realizado no [Render](https://rectangle-finder-back.onrender.com).

## Tecnologias utilizadas

* Java + Spring Boot:  Para a criação e gestão do servidor RESTful, oferecendo funcionalidades como injeção de dependências, configurações de banco de dados e mais;

* PostgreSQL:  Banco de dados relacional para armazenamento (persistência) e manipulação dos dados;

* Spring Data JPA (Java Persistence API):  Interface de acesso ao banco de dados, responsável por mapear as entidades dos modelos e as tabelas do banco de dados, simplificando a interação com o banco;

* Hibernate (ORM):  Framework para acesso ao banco de dados, responsável por mapear as classes do modelo de domínio para as tabelas do banco de dados;

## Camadas  da Aplicação

* Controllers: Camada responsável por receber as requisições HTTP e delegar o processamento dos parâmetros para os serviços apropriados;

## Entidades

A API não possui integração com um banco de dados, por isso, não possui entidades.


## Rotas e métodos
A API possui a seguinte rota:

### Games
* **POST** '/rectangle': Retorna a área para o maior retângulo formado dentro da matriz.

**Input**:  Uma matriz de qualquer tamanho, no formato MxN.
Ex: ```java
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
```

**Output**: Um Integer, que representa a área para o maior retângulo presente na matriz. Para o caso do exemplo, o retorno será 6.
```


#### Possíveis Erros:
--> Status ``422 (Unprocessable Entity) e a mensagem `Please, provide a matrix with only '0' and/or '1' chars```: A matriz fornecida apresenta algum caracter que é diferente de 0 e/ou 1. É preciso que apenas 0 ou 1 estejam presentes na matriz;

--> Status ``422 (Unprocessable Entity) e a mensagem `Please, provide a matrix in the MxN format. Check if the lenghts for all the rows are equal.```: A matriz fornecida apresenta uma ou mais linhas de diferentes tamanhos. Certifique-se de que todas as linhas possuam o mesmo tamanho;

--> Status ``422 (Unprocessable Entity) e a mensagem `Please, provide a matrix.```: A matriz fornecida está vazia;













## Documentação de referência
Para maior referências, por favor considere as seguintes seções:
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/#build-image)



