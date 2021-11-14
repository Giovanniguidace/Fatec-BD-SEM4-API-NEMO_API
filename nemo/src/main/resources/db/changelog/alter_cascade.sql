ALTER TABLE jo_exp_req
DROP CONSTRAINT fk_post_id_joer,
ADD CONSTRAINT fk_post_id_joer
    FOREIGN KEY(fk_post_id) REFERENCES "post"(post_id) ON DELETE CASCADE;

ALTER TABLE jo_formation_req
DROP CONSTRAINT fk_course_id_jofr,
ADD CONSTRAINT fk_course_id_jofr
    FOREIGN KEY(fk_course_id) REFERENCES course(course_id) ON DELETE CASCADE;