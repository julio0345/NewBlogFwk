<h3>#challengeFramework  \o/ </h3>

<br/>
<h4>Bora pra mais um desafio galera!</h4><br/>
Desafio:<br/>
Criar uma API Restful que nos permita cadastrar <b>Usuários</b>, fazermos <b>Login</b> dos mesmos com <b>Autenticação via JWT</b> (Jason Web Token) e ainda permitir que estes usuários criem alguns <b>Posts</b> (sem nenhuma restrição de conteúdo na qual possam colocar textos, imagens e links) ou <b>Álbuns de Fotos</b>. Estes Posts também podem receber <b>Comentários</b> de quaisquer outros usuários logados.<br/>
Algumas regrinhas:<br/>
• Os Posts só podem ser excluídos pelos seus respectivos criadores.<br/>
• Os Comentários só podem ser excluídos pelos seus respectivos criadores.<br/>
• Os Álbuns de Fotos só podem ser excluídos pelos seus respectivos criadores.<br/>
<br/>
Que comecem os jogos >) (aposto que lembrou do bonequinho dos Jogos Mortais  rsrs)<br/><br/>

Então galera, honestidade em primeiro lugar! Devido alguns compromissos pessoais, não tive um tempo hábil para aprender Angular (isso mesmo, aprender porque ainda não sei...rsrsr) e acabei conseguindo implementar apenas o backend.<br/>
Mas ficou bacana.. vale a pena continuar. #BoraLá

Neste repositório encontra-se o fonte do backend dessa API muito top. Parece meio complexo né, mas planejando uma boa arquitetura, tudo dá certo no final :D <br/> [O ministério da saúde adverte: Pegue seu café!] <br/>
Antes de te contar mais detalhes dessa API, vou descrever os recursos que foram utilizados e como proceder pra buildar com sucesso ;) <br/><br/>

<b>Tecnologias</b>
• Java 11 \o/ <br/>
• Spring Boot -> Neste font foi utilizado a versão 2.4.3<br/>
• Spring Security -> Um pedaço da segurança do Universo Spring<br/>
• JWT -> O segurança da festa. So entra de pulseirinha bem!<br/>
• Lombok -> Chega de boiler plates \o/ <br/>
• ModelMapper -> Pensa num cara inteligente... <br/>
• Swagger -> Deixa os docs com esse cara <br/>
• Hibernate (JPA) -> Nosso amigo do banco <br/>
• SpringData -> Nosso outro amigo do banco <br/>
• DevTools -> Hotdeploy everytime <br/>
• Maven -> Pensa num cara responsável<br/>
• Liquibase -> Versionador de scripts muito top(DML, DDL, DCL, DQL e outras coisas com L no final :D) <br/>
• GitHub -> Nosso repositório lindo<br/>
• STS4 -> É so nosso velho Eclipse maquiado<br/>
• Postman -> nosso testador de APIS pra quem nao gosta das cores do Swagger :D <br/><br/>

<h4>Banco de dados </h4>

Nesta API utilizamos o banco de dados relacional Postgres.<br/>
Para configurar o acesso no seu banco de dados, confira as informações no arquivo <b>application.properties</b> no diretorio:<br/>
.../blog-api/src/mains/resources<br/><br/>

