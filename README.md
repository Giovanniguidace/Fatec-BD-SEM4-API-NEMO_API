<h3>4º Semestre:</h3>

* <B>Projeto NEMO API - Grupo NEMO:</b>


![minipic](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/d3295be8d95140beb572615b52a02a87/Nemo_API__1_.png) 


<b>Objetivo do API:</b>

A necessidade do cliente para esta API é a criação de um sistema escalável e rápido para o envio de currículos já filtrados de forma inteligente e de acordo com parâmetros que sejam passados por outras aplicações. Não é necessária a criação de uma interface de contato com usuário, apenas será trabalhado com um back-end, onde receberá dados de parâmetros de filtros de pessoas e retornará os currículos filtrados e no formato JSON. Deverá ser criada uma API para que as aplicações dos clientes possam realizar as requisições.

<b>Objetivo do Projeto:</b>

O projeto Nemo visa ser uma solução simples, versátil, escalável e open source para pessoas e empresas que precisam de um sistema escalável, simples e versátil para fazer a gestão dos currículos de candidatos relacionando eles às vagas disponíveis pela empresa.

Sem possuir uma interface(Front-End) o NEMO API possui a responsabilidade de receber dados, realizar a inserção em banco e retornar dados solicitados com os parâmetros no formato JSON.

Com o intuito de gastar um menor tempo na seleção de currículos, a aplicação do cliente envia parâmetros de filtro para a NEMO API, onde a mesma tem a finalidade de retornar currículos de acordo com o parâmetro passado.

Seria esta a arquitetura do projeto:

![MVP_Architecture](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/da23ece3ab6a514cc11ec02a9a0adea7/MVP_Architecture.png)

Este é o Design da aplicação:

![MVP_System_Design](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/6cbd2856fb40b82e08d19ecca5133ab5/MVP_System_Design.png)

O Diagrama de Banco de Dados do projeto:

![diagrama do banco de dados](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/d4f43a9bca078511acc3a0f18e82192c/WhatsApp_Image_2021-04-18_at_23.14.01.jpeg)

<b>Tecnologias Adotadas na Solução:</b>

![](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/130e4d8da8a8daa4d9876b41e2568552/Design_sem_nome.png)

* Java - Motivo: De acordo com a votação realizada em grupo, foi realizada a escolha da linguagem Java;

* Spring Boot - Motivo: De acordo com a escolha da linguagem Java, que foi escolhida em grupo, foi escolhida também o framework Spring Boot para a criação da API e filtragem dos resultados;

* PostgreSQL - Motivo: Foi escolhido através de votação o banco de dados postgreSQL;

* Gitlab - Motivo: De acordo com votação em grupo, foi escolhido o repositório de código o Gitlab, pois os projetos anteriores já estavam sendo armazenados lá e o processo de CI/CD já estava mais avançado com este repositório;

As tecnologias foram escolhidas para complementar os estudos das matérias deste semestre, pois a linguagem utilizada pelos professores era Java utilizando Spring Boot.

<b>Contribuições individuais/pessoais</b>

* Auxilio na criação de métodos GET, POST e DELETE para o envio e recebimento de dados vindo do Front-End para candidatos:

	*	Exemplos:
