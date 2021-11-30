# Projeto E-Diaristas

Projeot desenvolvido durante a imersão Multi Stack da [TreinaWeb](http://treinaweb.com.br), utilizando Java e Spring Boot.

## Dependências do Projeto

- Spring Boot
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- Bean Validation

## Dependências de Desenvolvimento

- Spring Boot devtools
- Lombok

## Requisitos

- Java 17
- Maven 3.8

## Como testar esse projeto na minha máquina?

Clone este repositório e entre na pasta do projeto.

```sh
git clone https://github.com/helitonns/backend-ediarista-java.git
```

Atalize as configurações de acesso ao banco de dados no arquivo [application.properties](src/main/resources/application.properties)

```properties
spring.datasource.url=jdbc:postgresql://host:porta/seu-banco-de-dados
spring.datasource.username=usuario
spring.datasource.password=senha
```

Execute o projeto através da Maven.

```sh
mvn spring-boot:run
```

Acesse a aplicação em [http://localhost:8080/admin/servicos](http://localhost:8080/admin/servicos).
