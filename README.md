# Título
   Desafio Save food

# Descrição
   *Este projeto tem como finalidade registrar usuário<br> 
    gravar informações no banco H2 e cache no Redis.


# Tecnologoligias utilizadas para o desenvolvimento
   Java 11<br> 
   springframework.boot 2.5.2<br> 
   org.springframework.kafka<br> 
   org.projectlombok  1.18.20<br> 
   modelmapper<br> 
   springfox-swagger2<br> 
   springfox-swagger-ui<br> 
   gson<br> 
   junit<br> 
   mockito-core<br> 
   Maven<br> 
   
# Pre-requisitos
   JDK 11 + <br> 
   Maven 2.0 + <br>
   Docker 5 +
   Docker Composer
   Redis
   H2 data base embarcado 
   Browser Google chrome ou firefox.<br> 
   OS Linux ou Windows 7 + (Retirar o comando "sudo" na execução)

# Detalhes do sistema
   Contempla no sistema a inclusão de inforçmaão simples do usuário e sua idade, através do swagger ou Postam, payload incluido da documentação.
   A informação é salva em um banco H2 embarcado, que salva também em servidor de cache Redis por 10 segundos.
   O build da aplicação é composto por um Dockerfile e 2 arquivos docker compose.
   

# Documentação da aplicação(SWAGGER)
  * [swagger da aplicação](openapi_neo-user.json)<br> 
  * Para ler o swagger da aplicação digite no navegador o endereço https://editor.swagger.io<br> 
  e copie o conteudo do link acima, para o campo de inclusão no site.<br> 


# Rodar a aplicação
  * Na raiz do projeto:<br> 
    Build da aplicação via docker (Dockerfile)  <br> 
  * sudo docker build -t neo.user .  <br> 
    Deploy da aplicação via docker.   <br> 
  * sudo docker compose -f docker-app-compose.yml up  <br> 
    Baixara aimagem e Build do redis.
  * sudo docker compose -f docker-redis-compose.yml up <br> 



# Para acessar a aplicação e verificar a documentação(SWAGGER)
  * Apos rodar a aplicação digite:<br> 
   http://localhost:8080/swagger-ui.html<br> 

# Teste via CURL

  curl --location --request POST 'localhost:8080/api/v1/save/redis/user' \
--header 'Content-Type: application/json' \
--data-raw '{
	"name": "test8",
    "age":30
}'


# Considerações
  * O sistema tem apenas uma api para o envio de informações de registro de usuário.
# Contato
  herbeteneo@gmail.com 
  
# Links
   https://dev.java/community/ <br>
   https://spring.io/quickstart <br> 
   https://editor.swagger.io/ <br> 
   https://hub.docker.com/_/redis <br>
   https://www.docker.com/ <br>
   https://redis.io/ <br>
   https://www.h2database.com/html/main.html <br>