Ok. Você já deve estar se perguntando porque eu não subi tudo isso no Docker né? pois é :( <br/>
Confesso que não o utilizei justamente devido a deadline, já que eu não tenho Docker instalado no momento, e pra ajudar ainda estou no Windows.<br/>
Ia gastar um tempinho bom pra deixar ele no jeito aki.. e nossa deadline de 2 dias não foi brincadeira, rsrs<br/>
Mas teremos mais oportunidades! <br/>
Então chega de falação e bora rodar a API<br/><br/>

<h4>Como executar o projeto </h4>

Contando que você já tenha o Java 11 instalado na sua máquina (se ainda não, baixe em : https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html),
pelo prompt ou Git Bash, clone o projeto do repositorio do Git com o comando abaixo:<br/>
```
git clone https://github.com/julio0345/BlogFramework.git
```

Após o download, importe o projeto na sua IDE de preferência (eu utilizei o STS4).<br/><br/>

Configure o acesso ao seu banco de dados pelo arquivo <b>application.properties</b><br/><br/>
 
Build o projeto e o nosso amigo  <b>Linquibase</b> se responsabilizará em criar toda a estrutura do banco de dados necessário para utilizar a API.<br/><br/>

Outra forma de utilizar a API, seria  pelo terminal:<br/>
Nele, acesse o diretorio do projeto e execute o comando <br/>
```
mvn clean package
```
e sem seguida acesse o diretório onde foi criado o .jar (target) e execute o comando<br/>
```
java -jar blog-api-0.0.1-SNAPSHOT.jar
```
Mais uma vez, não se esqueça de instalar o <b>Postgres</b>(link para download: https://www.postgresql.org/download/) e configurar o seu usuário e senha do banco no arquivo application.properties<br/><br/>

 <h4>Documentação </h4>

Deixei essa parte com o  <b>Swagger</b>. Para acessá-la, utilize a URL: http://localhost:8080/swagger-ui.html#/<br/>
Tá eu sei, eu poderia ter colocado algumas annotations para esclarecer mais, mas acredite, o tempo foi muuuito curto.<br/><br/>

 <h4>Consumindo a API </h4>

<h2>1º Passo - Cadastre um Usuário</h2>

No Swagger pelo link http://localhost:8080/swagger-ui.html#/ na seção <b>usuario Controller</b>
ou pelo Postman com a URI: localhost:8080/blog/usuarios
ambos no verbo <b>Post</b>, utilize o trecho do json abaixo para inserir um novo usuário<br/>
```
{
  "email": "billgates@gmail.com",
  "nome": "Bill",
  "senha": "123pudim"
}
```

<h2>2º Passo - Faça o Login com este usuário</h2>

No Swagger pelo link http://localhost:8080/swagger-ui.html#/ na seção <b>login Controller</b>
ou pelo Postman com a URI: localhost:8080/blog/login
ambos no verbo <b>Post</b>, utilize o trecho do json abaixo para fazer login<br/>
```
{
  "login": "billgates@gmail.com",
  "senha": "123pudim"
}
```
No caso de sucesso (Status 200) será retornado o Token do login em um json. Ex.:
```
{
    "token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIn0.npAHD0VmlILmakw3hFQFIUvifWRVwtDEUbLQKE7Q3AM"
}
```
<b>ATENÇÃO!!! >>>> será necessário informar essa Token no header das próximas requisições</b> para que retornem o Status 200 ou similar

<h2>3º Passo - Crie um Post</h2>

No Swagger pelo link http://localhost:8080/swagger-ui.html#/ na seção <b>post Controller</b>
ou pelo Postman com a URI: localhost:8080/blog/posts
ambos no verbo <b>Post</b>, utilize o trecho do json abaixo para criar o Post<br/>
```
{
  "texto": "Nova Fiat Toro. Bom ou Mal Investimento??"
}
```

<h2>4º Passo - Comente neste Post</h2>

No Swagger pelo link http://localhost:8080/swagger-ui.html#/ na seção <b>comentario Controller</b>
ou pelo Postman com a URI: localhost:8080/blog/comentarios
ambos no verbo <b>Post</b>, utilize o trecho do json abaixo para criar o Comentário no Post<br/>
```
{
  "post": {
    "id": 1
  },
  "texto": "Não compraria nunca kkkkk"
}
```

<h2>5º Passo - Crie um Álbum de Fotos</h2>

No Swagger pelo link http://localhost:8080/swagger-ui.html#/ na seção <b>album Controller</b>
ou pelo Postman com a URI: localhost:8080/blog/albuns
ambos no verbo <b>Post</b>, utilize o trecho do json abaixo para criar o Álbum

Exemplo Json<br/>
```
{
  "titulo": "Meu Aniversário 2021"
}
```


<h2>6º Passo - Faça um upload de uma Foto</h2>

No Postman com a URI: localhost:8080/blog/fotos/imagem<br/>
no verbo <b>Post</b>, após informar o token no Header da requisição<br/>
Key = "Authorization"<br/>
Value = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIn0.npAHD0VmlILmakw3hFQFIUvifWRVwtDEUbLQKE7Q3AM"<br/>
Selecione o tipo <b>Body</b><br/>
Selecione o tipo <b>form-data</b><br/>
Informe a Key = "imagem"<br/>
Selecione a imagem desejada no campo Value<br/>
Envie a requisição<br/><br/>

Como retorno de sucesso, espera-se o Status 201 e o json do caminho de onde a foto foi enviada.<br/><br/>

Então assim conseguimos simular as principais funções solicitadas pelo desafios.<br/>
Alguns detalhes como Permissoes para excluir, podemos simplesmente ter um método onde verificamos se o usuário logado é o mesmo do usuário da entidade (Post, Comentário ou Album). Se afirmativo, o front libera o botão "Excluir".<br/><br/>


<H3>Esclarecimentos Finais</H3>

Pois é, o desafio que foi me passado esperava a implementação de um front em Angular 8++, porém, não já explicado acima, não foi implementado.<br/>
Então vamos deixar com //TODO não tão distante (by Shurek). <br/> <br/>

Confesso que apanhei igual filho sem mãe em alguns momentos do Upload porque foi o primeiro que fiz com essa função junto com JWT \o/.<br/>

Então é isso ai pessoal :) <br/> <br/>

Vou deixar meu e-mail para contato caso tenham dúvidas e fiquem a vontade para críticas. São elas quem nos fazem crescer \o/  <br/>
Bjos no coração <br/>
#TheEnd <br/> <br/>

Júlio Guimarães <br/>
julio0345@gmail.com
