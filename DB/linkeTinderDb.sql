CREATE TABLE IF NOT EXISTS "candidates" (
    "id" serial NOT NULL UNIQUE,
    "cpf" varchar(15) NOT NULL,
    "country" varchar(50) NOT NULL,
    "cep" varchar(9) NOT NULL,
    "description" text NOT NULL,
    "password" varchar(20) NOT NULL,
    "date_of_birth" varchar(255) NOT NULL,
    "frist_name" varchar(50) NOT NULL,
    "last_name" varchar(50) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "skill" (
    "id" serial NOT NULL UNIQUE,
    "name" varchar(50) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "candidate_skill" (
    "id" serial NOT NULL UNIQUE,
    "id_candidate" int NOT NULL,
    "id_skill" int NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "companies" (
    "id" serial NOT NULL UNIQUE,
    "name" varchar(50) NOT NULL,
    "cpnj" varchar(18) NOT NULL,
    "email" varchar(255) NOT NULL,
    "descripition" text NOT NULL,
    "country" varchar(50) NOT NULL,
    "cep" varchar(9) NOT NULL,
    "password" varchar(20) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "like_from_companie" (
    "id" serial NOT NULL UNIQUE,
    "candidate_id" int NOT NULL,
    "companie_id" int NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "vacancie_skill" (
    "id" serial NOT NULL UNIQUE,
    "name" varchar(50) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "job_vacancies" (
    "id" serial NOT NULL UNIQUE,
    "name " varchar(50) NOT NULL,
    "description" text NOT NULL,
    "state" varchar(50) NOT NULL,
    "city" varchar(50) NOT NULL,
    "companie_id" int NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "job_vacancies_skill" (
    "id" serial NOT NULL UNIQUE,
    "job_vacancie_id" int NOT NULL,
    "vacancie_skill_id" int NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "like_from_candidate" (
    "id" serial NOT NULL UNIQUE,
    "candidate_id" int NOT NULL,
    "companie_id" int NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE "candidate_skill" ADD CONSTRAINT "candidate_skill_fk1" FOREIGN KEY ("id_candidate") REFERENCES "candidates"("id");

ALTER TABLE "candidate_skill" ADD CONSTRAINT "candidate_skill_fk2" FOREIGN KEY ("id_skill") REFERENCES "skill"("id");

ALTER TABLE "like_from_companie" ADD CONSTRAINT "like_from_companie_fk1" FOREIGN KEY ("candidate_id") REFERENCES "candidates"("id");

ALTER TABLE "like_from_companie" ADD CONSTRAINT "like_from_companie_fk2" FOREIGN KEY ("companie_id") REFERENCES "companies"("id");

ALTER TABLE "job_vacancies" ADD CONSTRAINT "job_vacancies_fk5" FOREIGN KEY ("companie_id") REFERENCES "companies"("id");

ALTER TABLE "job_vacancies_skill" ADD CONSTRAINT "job_vacancies_skill_fk1" FOREIGN KEY ("job_vacancie_id") REFERENCES "job_vacancies"("id");

ALTER TABLE "job_vacancies_skill" ADD CONSTRAINT "job_vacancies_skill_fk2" FOREIGN KEY ("vacancie_skill_id") REFERENCES "vacancie_skill"("id");

ALTER TABLE "like_from_candidate" ADD CONSTRAINT "like_from_candidate_fk1" FOREIGN KEY ("candidate_id") REFERENCES "candidates"("id");

ALTER TABLE "like_from_candidate" ADD CONSTRAINT "like_from_candidate_fk2" FOREIGN KEY ("companie_id") REFERENCES "companies"("id");