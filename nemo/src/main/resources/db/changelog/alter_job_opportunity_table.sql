ALTER TABLE job_opportunity
ALTER COLUMN contract_type TYPE contract_type USING contract_type::contract_type;

ALTER TABLE job_opportunity
ALTER COLUMN availability TYPE availability USING availability::availability;