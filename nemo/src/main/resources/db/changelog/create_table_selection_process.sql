DROP TABLE IF EXISTS "selection_process";
DROP TABLE IF EXISTS "selection_process_status";

/* Table 'selection_process_status' */
CREATE TABLE "selection_process_status"(
status_name varchar (100) NOT NULL,
fk_jo_id serial NOT NULL,
create_dt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_dt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT status_pkey PRIMARY KEY (status_name, fk_jo_id),
CONSTRAINT fk_jo_id_status FOREIGN KEY (fk_jo_id) REFERENCES job_opportunity(jo_id)
);
/* Table 'selection_process' */
CREATE TABLE "selection_process" (
fk_jo_id serial NOT NULL,
fk_can_id serial NOT NULL,
fk_status_name varchar (100) NOT NULL,
obs varchar(100) NOT NULL,
create_dt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_dt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT selec_process_pkey PRIMARY KEY (fk_jo_id, fk_can_id),
CONSTRAINT fk_can_id_sp FOREIGN KEY (fk_can_id) REFERENCES candidate(can_id),
CONSTRAINT fk_jo_id_sp FOREIGN KEY (fk_jo_id,fk_status_name) REFERENCES selection_process_status(fk_jo_id,status_name)
);