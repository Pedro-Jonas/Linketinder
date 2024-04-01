CREATE TABLE IF NOT EXISTS "candidates" (
    "id" SERIAL NOT NULL UNIQUE,
    "frist_name" VARCHAR(50) NOT NULL,
    "last_name" VARCHAR(50) NOT NULL,
    "date_of_birth" DATE NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "cpf" VARCHAR(15) NOT NULL,
    "country" VARCHAR(50) NOT NULL,
    "cep" VARCHAR(9) NOT NULL,
    "description" TEXT NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "skill" (
    "id" SERIAL NOT NULL UNIQUE,
    "name" VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "candidate_skill" (
    "id" SERIAL NOT NULL UNIQUE,
    "candidate_id" INT NOT NULL,
    "skill_id" INT NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "companies" (
    "id" SERIAL NOT NULL UNIQUE,
    "name" VARCHAR(50) NOT NULL,
    "cnpj" VARCHAR(18) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "description" TEXT NOT NULL,
    "country" VARCHAR(50) NOT NULL,
    "cep" VARCHAR(9) NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "job_vacancies" (
    "id" SERIAL NOT NULL UNIQUE,
    "name" VARCHAR(50) NOT NULL,
    "description" TEXT NOT NULL,
    "state" VARCHAR(50) NOT NULL,
    "city" VARCHAR(50) NOT NULL,
    "company_id" INT NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "job_vacancies_skill" (
    "id" SERIAL NOT NULL UNIQUE,
    "job_vacancy_id" INT NOT NULL,
    "skill_id" INT NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "like" (
    "id" SERIAL NOT NULL UNIQUE,
    "candidate_id" INT NOT NULL,
    "company_id" INT NOT NULL,
    "job_vacancy_id" INT,
    PRIMARY KEY ("id")
);

ALTER TABLE "candidate_skill" ADD CONSTRAINT "candidate_skill_fk1" FOREIGN KEY ("candidate_id") REFERENCES "candidates"("id") ON DELETE CASCADE;

ALTER TABLE "candidate_skill" ADD CONSTRAINT "candidate_skill_fk2" FOREIGN KEY ("skill_id") REFERENCES "skill"("id") ON DELETE CASCADE;

ALTER TABLE "like" ADD CONSTRAINT "like_fk1" FOREIGN KEY ("candidate_id") REFERENCES "candidates"("id") ON DELETE CASCADE;

ALTER TABLE "like" ADD CONSTRAINT "like_fk2" FOREIGN KEY ("company_id") REFERENCES "companies"("id") ON DELETE CASCADE;

ALTER TABLE "like" ADD CONSTRAINT "like_fk3" FOREIGN KEY ("job_vacancy_id") REFERENCES "job_vacancies"("id") ON DELETE CASCADE;

ALTER TABLE "job_vacancies" ADD CONSTRAINT "job_vacancies_fk1" FOREIGN KEY ("company_id") REFERENCES "companies"("id") ON DELETE CASCADE;

ALTER TABLE "job_vacancies_skill" ADD CONSTRAINT "job_vacancies_skill_fk1" FOREIGN KEY ("job_vacancy_id") REFERENCES "job_vacancies"("id") ON DELETE CASCADE;

ALTER TABLE "job_vacancies_skill" ADD CONSTRAINT "job_vacancies_skill_fk2" FOREIGN KEY ("skill_id") REFERENCES "skill"("id") ON DELETE CASCADE;

-- inserindo os candidatos

INSERT INTO candidates (frist_name, last_name, date_of_birth, email,
                        cpf, country, cep, description, password)
VALUES ('Pedro Jonas', 'Nunes de Araújo', '19/07/2000', 'jonas@gmail.com', '111.111.111-11',
        '	Brasil', '11111-111', 'Essa é a descrição', 'jonas1111');

INSERT INTO candidates (frist_name, last_name, date_of_birth, email,
                        cpf, country, cep, description, password)
VALUES ('Maria Eduarda', 'Pereira', '20/08/2001','eduarda@gmail.com', '222.222.222-22',
        '	Brasil', '22222-222', 'Essa é a descrição', 'duda2222');

INSERT INTO candidates (frist_name, last_name, date_of_birth, email,
                        cpf, country, cep, description, password)
VALUES ('Guilherme', 'de Araújo', '15/09/2002','guilherme@gmail.com', '333.333.333-33',
        'Brasil', '33333-333', 'Essa é a descrição', 'gui3333');

INSERT INTO candidates (frist_name, last_name, date_of_birth, email,
                        cpf, country, cep, description, password)
VALUES ('Elizane', 'Nunes', '31/07/1999','eliziane@gmail.com', '444.444.444-44',
        'Brasil', '44444-444', 'Essa é a descrição', 'eli4444');

INSERT INTO candidates (frist_name, last_name, date_of_birth, email,
                        cpf, country, cep, description, password)
VALUES ('Ednaldo', 'Caetano', '23/04/1983','ednaldo@gmail.com', '555.555.555-55',
        'Brasil', '55555-555', 'Essa é a descrição', 'naldo5555');

-- inserindo as skills

INSERT INTO skill (name)
VALUES ('Java');

INSERT INTO skill (name)
VALUES ('Groovy');

INSERT INTO skill (name)
VALUES ('Javascript');

INSERT INTO skill (name)
VALUES ('Python');

INSERT INTO skill (name)
VALUES ('C++');

INSERT INTO skill (name)
VALUES ('Go');

-- relacionando as skills com candidatos

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (1,1);

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (1,2);

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (2,3);

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (3,5);

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (3,6);

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (4,1);

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (4,2);

INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (5,3);

-- inserindo empresas

INSERT INTO companies (name, cnpj, email, description, country, cep,  password)
VALUES ('brasitech', '11.111.111/1111-11','brasitech@gmail.com',
        'Essa é a descrição','Brasil', '11111-111', 'brasitech1111');

INSERT INTO companies (name, cnpj, email, description, country, cep,  password)
VALUES ('CyberSolutions', '22.222.222/2222-22','CyberSolutions@gmail.com',
        'Essa é a descrição','Brasil', '22222-222', 'Cyber2222');

INSERT INTO companies (name, cnpj, email, description, country, cep,  password)
VALUES ('DigitalEdge', '33.333.333/3333-33','DigitalEdge@gmail.com',
        'Essa é a descrição','Brasil', '33333-333', 'DigitalEdge3333');

INSERT INTO companies (name, cnpj, email, description, country, cep,  password)
VALUES ('ByteLabs', '44.444.444/4444-44','ByteLabs@gmail.com',
        'Essa é a descrição','Brasil', '44444-444', 'ByteLabs3333');

INSERT INTO companies (name, cnpj, email, description, country, cep,  password)
VALUES ('FutureTech', '55.555.555/5555-55','FutureTech@gmail.com',
        'Essa é a descrição','Brasil', '55555-555', 'FutureTech3333');

-- inserindo vagas

INSERT INTO job_vacancies (name, description, state, city, company_id)
VALUES ('vaga1','Essa é a descrição','Ceará', 'Crato', 1);

INSERT INTO job_vacancies (name, description, state, city, company_id)
VALUES ('vaga2','Essa é a descrição','Ceará', 'Crato', 1);

INSERT INTO job_vacancies (name, description, state, city, company_id)
VALUES ('vaga3','Essa é a descrição','São Paulo', 'São Paulo', 2);

INSERT INTO job_vacancies (name, description, state, city, company_id)
VALUES ('vaga4','Essa é a descrição','Rio Grande do Norte', 'Natal', 4);

INSERT INTO job_vacancies (name, description, state, city, company_id)
VALUES ('vaga5','Essa é a descrição','Ceará', 'Fortaleza', 5);

-- relacionando as skills com vagas

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (1,1);

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (1,2);

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (2,3);

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (3,5);

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (3,6);

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (4,1);

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (5,3);

INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id)
VALUES (5,4);