```java
	//EXEMPLO DE GET PARA CANDIDATES
	@GetMapping(value = "nemo/v1/candidate", produces = "application/json")
 public ResponseEntity<List<Candidate>> getCandidate(
						 @RequestParam(required = false) String gender,
						 @RequestParam(required = false) String country,
						 @RequestParam(required = false) String city,
						 @RequestParam(required = false) String zipCode,
						 @RequestParam(required = false) String skill,
						 @RequestParam(required = false) Double longitude,
						 @RequestParam(required = false) Double latitude,
						 @RequestParam(required = false) Double kilometers,
						 @RequestParam(required = false) String availablePeriod,
						 @RequestParam(required = false) String course,
						 @RequestParam(required = false) String institution,
						 @RequestParam(required = false) String workModality,
						 @RequestParam(required = false) Double pretensionSalary,
						 @RequestParam(required = false) String desiredJourney,
						 @RequestParam(required = false) String companyName,
						 @RequestParam(required = false) String postName
 ) {
		 return Optional
			 .ofNullable(findCandidateUseCase.findCandidate(gender, country, city, zipCode, 
			 skill, longitude, latitude, kilometers, availablePeriod, course, institution, 
			  workModality, pretensionSalary, desiredJourney, companyName, postName))
			 .map(candidate -> ResponseEntity.ok().body(candidate))
			 .orElseGet(() -> ResponseEntity.notFound().build());
 }
 
```
```java
//EXEMPLO PARA POST CANDIDATE
@PostMapping("nemo/v1/candidate/")
 public ResponseEntity<Candidate> criarCandidate(@RequestBody CandidateRequest candidate) {
	 try {
		 return ResponseEntity.ok().body(createCandidateUseCase.createCandidate(candidate));
	 } catch (Exception e) {
		 e.printStackTrace();
		 return ResponseEntity.badRequest().build();
	 }
 }
```
```java
//EXEMPLO PARA DELETE CANDIDATE
@DeleteMapping("nemo/v1/candidate/{id}")
 public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id) {
		 try {
			 deleteCandidateUseCase.deleteCandidateById(id);
			 return ResponseEntity.ok("Candidato deletado com sucesso.");
		 } catch (Exception e) {
			 e.printStackTrace();
			 return ResponseEntity.badRequest().build();
		 }
 }
```
Dificuldade: Para atingir a conclusão desta implementação, foi bem desafiador, pois não havia conhecimento necessário para trabalhar com Spring Boot e como era feito para realizar as requisições por API. Até então, tinha conhecimento apenas para trabalhar com Python/Django, então tive que me adaptar a uma nova linguagem e em como ela trabalha com o Spring Boot. 

* Selects nos arquivos de Repository:

```java
//EXEMPLO DE SELECT CRIADO NO REPOSITORY DE CANDIDATOS PARA COLETAR INFORMAÇÕES DAS TABELAS 
//EXPERIENCES, MODALITY, PRETENCION SALARY, COMPANY NAME, JOURNEY;
@Query("SELECT DISTINCT c FROM Candidate c " +
			 "INNER JOIN FETCH c.experiences e " +
			 "WHERE " +
			 "(:gender is null or c.gender = :gender) and " +
			 "(:country is null or c.country = :country) and " +
			 "(:city is null or c.city = :city) and " +
			 "(:zip_code is null or c.zipCode = :zip_code) and " +
			 "(:availablePeriod is null or c.availablePeriod.name = :availablePeriod) and " +
			 "(:workModality is null or c.workModality.name = :workModality) and " +
			 "(:desiredJourney is null or c.desiredJourney.name = :desiredJourney) and " +
			 "(:pretensionSalary is null or c.pretensionSalary <= :pretensionSalary) and " +
			 "(:companyName is null or e.company.name = :companyName) and " +
			 "(:postName is null or e.post.name = :postName)"
 )
 List<Candidate> findCandidateByAnyParams(
			 @Param("gender") String gender,
			 @Param("country") String country,
			 @Param("city") String city,
			 @Param("zip_code") String zipCode,
			 @Param("availablePeriod") String availablePeriod,
			 @Param("workModality") String workModality,
			 @Param("pretensionSalary") Double pretensionSalary,
			 @Param("desiredJourney") String desiredJourney,
			 @Param("companyName") String companyName,
			 @Param("postName") String postName
 );
```

```java
//EXEMPLO DE SELECT REALIZANDO UM JOIN NA TABELA DE SKILLS PARA TRAZER AS SKILLS DO CANCIDATO
//SELECIONADO
Query("SELECT c FROM Candidate c " +
		 "INNER JOIN FETCH c.skills s " +
		 "INNER JOIN FETCH s.skill sk " +
		 "WHERE " +
		 "(id in :ids) and " +
		 "(sk.description = :skill) "
	 )
 List<Candidate> findCandidateBySkillAndId(
		 @Param("ids") List<Long> ids,
		 @Param("skill") String skill
 );
```

