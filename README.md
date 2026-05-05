# Dashboard TI/DevOps (Spring Boot + PostgreSQL)

Aplicação web com autenticação (cadastro e login) e dashboard com gráficos de CPU, Memória e Rede.

## Requisitos

Instale na máquina:

- Java 21
- Maven 3.9+
- PostgreSQL 14+

## Configuração do PostgreSQL

1. Crie o banco:

```sql
CREATE DATABASE devops_dashboard;
```

2. Crie um usuário (opcional):

```sql
CREATE USER devops WITH PASSWORD 'devops123';
GRANT ALL PRIVILEGES ON DATABASE devops_dashboard TO devops;
```

## Configuração da aplicação

A aplicação usa estas variáveis de ambiente (com valores padrão):

- `DB_URL` (default: `jdbc:postgresql://localhost:5432/devops_dashboard`)
- `DB_USERNAME` (default: `devops`)
- `DB_PASSWORD` (default: `devops123`)

## Build

```bash
mvn clean package
```

## Execução

```bash
mvn spring-boot:run
```

Depois acesse:

- Cadastro: `http://localhost:8080/register`
- Login: `http://localhost:8080/login`
- Dashboard: `http://localhost:8080/dashboard`

## Como averiguar se está funcionando

1. Abra `/register` e crie uma conta com e-mail e senha (mínimo 8 caracteres).
2. Faça login em `/login`.
3. Após autenticar, confirme que os gráficos no dashboard começam a atualizar a cada 5 segundos.
4. Verifique no PostgreSQL se o usuário foi salvo na tabela `users`.

Exemplo de verificação no banco:

```sql
SELECT id, email FROM users;
```
