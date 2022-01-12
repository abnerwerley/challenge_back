# Challenge Back-end | FireDev

Olá, esse é um desafio da FIreDev que realizei.

# Como fiz:
- Criei gerei o zip do projeto no https://start.spring.io/ com as seguintes dependências:
  *  Spring Web já que seria uma aplicação web
  *  Spring Boot DevTools - para recarregar a aplicação de forma rápida
  *  Spring Boot JPA - para trabalhar com a persistência de dados
  *  Validation - Para trabalhar com condições para os atributos da model
  *  MySQL Driver - Para me conectar com o MySQL
  
 - Configurei o applicatoin.properties pra se comunicar com o meu MySQL, e defini a porta em 8085, mas depois mudei pra 8084 (em outras postas estava dando erro)
 - Fiz a model de usuário com os atributos de id, nome, email, senha e user name
 - Configurei o repositório pra fazer busca por esses atributos (menos por senha)
 - Fiz um CRUD simples pra testar no Postman
 - Inseri dados de um usuário pro MySQL pra fazer os testes com os GET's
 - Fiz os testes e fiz as modificações necessárias.
 - Adicionei a dependência springFox pra usar o Swagger
 - Configurei  o Swagger no SwaggerConfig
 - Testei os endpoints novamente pelo Swagger
 - Adicionei a dependência do PostgreeSQl pra usar o Postgree do Heroku
 - Adicionei uma nova aplicação e a nomeei
 - Adicionei o Resource do Postgree no Heroku
 - Selecionei a Branch "heroku" desse repositório
 - Cliquei pra fazer o deploy pela branch, mas deu um erro. Pesquisei no stackoverflow e parece que é um erro na versão do java.

# Como fazer o código rodar:

- Requisitos:
 - Ter uma IDE para rodar o projeto.
 - Ter o postman ou insomnia baixado

-Passos
 - Fazer o git clone do url do repositório pro seu workspace (Ou baixar o zip dele).
 - Importar o projeto Maven com o endereço do local onde está.
 - Selecionar o Projeto na Sua IDE e Rodar como Java Application
 - Entrar no http://localhost:8084/swagger-ui/ pelo seu navegador
 - Testar os endpoints e passar o parâmetro/preencher o body quando necessário.
