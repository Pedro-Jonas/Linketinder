# Linketinder

## DESCRIÇÃO
Linketinder é uma plataforma inovadora que combina a familiaridade e a simplicidade do Tinder com o profissionalismo e networking do LinkedIn, oferecendo uma abordagem revolucionária para recrutamento anônimo. Projetada para profissionais em busca de oportunidades de carreira e empregadores em busca de talento,linketinder redefine a maneira como as conexões profissionais são feitas e mantidas.

## Modelagem do Banco de Dados
DB Designer
<img src="./DB/linketinder.png">
## COMO RODAR
1. Faça o clone da apliação
2. Navegue até o diretório do projeto
3. Execulte
```bash
gradle build
```
4. Enfim execulte
```bash
gradle run
```
## Melhorias de Refatorção
1. Criação de Testes
2. Criação de Interfaces
3. Separação de Responsabilidades
4. Aplicação de princípios SOLID
5. Adoção do padrão Factory
- Para facilitar a integração de bancos de dados diferentes sem quebrar a apliação
6. Adoção do padrão Singleton
- Garantindo que existe apenas uma conexão com o banco de dados e que seja instaciada a partir da mesma

## EndPoints da API
### Foi utilizado o TomCat e Servlets para implementação da API

1. Listagem de candidatos
- GET para http://localhost:8080/Linketinder/candidates
2. Listagem de candidatos com Habilidades
- GET para http://localhost:8080/Linketinder/candidates/withSkills
3. Cadastro de candidatos
- POST para http://localhost:8080/Linketinder/candidates
- Body exemplo
```bash
{
  "firstName": "Teste",
  "lastName": "Teste",
  "dateOfBirth": "1999-12-12",
  "cpf": "111.111.111-11",
  "email": "teste@gmail.com",
  "country": "Teste",
  "cep": "11111-111",
  "description": "teste",
  "password": "test1111"
}
```
4. Listagem de empresas
- GET para http://localhost:8080/Linketinder/companies
5. Cadastro de empresas
- POST para http://localhost:8080/Linketinder/companies
- Body exemplo
```bash
{
  "name": "teste",
  "cnpj": "11.111.111/1111-11",
  "email": "teste@gmail.com",
  "country": "Teste",
  "cep": "11111-111",
  "description": "Teste",
  "password": "Teste1111"
}
```
6. Listagem de Vagas
- GET para http://localhost:8080/Linketinder/jobVacancies
7. Listagem de Vagas com Habilidades
- GET para http://localhost:8080/Linketinder/jobVacancies/withSkills
8. Cadastro de vagas
- POST para http://localhost:8080/Linketinder/jobVacancies
- Body exemplo
```bash
{
  "name": "Teste",
  "state": "Teste",
  "city": "Teste",
  "description": "Teste",
  "companyId": 1
}
```
9. Listagem de hablidades
- GET para http://localhost:8080/Linketinder/skills
10. cadastro de habilidades do candidato
- POST para http://localhost:8080/Linketinder/skills/candidates
- Body exemplo
```bash
{
  "candidateId": 1,
  "name": "Teste"
}
```
11. Cadastro de habilidades da vaga
- POST para http://localhost:8080/Linketinder/skills/companies
- Body exemplo
```bash
{
  "jobVacancyId": 1,
  "name": "Teste"
}
```

<!-- Pedro Jonas Nunes de Araújo -->
