DROP FUNCTION if exists selection_process_trigger_func() CASCADE;
DROP function if EXISTS first_status_trigger_func() CASCADE;


-- func trigger para adicionar o status 0
CREATE OR REPLACE FUNCTION first_status_trigger_func()
RETURNS trigger AS
$$
BEGIN
    INSERT INTO "selection_process_status" ("status_name", "fk_jo_id")  VALUES('Pré-Selecionado',NEW."jo_id");
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

-- trigger para adicionar o status 0
create trigger first_status
	after  insert on job_opportunity
	for each row
	execute procedure first_status_trigger_func();

-- func trigger para adicionar os candidatos
CREATE OR REPLACE FUNCTION selection_process_trigger_func()
RETURNS trigger AS
$$
 declare
    temprow record;
begin
	FOR temprow IN
		select CAN_ID from candidate c LIMIT 10 -- DUMMY QUERY
	loop
    	INSERT INTO "selection_process" ("fk_jo_id", "fk_can_id", fk_status_name ) VALUES(new."jo_id",temprow.CAN_ID, 'Pré-Selecionado');
    END LOOP;
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

-- trigger para adicionar os candidatos
create trigger selection_process_candidate
	after  insert on job_opportunity
	for each row
	execute procedure selection_process_trigger_func();
