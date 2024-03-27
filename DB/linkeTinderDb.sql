CREATE TABLE IF NOT EXISTS "candidates" (
    "id" SERIAL NOT NULL UNIQUE,
    "cpf" VARCHAR(15) NOT NULL,
    "country" VARCHAR(50) NOT NULL,
    "cep" VARCHAR(9) NOT NULL,
    "description" TEXT NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    "date_of_birth" DATE NOT NULL,
    "frist_name" VARCHAR(50) NOT NULL,
    "last_name" VARCHAR(50) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "skill" (
    "id" SERIAL NOT NULL UNIQUE,
    "name" VARCHAR(50) NOT NULL,
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
    "cpnj" VARCHAR(18) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "descripition" TEXT NOT NULL,
    "country" VARCHAR(50) NOT NULL,
    "cep" VARCHAR(9) NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "job_vacancies" (
    "id" SERIAL NOT NULL UNIQUE,
    "name " VARCHAR(50) NOT NULL,
    "description" TEXT NOT NULL,
    "state" VARCHAR(50) NOT NULL,
    "city" VARCHAR(50) NOT NULL,
    "companie_id" INT NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "job_vacancies_skill" (
    "id" SERIAL NOT NULL UNIQUE,
    "job_vacancie_id" INT NOT NULL,
    "skill_id" INT NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "like" (
    "id" SERIAL NOT NULL UNIQUE,
    "candidate_id" INT NOT NULL,
    "companie_id" INT NOT NULL,
    "job_vacancie_id" INT,
    PRIMARY KEY ("id")
);

ALTER TABLE "candidate_skill" ADD CONSTRAINT "candidate_skill_fk1" FOREIGN KEY ("candidate_id") REFERENCES "candidates"("id");

ALTER TABLE "candidate_skill" ADD CONSTRAINT "candidate_skill_fk2" FOREIGN KEY ("skill_id") REFERENCES "skill"("id");

ALTER TABLE "like" ADD CONSTRAINT "like_fk1" FOREIGN KEY ("candidate_id") REFERENCES "candidates"("id");

ALTER TABLE "like" ADD CONSTRAINT "like_fk2" FOREIGN KEY ("companie_id") REFERENCES "companies"("id");

ALTER TABLE "like" ADD CONSTRAINT "like_fk3" FOREIGN KEY ("job_vacancie_id") REFERENCES "job_vacancies"("id");

ALTER TABLE "job_vacancies" ADD CONSTRAINT "job_vacancies_fk1" FOREIGN KEY ("companie_id") REFERENCES "companies"("id");

ALTER TABLE "job_vacancies_skill" ADD CONSTRAINT "job_vacancies_skill_fk1" FOREIGN KEY ("job_vacancie_id") REFERENCES "job_vacancies"("id");

ALTER TABLE "job_vacancies_skill" ADD CONSTRAINT "job_vacancies_skill_fk2" FOREIGN KEY ("skill_id") REFERENCES "vacancie_skill"("id");
