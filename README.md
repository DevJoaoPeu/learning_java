# Learning Java — CRUD de Produtos

Projeto de aprendizado em Java com um CRUD simples de produtos via terminal.

## Estrutura

```
src/
├── Main.java               # Menu interativo no terminal
├── model/Product.java      # Entidade produto
├── repository/             # Acesso aos dados (lista em memória)
└── service/                # Regras de negócio e validações
```

## Funcionalidades

- Listar todos os produtos
- Buscar produto por ID
- Remover produto por ID
- Atualizar produto
- Cadastrar novo produto

## Próximos passos

- [ ] Integrar banco de dados usando Hibernate
- [ ] Migrar para Spring Boot

## Como rodar

Compile e execute pelo IntelliJ ou via terminal:

```bash
javac -d out src/**/*.java src/Main.java
java -cp out Main
```
