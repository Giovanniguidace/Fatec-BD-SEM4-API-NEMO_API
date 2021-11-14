create table if not exists distance_parameters (
    distance_parameter_id int8 PRIMARY KEY,
    start_low_distance int8 not null,
    end_low_distance int8 not null,
    low_distance_value integer,
    start_medium_distance int8 not null,
    end_medium_distance int8 not null,
    medium_distance_value integer not null,
    start_high_distance int8 not null,
    end_high_distance int8 not null,
    high_distance_value integer
);

create table if not exists parameter (
    parameter_id int8 not null,
    hability integer not null,
    experience integer not null,
    distance_parameter_id int8
);

ALTER TABLE parameter
    ADD CONSTRAINT parameter_fk
        FOREIGN KEY (distance_parameter_id)
            REFERENCES distance_parameters (distance_parameter_id);
