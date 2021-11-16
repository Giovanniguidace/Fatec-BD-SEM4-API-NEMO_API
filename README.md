

# Projeto Integrador 4º Semestre

## :orange_book: <b>Desafio do PI 4º Semestre:</b>


![image](https://user-images.githubusercontent.com/62898187/142062293-f462d9ce-3088-4753-9c9f-2638d8ace0e4.png) 


A necessidade do cliente para esta API é a criação de um sistema escalável e rápido para o envio de currículos já filtrados de forma inteligente e de acordo com parâmetros que sejam passados por outras aplicações. Não é necessária a criação de uma interface de contato com usuário, apenas será trabalhado com um back-end, onde receberá dados de parâmetros de filtros de pessoas e retornará os currículos filtrados e no formato JSON. Deverá ser criada uma API para que as aplicações dos clientes possam realizar as requisições.

## <b>:dart: Objetivo da Aplicação Nemo API</b>
O projeto Nemo visa ser uma solução simples, versátil, escalável e open source para pessoas e empresas que precisam de um sistema escalável, simples e versátil para fazer a gestão dos currículos de candidatos relacionando eles às vagas disponíveis pela empresa.

Sem possuir uma interface(Front-End) o NEMO API possui a responsabilidade de receber dados, realizar a inserção em banco e retornar dados solicitados com os parâmetros no formato JSON.

Com o intuito de gastar um menor tempo na seleção de currículos, a aplicação do cliente envia parâmetros de filtro para a NEMO API, onde a mesma tem a finalidade de retornar currículos de acordo com o parâmetro passado.

Seria esta a arquitetura do projeto:

![image](https://user-images.githubusercontent.com/62898187/142062827-03df10bb-ae53-4bd3-b04c-b5d17217046e.png)

Este é o Design da aplicação:

![image](https://user-images.githubusercontent.com/62898187/142063180-ad769686-80cf-4d34-99e7-d3ab35a419f4.png)

O Diagrama de Banco de Dados do projeto:

![diagrama do banco de dados](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/d4f43a9bca078511acc3a0f18e82192c/WhatsApp_Image_2021-04-18_at_23.14.01.jpeg)

## <b>⚙️ Tecnologias Adotadas na Solução:</b>

![](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/130e4d8da8a8daa4d9876b41e2568552/Design_sem_nome.png)

* Java - Motivo: De acordo com a votação realizada em grupo, foi realizada a escolha da linguagem Java;

* Spring Boot - Motivo: De acordo com a escolha da linguagem Java, que foi escolhida em grupo, foi escolhida também o framework Spring Boot para a criação da API e filtragem dos resultados;

* PostgreSQL - Motivo: Foi escolhido através de votação o banco de dados postgreSQL;

* Gitlab - Motivo: De acordo com votação em grupo, foi escolhido o repositório de código o Gitlab, pois os projetos anteriores já estavam sendo armazenados lá e o processo de CI/CD já estava mais avançado com este repositório;

As tecnologias foram escolhidas para complementar os estudos das matérias deste semestre, pois a linguagem utilizada pelos professores era Java utilizando Spring Boot.

## <b> :wrench: Contribuições individuais/pessoais</b>

* Auxilio na criação de métodos GET, POST e DELETE para o envio e recebimento de dados vindo do Front-End para candidatos:

	*	Exemplos:
```java
1 	//EXEMPLO DE GET PARA CANDIDATES
2 	@GetMapping(value = "nemo/v1/candidate", produces = "application/json")
3	public ResponseEntity<List<Candidate>> getCandidate(
4 						 @RequestParam(required = false) String gender,
5 						 @RequestParam(required = false) String country,
6 						 @RequestParam(required = false) String city,
7 						 @RequestParam(required = false) String zipCode,
8 						 @RequestParam(required = false) String skill,
9 						 @RequestParam(required = false) Double longitude,
10						 @RequestParam(required = false) Double latitude,
11						 @RequestParam(required = false) Double kilometers,
12						 @RequestParam(required = false) String availablePeriod,
13						 @RequestParam(required = false) String course,
14						 @RequestParam(required = false) String institution,
15						 @RequestParam(required = false) String workModality,
16						 @RequestParam(required = false) Double pretensionSalary,
17						 @RequestParam(required = false) String desiredJourney,
18						 @RequestParam(required = false) String companyName,
19						 @RequestParam(required = false) String postName
20	) {
21		 return Optional
22			 .ofNullable(findCandidateUseCase.findCandidate(gender, country, city, zipCode, 
23			 skill, longitude, latitude, kilometers, availablePeriod, course, institution, 
24			  workModality, pretensionSalary, desiredJourney, companyName, postName))
25			 .map(candidate -> ResponseEntity.ok().body(candidate))
26			 .orElseGet(() -> ResponseEntity.notFound().build());
27	}
 
```
```java
1  //EXEMPLO PARA POST CANDIDATE
2  @PostMapping("nemo/v1/candidate/")
3 public ResponseEntity<Candidate> criarCandidate(@RequestBody CandidateRequest candidate) {
4 	 try {
5 		 return ResponseEntity.ok().body(createCandidateUseCase.createCandidate(candidate));
6 	 } catch (Exception e) {
7 		 e.printStackTrace();
8 		 return ResponseEntity.badRequest().build();
9 	 }
10 }
```
```java
1  //EXEMPLO PARA DELETE CANDIDATE
2  @DeleteMapping("nemo/v1/candidate/{id}")
3 public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id) {
4 		 try {
5 			 deleteCandidateUseCase.deleteCandidateById(id);
6 			 return ResponseEntity.ok("Candidato deletado com sucesso.");
7 		 } catch (Exception e) {
8 			 e.printStackTrace();
9 			 return ResponseEntity.badRequest().build();
10		 }
11 }
```
Dificuldade: Para atingir a conclusão desta implementação, foi bem desafiador, pois não havia conhecimento necessário para trabalhar com Spring Boot e como era feito para realizar as requisições por API. Até então, tinha conhecimento apenas para trabalhar com Python/Django, então tive que me adaptar a uma nova linguagem e em como ela trabalha com o Spring Boot. 

* Selects nos arquivos de Repository:

```java
1  //EXEMPLO DE SELECT CRIADO NO REPOSITORY DE CANDIDATOS PARA COLETAR INFORMAÇÕES DAS TABELAS 
2  //EXPERIENCES, MODALITY, PRETENCION SALARY, COMPANY NAME, JOURNEY;
3 @Query("SELECT DISTINCT c FROM Candidate c " +
4 			 "INNER JOIN FETCH c.experiences e " +
5 			 "WHERE " +
6 			 "(:gender is null or c.gender = :gender) and " +
7 			 "(:country is null or c.country = :country) and " +
8 			 "(:city is null or c.city = :city) and " +
9 			 "(:zip_code is null or c.zipCode = :zip_code) and " +
10			 "(:availablePeriod is null or c.availablePeriod.name = :availablePeriod) and " +
11			 "(:workModality is null or c.workModality.name = :workModality) and " +
12			 "(:desiredJourney is null or c.desiredJourney.name = :desiredJourney) and " +
13			 "(:pretensionSalary is null or c.pretensionSalary <= :pretensionSalary) and " +
14			 "(:companyName is null or e.company.name = :companyName) and " +
15			 "(:postName is null or e.post.name = :postName)"
16 )
17 List<Candidate> findCandidateByAnyParams(
18			 @Param("gender") String gender,
19			 @Param("country") String country,
20			 @Param("city") String city,
21			 @Param("zip_code") String zipCode,
22			 @Param("availablePeriod") String availablePeriod,
23			 @Param("workModality") String workModality,
24			 @Param("pretensionSalary") Double pretensionSalary,
25			 @Param("desiredJourney") String desiredJourney,
26			 @Param("companyName") String companyName,
27			 @Param("postName") String postName
28 );
```

```java
1  //EXEMPLO DE SELECT REALIZANDO UM JOIN NA TABELA DE SKILLS PARA TRAZER AS SKILLS DO CANDIDATO SELECIONADO
2
3 Query("SELECT c FROM Candidate c " +
4 		 "INNER JOIN FETCH c.skills s " +
5 		 "INNER JOIN FETCH s.skill sk " +
6 		 "WHERE " +
7 		 "(id in :ids) and " +
8 		 "(sk.description = :skill) "
9 	 )
10 List<Candidate> findCandidateBySkillAndId(
11		 @Param("ids") List<Long> ids,
12		 @Param("skill") String skill
13 );

```

* Criação de tabelas no arquivo DDL.SQL para a criação do banco de dados utilizando Liquibase:
	
```sql		
1  /* Table 'skill' */
2  CREATE TABLE "skill" (
3 skill_id serial,
4  description varchar(20) NOT NULL UNIQUE,
5  PRIMARY KEY(skill_id));
6  
7  
8  /* Enum 'skill_level' */
9  CREATE TYPE skill_level
10 AS ENUM('ONE','TWO','THREE','FOUR','FIVE');
11 
12 /* Enum 'candidate_skill' */
13 CREATE TABLE "candidate_skill" (
14 fk_can_id serial,
15 fk_skill_id serial,
16 skill_level skill_level NOT null,
17 PRIMARY KEY(fk_can_id, fk_skill_id),
18 CONSTRAINT fk_can_id_cs FOREIGN KEY(fk_can_id) REFERENCES "candidate"(can_id),
19 CONSTRAINT fk_skill_id_cs FOREIGN KEY(fk_skill_id) REFERENCES "skill"(skill_id)
20 );
21 
22 /* Table 'company' */
23 CREATE TABLE "company" (
24 company_id serial NOT NULL,
25 com_name varchar(30) UNIQUE NOT NULL,
26 PRIMARY KEY(company_id));
27 
28 /* Table 'post' */
29 CREATE TABLE "post" (
30 post_id serial NOT NULL,
31 post_name varchar(30) UNIQUE NOT NULL,
32 PRIMARY KEY(post_id));
33 
34 /* Table 'candidate_exp' */
35 CREATE TABLE "candidate_exp" (
36 fk_can_id serial NOT NULL,
37 fk_company_id serial NOT NULL,
38 fk_post_id serial NOT NULL,
39 dt_start date NOT NULL,
40 dt_end date,
41 description text NOT NULL,
42 PRIMARY KEY(fk_can_id,fk_company_id,fk_post_id),
43 CONSTRAINT fk_can_id_ce FOREIGN KEY(fk_can_id) REFERENCES "candidate"(can_id),
44 CONSTRAINT fk_company_id FOREIGN KEY(fk_company_id) REFERENCES "company"(company_id),
45 CONSTRAINT fk_post_id FOREIGN KEY(fk_post_id) REFERENCES "post"(post_id));
46 
47 
48 /* Table 'institution' */
49 CREATE TABLE "institution" (
50 inst_id serial NOT NULL,
51 inst_name varchar(30) UNIQUE NOT NULL,
52 PRIMARY KEY(inst_id));
53 
54 /* Table 'course' */
55 CREATE TABLE "course" (
56 course_id serial NOT NULL,
57 course_name varchar(30) UNIQUE NOT NULL,
58 PRIMARY KEY(course_id));
59 
60 /* Table 'candidate_formation' */
61 CREATE TABLE "candidate_formation" (
62 fk_can_id serial NOT NULL,
63 fk_inst_id serial NOT NULL,
64 fk_course_id serial NOT NULL,
65 dt_start date NOT NULL,
66 dt_end date NOT NULL,
67 PRIMARY KEY(fk_can_id,fk_inst_id,fk_course_id),
68 CONSTRAINT fk_can_id_cf FOREIGN KEY(fk_can_id) REFERENCES "candidate"(can_id),
69 CONSTRAINT fk_inst_id FOREIGN KEY(fk_inst_id) REFERENCES "institution"(inst_id),
70 CONSTRAINT fk_course_id FOREIGN KEY(fk_co

```

* Auxilio na idealização do projeto:

	*	Ao participar das reuniões com o cliente, tivemos uma reunião de grupo, onde definimos estratégias de tarefas e cronogramas de entrega. Com isso, participei junto à equipe destas decisões, focando no plano de negócio do cliente e qual a necessidade do mesmo.

![Backlog do Produto](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/d95019d0b68b3054838f0a3c45a4ca3b/Backlog_do_Produto.jpg)

* Criação de servidor na AWS;

	*	Para que fosse possível realizar a hospedagem em nuvem, contratamos um servidor gratuito em nuvem(AWS), e hospedamos nossa aplicação. Realizei a instalação do PostgreSQL, Firewall no Ubuntu com iptables, configurei acesso SSH, e realizei a liberação de portas 80(HTTP) e 443(HTTPS).

Utilizamos o NGINX como proxy reverso, onde foi apontado para o serviço nemo.service, tornando possível o acesso à API através da Nuvem(Internet).

![](https://gitlab.com/felipemessibraga/pi-1sem-2021/-/wikis/uploads/0ace901e33fb1baf95eba56665a19b9a/Design_sem_nome.gif)

Devido à custos que foram cobrados para permanecer com o servidor hospedado na AWS, o grupo decidiu hospedar a aplicação no Heroku, que é um provedor gratuito.

Com isso, o CI/CD foi transferido para o Heroku, conforme imagens abaixo:

Foi configurado o CI/CD da aplicação, onde foi criado um Pipeline de Build; Test; e Deploy; Ao executar o Deploy, já atualiza automaticamente o Deploy no servidor alocado no Heroku

![image](https://user-images.githubusercontent.com/62898187/139724835-478095ba-2e90-4bc5-9846-2d4fad402d38.png)

![image](https://user-images.githubusercontent.com/62898187/139725292-bf394383-5fdb-4471-8e18-119e57562e66.png)

Arquivo .gitlab-ci.yml:

```yml
1  image: docker:latest
2  services:
3 	 - docker:dind
4  variables:
5  	 DOCKER_DRIVER: overlay
6  	 SPRING_PROFILES_ACTIVE: gitlab-ci
7  	 REPOSITORY: pi-1sem-2021
8  	 NAME_PROJECT: nemo
9  	 REGISTRY: registry.gitlab.com/pi-1sem-2021
10 	 MAVEN_CLI_OPTS: "--batch-mode  --errors  --fail-at-end"
11 	 MAVEN_OPTS: "-Dhttps.protocols=TLSv1.2  -Dmaven.repo.local=.m2/pi-1sem-2021"
12 	 DOCKER_TLS_CERTDIR: ""
13 before_script:
14 	 - echo "-- Iniciando Comandos CI/CD"
15 	 - apt-get update -qy
16 	 - apt-get install -y ruby-dev
17 	 - gem install dpl
18 stages:
19 	 - build
20 	 - test
21 	 - deploy 
22 maven-build:
23 	 image: maven:3-jdk-11
24 	 stage: build
25 	 script: "mvn  -f  pom.xml  compile"
26 	 artifacts:
27 	 name: $NAME_PROJECT
28 	 paths:
29 	 - teste_nemo.jar
30 maven-testes:
31 	 image: maven:3-jdk-11
32 	 stage: test 
33 	 before_script:
34 	 - echo "Iniciando Testes da Aplicação"
35 	 script: 
36 	 - 'mvn  -f  pom.xml  $MAVEN_CLI_OPTS  install'
37 	 
38 production:
39 	 stage: deploy
40 	 image: maven:3-jdk-11
41 	 script:
42 	 - dpl --provider=heroku --app=$HEROKU_APP_PRODUCTION --api-key=$HEROKU_API_KEY
```

## <b>🧠 Aprendizados Efetivos</b>

* Aprendizado na utilização de framework Spring Boot para criação de projetos Java:

	*	Este aprendizado foi um dos mais difíceis neste projeto, pois é uma linguagem diferente da que estava habitualmente trabalhando e tive que buscar cursos na Web, até comprar uns, para que eu pudesse ter como base para a implementação. Muitas vezes solicitei ajuda de professores e que também me ajudaram demais. As aulas da Fatec também foram muito úteis para estas implementações com Java e Spring Boot.

* Aprendizado na criação de tabelas de banco de dados utilizando o Liquibase:

	*	Em nosso projeto, utilizamos o Liquibase para tratar a inserção de dados "Mockados" no banco de dados PostgreSQL de uma forma otimizada. Foi um grande desafio trabalhar com esta ferramenta, mas foi muito útil, pois eu posso utilizá-la para outros projetos e em linguagens distintas

* Aprendizado para subir servidor com comando do Maven e arquivo pom.xml, Nginx e serviços do Linux:

	*	O aprendizado obtido com essa tarefa foi subir o serviço do Maven através do arquivo pom.xml utilizando os services do Linux e criando um socket para esta conexão, configurando também para iniciar automaticamente após a reinicialização do sistema operacional;
	*	Também foi adquirido conhecimento na utilização do Nginx, apontando para este serviço criado do Maven e direcionando o IP ou DNS público para que fosse direcionado diretamente nesta porta. Desta forma, foi configurado também que fosse utilizado o HTTPS para que a conexão fosse segura através de certificado gratuito do Let's Encrypt;
	*	Foi realizada a liberação das portas 80 e 443 no firewall do Linux utilizando o iptables e adquirindo assim, conhecimento no firewall do Linux;

* Aprendizado em testes e deploys utilizando CI/CD:

	*	Mesmo trabalhando na área de Infraestrutura de Redes e Servidores a quase 10 anos, eu nunca havia feito nenhuma pipeline de testes de CI/CD. Para mim foi maravilhoso atuar e adquirir conhecimento, pois é o futuro da área de Infra e a partir daí me permitiu que pudesse trabalhar com isso no meu emprego atual. Entendi o que seria o arquivo .yml e como era feito estes testes e build, no qual é utilizada uma máquina virtual que é criada temporariamente em nuvem para a execução destes testes e deploy;
