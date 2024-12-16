DROP TABLE IF EXISTS example.employee;

---------------------------------------------------------------------------------
-- Table: example.employee
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS example.employee
(
    id         bigserial                         NOT NULL,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name  text COLLATE pg_catalog."default" NOT NULL,
    email      text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "employeePKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS example.employee
    OWNER to postgresadmin;