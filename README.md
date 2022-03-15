# R4 - TRANSACTIONS

<br/>
<p align="center">
Implementação BACKEND de uma API RESTFULL, para registro e acesso a transações financeiras.
</p>


:man_technologist: [**@rafaelrodrigues**](https://github.com/rafaelrodrigues) 

## Dependências
* Java 11 LTS
* Spring Boot [versão 2.6.4]
* h2 DataBase 

## Build

``` 
mvnw clean package 

```

Executado o comando acima, os artefatos gerados estarão disponíveis na pasta ` target ` do projeto.

### Execução

```
docker build -t r4-transaction .

docker run -d -p 8080:8080 --name r4-transaction-docker r4-transaction

```


### Referências

- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html)
- [Spring Quickstart Guide](https://spring.io/quickstart)
- [Spring Boot](https://spring.io/projects/spring-boot)


## :ballot_box_with_check: Endpoints ##

**[POST]**    `/accounts`

**[GET]**     `/accounts/:id` 

**[POST]**    `/transactions`