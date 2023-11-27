# Projeto de Gerenciamento de Loja

Este é um projeto de exemplo que implementa um sistema de gerenciamento de uma loja usando Java e JPA (Java Persistence API). O projeto inclui classes para manipulação de entidades como produtos, clientes, pedidos e categorias, bem como testes de desempenho e consultas usando Criteria API.

## Estrutura do Projeto

### `model`

Este pacote contém as classes de modelo, como `Product`, `Client`, `Order`, `OrderItem`, `Category`, `PersonalData`, `Computing` e `Book`. Essas classes representam as entidades do sistema e são mapeadas para o banco de dados usando JPA.

### `dao`

Contém as classes responsáveis por realizar operações de acesso a dados, como `ProductDAO`, `ClientDAO`, `RequestDAO` e `CategoryDAO`. Essas classes utilizam o `EntityManager` para interagir com o banco de dados.

### `tests`

Contém classes de teste que demonstram o uso das classes DAO e a realização de consultas específicas, como `CriteriaTest`, `PerformanceQueries`, `ProductRegistration` e `RequestRegistration`.

### `util`

Fornece utilitários para interação com o JPA, como a classe `JPAUtil` que cria e fornece instâncias de `EntityManager`.

### `vo`

Contém a classe `SalesReportVo`, que é um objeto de valor utilizado para representar dados de relatórios de vendas.

```bash
└───main
    ├───java
    │   └───br
    │       └───com
    │           └───lasbr
    │               ├───dao
    │               ├───model
    │               ├───tests
    │               ├───util
    │               └───vo
    └───resources
        └───META-INF
```

### Docker Compose (docker-compose.yml):

Existe um arquivo docker-compose.yml, na raiz do projeto que define serviços para um banco de dados PostgreSQL (postgres) e PgAdmin (pgadmin).
Esses serviços estão na mesma rede chamada REDESTORE.
O banco de dados PostgreSQL é usado para armazenar dados da aplicação.

## Tecnologias Utilizadas

**Back-end:** Java, JPA Hibernate

**Banco de Dados:** PostgreSQL

**Tool Admin:** PGAdmin

**Conteiner** Docker, Docker-Compose





## Configurações

Para executar a aplicação, é necessário ter o Docker instalado.
O arquivo docker-compose.yml pode ser utilizado para iniciar containers do PostgreSQL e PgAdmin.
Certifique-se de que ambos os serviços (postgres e pgadmin) estejam na mesma rede (REDESTORE) para garantir a conectividade.

1. Postgres Service:

* image: Utiliza a imagem mais recente do PostgreSQL do Docker Hub.
* container_name: Define o nome do contêiner como store-persistence.
* environment: Configura variáveis de ambiente, como o nome do banco de dados, usuário e senha.
* ports: Mapeia a porta 5432 do host para a porta 5432 do contêiner, permitindo a conexão com o banco de dados.
* networks: Associa o contêiner à rede denominada REDESTORE.

2. PgAdmin Service:

* image: Utiliza a imagem do PgAdmin4 do Docker Hub.
* container_name: Define o nome do contêiner como store-pgadmin.
* environment: Configura variáveis de ambiente, como o e-mail e senha padrão do PgAdmin4.
* ports: Mapeia a porta 5050 do host para a porta 80 do contêiner, permitindo o acesso à interface web do PgAdmin4.
* networks: Associa o contêiner à rede denominada REDESTORE.
* depends_on: Garante que o contêiner PgAdmin4 seja iniciado somente após o contêiner PostgreSQL.

3. Networks:

* REDESTORE: Define uma rede personalizada chamada REDESTORE para garantir que os serviços PgAdmin4 e PostgreSQL possam se comunicar por meio dessa rede.

4. Como usar:

* Execute docker-compose up -d no diretório onde o docker-compose.yml está localizado.
* O Docker irá baixar as imagens e iniciar os contêineres.
* O PostgreSQL estará acessível na porta 5432, e o PgAdmin4 estará acessível na porta 5050 do host.
* Ao configurar o PgAdmin4, adicione um novo servidor usando o nome do serviço PostgreSQL (postgres), host postgres, e as credenciais definidas no arquivo docker-compose.yml.
* Certifique-se de que ambos os serviços estejam na mesma rede (REDESTORE), permitindo a comunicação direta entre eles.
* Este arquivo Docker Compose facilita a criação de ambientes de desenvolvimento, fornecendo um banco de dados PostgreSQL e uma interface web PgAdmin4 para gerenciamento do banco de dados. A rede compartilhada (REDESTORE) permite que esses serviços se comuniquem. Certifique-se de ajustar as configurações conforme necessário para atender aos requisitos do seu projeto.

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: store-persistence
    environment:
      POSTGRES_DB: store_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: lasBr01
    ports:
      - "5432:5432"
    networks:
      - REDESTORE

  pgadmin:
    image: dpage/pgadmin4
    container_name: store-pgadmin
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@example.com
      PGADMIN_DEFAULT_PASSWORD: admin
      PGADMIN_LISTEN_PORT: 80
    ports:
      - "5050:80"
    networks:
      - REDESTORE
    depends_on:
      - postgres

networks:
  REDESTORE:
```


## Melhorias

O projeto foi inicialmente startado com um banco de dados em memória, o H2. No entanto, foi substituído pelo PostgreSQL para proporcionar mais dinamismo e torná-lo mais próximo da realidade possível. Utilizou-se a ferramenta pgAdmin para manipular o banco de dados. Ambos, pgAdmin e PostgreSQL, foram configurados no docker-compose para aprimorar a experiência no desenvolvimento.

Atualmente, o projeto está em fase de implementação/refatoração, visando torná-lo ainda mais robusto e leve.


## Licença

[MIT](LICENSE)
