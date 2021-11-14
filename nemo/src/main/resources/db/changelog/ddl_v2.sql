/* ************* DLL ************* */

DROP TABLE IF EXISTS "candidate_skill" CASCADE;
DROP TABLE IF EXISTS "candidate_exp" CASCADE;
DROP TABLE IF EXISTS "candidate_formation" CASCADE;
DROP TABLE IF EXISTS "jo_skill_req" CASCADE;
DROP TABLE IF EXISTS "jo_exp_req" CASCADE;
DROP TABLE IF EXISTS "jo_formation_req" CASCADE;
DROP TABLE IF EXISTS "skill" CASCADE;
DROP TABLE IF EXISTS "institution" CASCADE;
DROP TABLE IF EXISTS "course" CASCADE;
DROP TABLE IF EXISTS "company" CASCADE;
DROP TABLE IF EXISTS "post" CASCADE;
DROP TYPE IF EXISTS "contract_type" CASCADE;
DROP TYPE IF EXISTS "skill_level" CASCADE;
DROP TYPE IF EXISTS "availability" CASCADE;
DROP TABLE IF EXISTS "candidate" CASCADE;
DROP TABLE IF EXISTS "job_opportunity" CASCADE;

/* Enum 'availability' */
CREATE TYPE availability
AS ENUM('M', 'T', 'N', 'S', 'MT', 'MN', 'MS', 'TN', 'NS', 'MTN', 'MTS', 'MNS', 'TNS', 'MTNS');

/* Table 'candidate' */
CREATE TABLE "candidate" (
can_id SERIAL,
can_name varchar(100) NOT NULL,
email varchar(100) NOT null UNIQUE,
cpf varchar(14) NOT null UNIQUE,
phone varchar(14) NOT NULL,
gender varchar(20) NOT NULL,
birthday date NOT NULL,
country varchar(100) NOT NULL,
availability availability NOT NULL,
city varchar(100) NOT NULL,
neighborhood varchar(100) NOT NULL,
street varchar(100) NOT NULL,
home_number integer NOT NULL,
complement varchar(100),
zip_code varchar(8) NOT NULL,
latitude float8 NOT NULL,
longitude float8 NOT NULL,
create_dt date NOT NULL default CURRENT_DATE,
update_dt date,
PRIMARY KEY(can_id)
);

/* Table 'skill' */
CREATE TABLE "skill" (
skill_id serial,
description varchar(20) NOT NULL UNIQUE,
PRIMARY KEY(skill_id));


/* Enum 'skill_level' */
CREATE TYPE skill_level
AS ENUM('ONE','TWO','THREE','FOUR','FIVE');

/* Enum 'candidate_skill' */
CREATE TABLE "candidate_skill" (
fk_can_id serial,
fk_skill_id serial,
skill_level skill_level NOT null,
PRIMARY KEY(fk_can_id, fk_skill_id),
CONSTRAINT fk_can_id_cs FOREIGN KEY(fk_can_id) REFERENCES "candidate"(can_id),
CONSTRAINT fk_skill_id_cs FOREIGN KEY(fk_skill_id) REFERENCES "skill"(skill_id)
);

/* Table 'company' */
CREATE TABLE "company" (
company_id serial NOT NULL,
com_name varchar(30) UNIQUE NOT NULL,
PRIMARY KEY(company_id));

/* Table 'post' */
CREATE TABLE "post" (
post_id serial NOT NULL,
post_name varchar(30) UNIQUE NOT NULL,
PRIMARY KEY(post_id));

/* Table 'candidate_exp' */
CREATE TABLE "candidate_exp" (
fk_can_id serial NOT NULL,
fk_company_id serial NOT NULL,
fk_post_id serial NOT NULL,
dt_start date NOT NULL,
dt_end date,
description text NOT NULL,
PRIMARY KEY(fk_can_id,fk_company_id,fk_post_id),
CONSTRAINT fk_can_id_ce FOREIGN KEY(fk_can_id) REFERENCES "candidate"(can_id),
CONSTRAINT fk_company_id FOREIGN KEY(fk_company_id) REFERENCES "company"(company_id),
CONSTRAINT fk_post_id FOREIGN KEY(fk_post_id) REFERENCES "post"(post_id));


