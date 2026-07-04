# Serviço de Envio de E-mail via Gmail

Este projeto é uma aplicação Java desenvolvida com **Spring Boot**, criada para o envio de e-mails utilizando o serviço SMTP do **Gmail**. Utiliza o `spring-boot-starter-mail` para o envio das mensagens e o Thymeleaf para renderização de templates de e-mail em HTML.

---

## 📑 Sumário

- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades](#️-funcionalidades)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração do Envio de E-mail](#️-configuração-do-envio-de-e-mail)
- [Como Rodar o Projeto](#️-como-rodar-o-projeto)
- [Endpoints da API](#-endpoints-da-api)
- [Autor](#-autor)

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.9
- Spring Boot Starter Mail
- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Lombok
- Maven

---

## ⚙️ Funcionalidades

- Cadastro de usuário via endpoint REST
- Envio automático de e-mail de confirmação com conteúdo inline (imagem embutida no corpo do e-mail)
- Templates de e-mail em HTML usando Thymeleaf
- Configuração simples via `application.properties`

---

## ✅ Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

- [JDK 17](https://adoptium.net/) ou superior
- Maven 3.8+ (opcional, o projeto já inclui o wrapper `mvnw`)
- Uma conta do Gmail com **senha de app** gerada (necessária para autenticação SMTP)
- Uma IDE de sua preferência (IntelliJ, Eclipse, VS Code, etc.)

---

## ✉️ Configuração do Envio de E-mail

Para que o envio funcione, é necessário gerar uma **senha de app** na sua conta do Google (não use a senha normal da conta):

1. Acesse [https://myaccount.google.com/apppasswords](https://myaccount.google.com/apppasswords)
2. Gere uma senha de app específica para "E-mail"
3. Configure as credenciais no arquivo `src/main/resources/application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seuemail@gmail.com
spring.mail.password=SUA_SENHA_DE_APP
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> **Atenção:** nunca suba suas credenciais reais para o repositório. Utilize variáveis de ambiente ou um arquivo `application-local.properties` ignorado pelo Git.

---

## ▶️ Como Rodar o Projeto

1. Clone o repositório:
```bash
   git clone https://github.com/gu1gor/EnvioEmailGmail.git
   cd EnvioEmailGmail
```

2. Configure as credenciais de e-mail conforme a seção anterior.

3. Rode o projeto usando o Maven Wrapper:
```bash
   # Linux/Mac
   ./mvnw spring-boot:run

   # Windows
   mvnw.cmd spring-boot:run
```

   Ou execute diretamente a classe principal da aplicação pela sua IDE.

4. A aplicação estará disponível em:
http://localhost:8080

---

## 🔌 Endpoints da API

| Método | Endpoint    | Descrição                                              |
|--------|-------------|----------------------------------------------------------|
| POST   | `/register` | Registra um novo usuário e envia um e-mail de confirmação (com conteúdo inline) |

Exemplo de requisição com `curl`:
```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"nome": "João da Silva", "email": "joao@email.com"}'
```

Exemplo de resposta (201 Created):
```json
{
  "message": "User created successfully"
}
```

> Os campos do JSON de entrada dependem dos atributos reais da classe `User` (model).

---

## 👤 Autor

Desenvolvido por **Gustavo Igor**

[![GitHub](https://img.shields.io/badge/GitHub-gu1gor-181717?style=flat&logo=github)](https://github.com/gu1gor)
