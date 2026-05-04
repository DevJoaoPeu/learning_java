# Learning Java — CRUD de Produtos

Projeto de aprendizado em Java com um CRUD de produtos via API REST.

## Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Docker (banco de dados)

## Estrutura

```
src/
└── main/
    └── java/app/
        ├── Main.java                        # Ponto de entrada Spring Boot
        ├── controller/ProductController.java
        ├── model/Product.java
        ├── repository/ProductRepository.java
        └── service/ProductService.java
```

## Funcionalidades

- Listar todos os produtos
- Buscar produto por ID
- Cadastrar novo produto
- Atualizar produto
- Remover produto por ID

## Como rodar

Requer Docker para o banco de dados:

```bash
docker compose up -d   # sobe o PostgreSQL
mvn spring-boot:run    # compila e executa
```

Configure o arquivo `.env` na raiz com as variáveis de ambiente (o IntelliJ carrega automaticamente via run configuration):

```
POSTGRES_URL=jdbc:postgresql://localhost:5432/learning_java_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password
```

## Próximos passos

- [ ] Validação com `@Valid` nas entidades
- [ ] Tratamento de erros global com `@ControllerAdvice`
- [ ] Migrations com Flyway
- [ ] Testes com `@SpringBootTest` e `MockMvc`
- [ ] Spring Security
