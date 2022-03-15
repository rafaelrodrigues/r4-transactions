# R4 - Transactions

<p align="center">
  <a target="_blank" href="https://www.oracle.com/java/technologies/javase-jdk11-doc-downloads.html">
  	 	<img alt="Spring Boot" src="https://img.shields.io/static/v1?color=green&label=Java&message=11&?style=for-the-badge&logo=Java">
  </a>
  <a target="_blank" href="https://spring.io/projects/spring-boot">
      <img alt="Spring Boot" src="https://img.shields.io/static/v1?color=green&label=Spring Boot&message=2.6.4&?style=for-the-badge&logo=spring-boot">
  </a>
  <a target="_blank" href="https://spring.io/projects/spring-boot">
      <img alt="Spring Boot" src="https://img.shields.io/static/v1?color=green&label=Docker&message=Build&?style=for-the-badge&logo=Docker">
  </a>
</p>

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

## Execução

Executar os comandos abaixo no diretório raiz do projeto, para construir a imagem docker, e executar o container na porta 8080 com a imagem criada para o projeto.

```
docker build -t r4-transaction .

docker run -d -p 8080:8080 --name r4-transaction-docker r4-transaction

```


## Referências

- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html)
- [Spring Quickstart Guide](https://spring.io/quickstart)
- [Spring Boot](https://spring.io/projects/spring-boot)


## :ballot_box_with_check: Endpoints ##

**[POST]**    `/accounts`

**[GET]**     `/accounts/:id` 

**[POST]**    `/transactions`