* Auxilio na idealização do projeto:

	*	Ao participar das reuniões com o cliente, tivemos uma reunião de grupo, onde definimos estratégias de tarefas e cronogramas de entrega. Com isso, participei junto à equipe destas decisões, focando no plano de negócio do cliente e qual a necessidade do mesmo.

![Backlog do Produto](https://gitlab.com/felipemessibraga/pi-1sem-2021/uploads/d95019d0b68b3054838f0a3c45a4ca3b/Backlog_do_Produto.jpg)

* Criação de servidor na AWS;

	*	Para que fosse possível realizar a hospedagem em nuvem, contratamos um servidor gratuito em nuvem(AWS), e hospedamos nossa aplicação. Realizei a instalação do PostgreSQL, Firewall no Ubuntu com iptables, configurei acesso SSH, e realizei a liberação de portas 80(HTTP) e 443(HTTPS).

O Proxy Reverso utilizado foi o NGINX, onde foi apontado para o serviço nemo.service, fazendo com que ao acessar a aplicação, o NGINX já apontasse para o serviço Nemo em execução.

![](https://gitlab.com/felipemessibraga/pi-1sem-2021/-/wikis/uploads/0ace901e33fb1baf95eba56665a19b9a/Design_sem_nome.gif)

Devido à custos que foram cobrados para permanecer com o servidor hospedado na AWS, o grupo decidiu hospedar a aplicação no Heroku, que é um provedor gratuito.

Com isso, o CI/CD foi transferido para o Heroku, conforme imagens abaixo:

Foi configurado o CI/CD da aplicação, onde foi criado um Pipeline de Build; Test; e Deploy; Ao executar o Deploy, já atualiza automaticamente o Deploy no servidor alocado no Heroku

![image](https://user-images.githubusercontent.com/62898187/139724835-478095ba-2e90-4bc5-9846-2d4fad402d38.png)

![image](https://user-images.githubusercontent.com/62898187/139725292-bf394383-5fdb-4471-8e18-119e57562e66.png)

Arquivo .gitlab-ci.yml:

```yml
image: docker:latest
services:
	 - docker:dind
variables:
	 DOCKER_DRIVER: overlay
	 SPRING_PROFILES_ACTIVE: gitlab-ci
	 REPOSITORY: pi-1sem-2021
	 NAME_PROJECT: nemo
	 REGISTRY: registry.gitlab.com/pi-1sem-2021
	 MAVEN_CLI_OPTS: "--batch-mode  --errors  --fail-at-end"
	 MAVEN_OPTS: "-Dhttps.protocols=TLSv1.2  -Dmaven.repo.local=.m2/pi-1sem-2021"
	 DOCKER_TLS_CERTDIR: ""
before_script:
	 - echo "-- Iniciando Comandos CI/CD"
	 - apt-get update -qy
	 - apt-get install -y ruby-dev
	 - gem install dpl
stages:
	 - build
	 - test
	 - deploy 
maven-build:
	 image: maven:3-jdk-11
	 stage: build
	 script: "mvn  -f  pom.xml  compile"
	 artifacts:
	 name: $NAME_PROJECT
	 paths:
	 - teste_nemo.jar
maven-testes:
	 image: maven:3-jdk-11
	 stage: test 
	 before_script:
	 - echo "Iniciando Testes da Aplicação"
	 script: 
	 - 'mvn  -f  pom.xml  $MAVEN_CLI_OPTS  install'
	 
production:
	 stage: deploy
	 image: maven:3-jdk-11
	 script:
	 - dpl --provider=heroku --app=$HEROKU_APP_PRODUCTION --api-key=$HEROKU_API_KEY
```

<b>Aprendizados Efetivos</b>

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
