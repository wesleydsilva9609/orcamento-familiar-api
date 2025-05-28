# 📊 Orçamento Familiar API
Este projeto foi desenvolvido durante o curso da Alura, com o objetivo de criar uma API REST para controle de receitas e despesas de um orçamento familiar, utilizando Spring Boot e MySQL, além de implementar autenticação via JWT.

## ✅ Tecnologias usadas
* Java 17
* Spring Boot
* Postgres
* Spring Security
* JWT (JSON Web Token)
* Maven

## 🚀 Funcionalidades implementadas
### 🔹 Primeira semana
* CRUD de Receitas e Despesas

Cadastro, listagem, atualização e remoção de receitas e despesas.
* Integração com banco de dados Postgres.
* Validações básicas para não permitir registros duplicados no mesmo mês.
### 🔹 Segunda semana
* Resumo e Buscas Personalizadas

Filtro de receitas e despesas por descrição.
Paginação nas listagens.
Geração de resumo mensal com:
* Total de receitas.
* Total de despesas.
* Saldo final.
* Total gasto por categoria.
### 🔹 Terceira semana
* Autenticação com JWT

Cadastro e login de usuários.
* Proteção das rotas da API para permitir acesso somente com token válido.
* Implementação de segurança usando Spring Security.
* deploy no render 

## 📌 Aprendizados
Boas práticas com Spring Boot e arquitetura em camadas.
Implementação de autenticação com JWT.
Paginação, validação e tratamento de erros.
Criação de consultas personalizadas no JPA.
