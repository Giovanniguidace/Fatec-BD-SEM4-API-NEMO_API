ALTER TABLE candidate
ADD column if not exists desired_journey varchar(20);

ALTER TABLE candidate
ADD column if not exists work_modality varchar(20);

ALTER TABLE candidate
ADD column if not exists pretension_salary numeric(15,2);

ALTER TABLE candidate_skill
DROP CONSTRAINT candidate_skill_pkey;

ALTER TABLE candidate_skill
ADD COLUMN IF NOT EXISTS id serial;

ALTER TABLE candidate_skill add primary key (id);

ALTER TABLE candidate
ALTER COLUMN availability TYPE varchar(8);

ALTER TABLE candidate_exp
DROP CONSTRAINT candidate_exp_pkey;

ALTER TABLE candidate_exp
ADD COLUMN IF NOT EXISTS id serial;

ALTER TABLE candidate_exp add primary key (id);

ALTER TABLE candidate_formation
DROP CONSTRAINT candidate_formation_pkey;

ALTER TABLE candidate_formation
ADD COLUMN IF NOT EXISTS id serial;

ALTER TABLE candidate_formation add primary key (id);