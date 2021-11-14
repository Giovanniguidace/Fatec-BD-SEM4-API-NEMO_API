CREATE TABLE public.distance_matrix (
                                        id serial NOT NULL,
                                        start_address varchar(155) NULL,
                                        end_address varchar(155) NULL,
                                        total_distance float8 NULL,
                                        can_id serial NOT NULL,
                                        jo_id serial NOT NULL,
                                        CONSTRAINT distance_matrix_pkey PRIMARY KEY (id)
);


-- public.distance_matrix foreign keys

ALTER TABLE public.distance_matrix ADD CONSTRAINT fk_job null;
ALTER TABLE public.distance_matrix ADD CONSTRAINT fk_user null;

CREATE TABLE public.legs (
                             id serial NOT NULL,
                             start_address varchar(100) NULL,
                             end_address varchar(100) NULL,
                             distance float8 NULL,
                             duration float8 NULL,
                             distance_matrix_id serial NOT NULL,
                             CONSTRAINT legs_pk PRIMARY KEY (id)
);


-- public.legs foreign keys

ALTER TABLE public.legs ADD CONSTRAINT fk_distance_matrix FOREIGN KEY (distance_matrix_id) REFERENCES distance_matrix(id);


CREATE TABLE public.steps (
                              id serial NOT NULL,
                              distance float8 NULL,
                              duration float8 NULL,
                              html_instruction varchar(155) NULL,
                              travel_mode varchar(20) NULL,
                              leg_id serial NOT NULL
);


-- public.steps foreign keys

ALTER TABLE public.steps ADD CONSTRAINT fk_legs_id FOREIGN KEY (leg_id) REFERENCES legs(id);