/* Table 'institution' */
CREATE TABLE "institution" (
inst_id serial NOT NULL,
inst_name varchar(30) UNIQUE NOT NULL,
PRIMARY KEY(inst_id));

/* Table 'course' */
CREATE TABLE "course" (
course_id serial NOT NULL,
course_name varchar(30) UNIQUE NOT NULL,
PRIMARY KEY(course_id));

/* Table 'candidate_formation' */
CREATE TABLE "candidate_formation" (
fk_can_id serial NOT NULL,
fk_inst_id serial NOT NULL,
fk_course_id serial NOT NULL,
dt_start date NOT NULL,
dt_end date NOT NULL,
PRIMARY KEY(fk_can_id,fk_inst_id,fk_course_id),
CONSTRAINT fk_can_id_cf FOREIGN KEY(fk_can_id) REFERENCES "candidate"(can_id),
CONSTRAINT fk_inst_id FOREIGN KEY(fk_inst_id) REFERENCES "institution"(inst_id),
CONSTRAINT fk_course_id FOREIGN KEY(fk_course_id) REFERENCES "course"(course_id));

/* Enum 'contract_type' */
CREATE TYPE "contract_type"
AS ENUM('PJ', 'CLT', 'INTERN', 'APRENDIZ', 'FREELANCE');

/* Table 'job_opportunity' */
CREATE TABLE "job_opportunity" (
	jo_id serial NOT NULL,
	jo_name varchar(100) NOT NULL,
	description varchar(250) NOT NULL,
	contract_type varchar(50) NOT NULL,
	working_hours time NOT NULL, -- per month
	salary_range_ini float8 NOT NULL,
	salary_range_end float8 NOT NULL,
	gender varchar(20),
	availability varchar(50) NOT NULL,
	workplace_country varchar(20),
	workplace_city varchar(30),
	workplace_neighborhood varchar(30),
	workplace_street varchar(30),
	workplace_home_number int4,
	workplace_complement varchar(30),
	workplace_zip_code varchar(8),
	workplace_latitude float8,
	workplace_longitude float8,
	divulgation_ini timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	divulgation_end timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	create_dt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_dt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(jo_id)
);

/* Table 'jo_skill_req' */
CREATE TABLE "jo_skill_req" (
fk_jo_id serial NOT NULL,
fk_skill_id serial NOT NULL,
skill_level skill_level NOT NULL,
PRIMARY KEY(fk_jo_id, fk_skill_id),
CONSTRAINT fk_jo_id_josr FOREIGN KEY(fk_jo_id) REFERENCES "job_opportunity"(jo_id),
CONSTRAINT fk_skill_josr FOREIGN KEY(fk_skill_id) REFERENCES "skill"(skill_id)
);


/* Table 'jo_formation_req' */
CREATE TABLE "jo_formation_req" (
fk_jo_id serial NOT NULL,
fk_course_id serial NOT NULL,
finished bool NOT NULL,
PRIMARY KEY(fk_jo_id, fk_course_id),
CONSTRAINT fk_jo_id_jofr FOREIGN KEY(fk_jo_id) REFERENCES "job_opportunity"(jo_id),
CONSTRAINT fk_course_id_jofr FOREIGN KEY(fk_course_id) REFERENCES "course"(course_id)
);


/* Table 'jo_exp_req' */
CREATE TABLE "jo_exp_req" (
fk_jo_id serial NOT NULL,
fk_post_id serial NOT NULL,
exp_time integer NOT null, --  months
PRIMARY KEY(fk_jo_id, fk_post_id),
CONSTRAINT fk_jo_id_joer FOREIGN KEY(fk_jo_id) REFERENCES "job_opportunity"(jo_id),
CONSTRAINT fk_post_id_joer FOREIGN KEY(fk_post_id) REFERENCES "post"(post_id)
);

alter table candidate add column if not exists geom geometry;

CREATE SEQUENCE if not exists serial START 1;
