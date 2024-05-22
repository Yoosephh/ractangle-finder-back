## Rectangle Finder

O Rectangle Finder é uma API que, dada uma matriz MxN composta por "0"s e "1"s, retorna a maior área encontrada para retângulos compostos apenas por "1"s.

## Deploy URL

O deploy da aplicação foi realizado no [Render](https://rectangle-finder-back.onrender.com).

## Tecnologias utilizadas

* Java (V.17) + Spring Boot:  Para a criação e gestão do servidor RESTful, oferecendo funcionalidades como injeção de dependências, configurações de banco de dados e mais;


## Lógica do código
O código utiliza 3 arrays para mapear, procurar por um retângulo e calcular sua área:
```java
  int[] left = new int[rowLength]; // Inicializado como left [0, 0... até rowLength]
  int[] right = new int[rowLength]; // Inicialmente [0, 0...] mas logo é populada para [rowLength, rowLength, ....]
  int[] height = new int[rowLength]; // Inicializado como right [0, 0... até rowLength]
```
Estes arrays irão armazenar, para cada linha da matriz, as possíveis posições que marcam o início (left), fim (right) e a altura (height) de um retângulo.

O código então realiza um loop pela matriz recebida ``java for (String[] row : matrix)`` e inicializa as variáveis ``java int currLeft = 0;    int currRight = rowLength;``

Estas variáveis serão fundamentais para auxiliar a mapear a posição que inicia o maior retângulo (currLeft) e a posição que termina o maior retângulo (currRight).
A variável currLeft é inicializada como 0 pois supõe-se, inicialmente, que o maior retângulo possível começa na primeira posição, enquanto que currRight inicializa com o valor da última posição possível, pois supõe-se, inicalmente, que o maior retângulo possível termina na última posição.

Para cada coluna da linha, o código verifica se a célula contém o valor "1". Se positivo, atualiza os arrays *left* e *right* para considerar a coluna atual como parte do retângulo, e incrementa a altura em *height*. Caso contrário, o retângulo atual é finalizado e as variáveis *currLeft* e *currRight* são atualizadas para a próxima coluna.

Após mapear cada linha, o algorítmo calcula as possíveis áreas de retângulos encontrados pelos arrays *left, right e height* e armazena este valor na variável *maxArea*. Através de comparações com o Math.max, é possível avaliar, para cada linha, se um novo valor encontrado é maior que o maior valor encontrado até então.

## Camadas  da Aplicação

* Controllers: Camada responsável por receber as requisições HTTP e delegar o processamento dos parâmetros para os serviços apropriados;

## Entidades

A API não possui integração com um banco de dados, por isso, não possui entidades.


## Rotas e métodos
A API possui a seguinte rota:

### Games
* **POST** '/rectangle': Retorna a área para o maior retângulo formado dentro da matriz.

**Input**:  Uma matriz de qualquer tamanho, no formato MxN.
Ex: 
```java
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
--> Status ``422 (Unprocessable Entity)`` e a mensagem ``Please, provide a matrix with only '0' and/or '1' chars``: A matriz fornecida apresenta algum caracter que é diferente de 0 e/ou 1. É preciso que apenas 0 ou 1 estejam presentes na matriz;

--> Status ``422 (Unprocessable Entity)`` e a mensagem ``Please, provide a matrix in the MxN format. Check if the lenghts for all the rows are equal.``: A matriz fornecida apresenta uma ou mais linhas de diferentes tamanhos. Certifique-se de que todas as linhas possuam o mesmo tamanho;

--> Status ``422 (Unprocessable Entity)`` e a mensagem ``Please, provide a matrix.``: A matriz fornecida está vazia;













## Documentação de referência
Para maior referências, por favor considere as seguintes seções:
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/#build-image)



