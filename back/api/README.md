## BoardCamp

O BoardCamp é uma API construída em Java que permite gerenciar o aluguel de gameboards.

## Deploy URL

O deploy da aplicação foi realizado no [Render](https://boardcamp-api-2uls.onrender.com).

## Tecnologias utilizadas

* Java + Spring Boot:  Para a criação e gestão do servidor RESTful, oferecendo funcionalidades como injeção de dependências, configurações de banco de dados e mais;

* PostgreSQL:  Banco de dados relacional para armazenamento (persistência) e manipulação dos dados;

* Spring Data JPA (Java Persistence API):  Interface de acesso ao banco de dados, responsável por mapear as entidades dos modelos e as tabelas do banco de dados, simplificando a interação com o banco;

* Hibernate (ORM):  Framework para acesso ao banco de dados, responsável por mapear as classes do modelo de domínio para as tabelas do banco de dados;

## Camadas  da Aplicação

* Controllers: Camada responsável por receber as requisições HTTP e delegar o processamento dos parâmetros para os serviços apropriados;

* Services: Camada responsável pela implementação das regras de negócio e interação com os repositórios;

* Models: Camada que representa as entidades do banco de dados. Contém classes que mapeiam diretamente as tabelas para o banco de dados;

* DTOs (Data Transfer Objects): Camada que permite  a troca de informações entre as camadas, sem a necessidade de converter tipos;

* Repositories:  Camada responsável por acessar o banco de dados, realizando as operações de CRUD (Create, Read, Update e Delete);

* Errors:  Camada responsável por lidar com exceções e erros da aplicação, centralizando o tratamento de exceções, melhorando a consistência das respostas geradas nos casos de erro;

## Entidades

A aplicação possui as seguintes entidades:

### Games

Representa um jogo da biblioteca de jogos:
``` java
{
  id: 1,
  name: 'Banco Imobiliário',
  image: 'http://...',
  stockTotal: 3,
  pricePerDay: 1500
}
```

### Customers

Representa um cliente da loja, registrado ao alugar um jogo:
```java
{
  id: 1,
  name: 'João Alfredo',
  cpf: '01234567890'
}
```

### Rentals

Representa  uma locação de um jogo. Criada quando um cliente aluga um jogo:

```java
{
  id: 1,
  customerId: 1,
  gameId: 1,
  rentDate: '2021-06-20',    // data em que o aluguel foi feito, formato LocalDate
  daysRented: 3,             // por quantos dias o cliente agendou o aluguel
  returnDate: null,          // data que o cliente devolveu o jogo (null enquanto não devolvido)
  originalPrice: 4500,       // preço total do aluguel em centavos
  delayFee: 0                // multa total paga por atraso (dias que passaram do prazo vezes o preço por dia do jogo)
}
```



## Rotas e métodos
A API possui as seguintes rotas:

### Games
* **GET** '/games': Retorna uma lista com todos os dados dos jogos.

**Input**:  Nenhum.

**Output**: Lista de jogos encontrados, com o status ``200 (OK)``, no seguinte formato:
```java
[
  {
    id: 1,
    name: 'Banco Imobiliário',
    image: 'http://',
    stockTotal: 3,
    pricePerDay: 1500
  },
  {
    id: 2,
    name: 'Detetive',
    image: 'http://',
    stockTotal: 1,
    pricePerDay: 2500
  },
]
```
* **POST** '/games':  Adiciona um novo jogo na base de dados.

**Input**: Um body no formato
```java
{
  name: 'Banco Imobiliário',
  image: 'http://www.imagem.com.br/banco_imobiliario.jpg',
  stockTotal: 3,
  pricePerDay: 1500
}
```
**Output**: Status ``201 (Created)`` retornando o jogo registrado e seu ID.
```java
{
	id: 1,
  name: 'Banco Imobiliário',
  image: 'http://www.imagem.com.br/banco_imobiliario.jpg',
  stockTotal: 3,
  pricePerDay: 1500
}
```

#### Possíveis Erros:
--> Status ``409 (Conflict)``: O nome para o jogo cadastrado está indisponível;

--> Status ``400 (Bad Request)``: Um ou mais campos obrigatórios não foram preenchidos ou estão inválidos.

## Customers
* **GET** '/customers/{id}'  : Retorna informações do cliente com o ID fornecido.

Input: Deve ser fornecido um id de usuário como parâmetro de rota.

Output:  Status ``200 (OK)``, retornando as informações do cliente.
```java
{
  id: 1,
  name: 'João Alfredo',
  cpf: '01234567890'
}
```
#### Possíveis Erros:
--> Status ``404 (Not Found)``: Não foi encontrado um usuário para o ID fornecido;

*  **POST** '/customers': Cadastra um novo cliente.

**Input:** um body no formato:
```java
{
  name: 'João Alfredo',
  cpf: '01234567890'
}
```

**Output:** Status ``201 (Created)``, retornando o cliente retornado com o seu respectivo ID.
```java
{
	id: 1,
  name: 'João Alfredo',
  cpf: '01234567890'
}
```
#### Possíveis Erros:
--> Status ``409 (Conflict)``: O CPF fornecido já consta como cadastrado no banco de dados;
--> Status ``400 (Bad Request)``: Algum dos dados fornecidos não está no formato esperado, ou campo obrigatório não foi recebido;

## Rentals
* **GET** '/rentals': Lista todos os aluguéis registrados, com dados referentes ao cliente que realizou o aluguel, bem como o jogo alugado.

**Input:** Nenhum.
**Output:** Status ``200 (OK)`` e a lista de aluguéis
```java
[
  {
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: null, // troca pra uma data quando já devolvido
    originalPrice: 4500,
    delayFee: 0, // troca por outro valor caso tenha devolvido com atraso
    customer: {
      id: 1,
      name: 'João Alfredo',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
		  name: 'Banco Imobiliário',
		  image: 'http://www.imagem.com.br/banco.jpg',
		  stockTotal: 3,
		  pricePerDay: 1500
    }
  }
]
```

* **POST** '/rentals': Cadastra o aluguél de um usuário.

**Input:** Body no seguinte formato:
```java
{
  customerId: 1,
  gameId: 1,
  daysRented: 3
}
``` 

**Output:** Retorna o status ``201 (Created)`` com o aluguel criado, dados do customer e do jogo.

```java
{
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: null, 
    originalPrice: 4500,
    delayFee: 0, 
    customer: {
      id: 1,
      name: 'João Alfredo',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
		  name: 'Banco Imobiliário',
		  image: 'http://www.imagem.com.br/banco.jpg',
		  stockTotal: 3,
		  pricePerDay: 1500
    }
  }
```
#### Possíveis Erros:
--> Status ``404 (Not Found)``: Não foi encontrado um jogo ou usuário cadastrado para o ID fornecido;

--> Status ``400 (Bad Request)``: Algum dos dados fornecidos não está no formato esperado, ou um campo obrigatório não foi recebido;

--> Status ``422 (Unprocessable Entity)``: O jogo selecionado não apresenta estoque suficiente para realizar um aluguel;

* **PUT** '/rentals/{id}/return': Registra o encerramento de um aluguel (devolução do jogo pelo cliente)

**Input:** O ID referente à um aluguel que esteja em aberto.

**Output:** Retorna o status ``200 (OK)`` e os dados completos referente ao aluguel
```java
{
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: '2021-06-25', 
    originalPrice: 4500,
    delayFee: 3000, 
    customer: {
      id: 1,
      name: 'João Alfredo',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
		  name: 'Banco Imobiliário',
		  image: 'http://www.imagem.com.br/banco.jpg',
		  stockTotal: 3,
		  pricePerDay: 1500
    }
  }
```

#### Possíveis Erros:
--> Status ``404 (Not Found)``: Não foi encontrado um aluguel para o ID fornecido;

--> Status ``422 (Unprocessable Entity)``: Não foi encontrado um aluguel **em aberto** para o ID fornecido;













## Documentação de referência
Para maior referências, por favor considere as seguintes seções:
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/#build-image)



