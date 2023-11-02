-- liquibase formatted sql

-- changeset krakhimzyanov:1
CREATE INDEX student_name_index ON student (name);

-- changeset krakhimzyanov:2
CREATE INDEX faculty_nc_index ON faculty (name, color);