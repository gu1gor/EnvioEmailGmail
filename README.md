# Envio de E-mail via Gmail

Projeto Java desenvolvido com **Spring Boot**, com o objetivo de demonstrar o envio de e-mails utilizando o serviço SMTP do **Gmail**. Ao registrar um usuário, a aplicação dispara automaticamente um e-mail (com conteúdo inline) para o endereço informado.

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
- Thymeleaf
- Lombok
- Maven

---

## ⚙️ Funcionalidades

- Cadastro de usuário via API REST
- Envio automático de e-mail com conteúdo inline (imagem/HTML embutido) ao registrar um usuário
- Configuração via `application.properties`

---

## ✅ Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

- [JDK 17](https://adoptium.net/) ou superior
- Maven 3.8+ (opcional, o projeto já inclui o wrapper `mvnw`)
- Uma conta do Gmail com **senha de app** gerada (necessária para autenticação SMTP)
- Uma IDE de sua preferência (IntelliJ, Eclipse, VS Code, etc.)

---

## ✉️ Configuração do Envio de E-mail

O Gmail não permite mais o uso da senha normal da conta para autenticação via SMTP em aplicações externas. Por isso, é necessário gerar uma **senha de app**:

1. Acesse [myaccount.google.com/security](https://myaccount.google.com/security)
2. Ative a **verificação em duas etapas** (caso ainda não esteja ativa)
3. Vá em **Senhas de app** e gere uma senha específica para o projeto
4. Configure o arquivo `src/main/resources/application.properties` com os dados abaixo:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=SEU_EMAIL@gmail.com
spring.mail.password=SUA_SENHA_DE_APP
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> ⚠️ Nunca suba suas credenciais reais para o repositório. O ideal é usar variáveis de ambiente ou um arquivo `application-local.properties` ignorado pelo Git.

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

4. A API estará disponível em:
http://localhost:8080

---

## 🔌 Endpoints da API

| Método | Endpoint     | Descrição                                                        |
|--------|--------------|-------------------------------------------------------------------|
| POST   | `/register`  | Cadastra um novo usuário e dispara um e-mail com conteúdo inline |

Exemplo de requisição com `curl`:
```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"name": "João da Silva", "email": "joao@email.com"}'
```

**Resposta esperada (201 Created):**
```json
{
  "message": "User created successfully"
}
```

> Os campos exatos do corpo da requisição (`name`, `email`, etc.) dependem da classe `User` (model). Ajuste o exemplo acima conforme os atributos reais dessa classe.

---

## 📜 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Autor

Desenvolvido por **Gustavo Igor**

[![GitHub](https://img.shields.io/badge/GitHub-gu1gor-181717?style=flat&logo=github)](https://github.com/gu1gor)
Só falta eu confirmar os campos exatos da classe User pra deixar o exemplo de JSON 100% certo — se quiser colar o conteúdo dela aqui, eu ajusto o exemplo de requisição.package com.demo.emailservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }
}
