DROP TABLE IF EXISTS code_model.text_file_extension;
DROP TABLE IF EXISTS code_model.text_file;
DROP TABLE IF EXISTS code_model.data_file;
DROP TABLE IF EXISTS code_model.directory_file;
DROP TABLE IF EXISTS code_model.file;
DROP TABLE IF EXISTS code_model.project;
DROP TABLE IF EXISTS code_model.file_node;
DROP TABLE IF EXISTS code_model.file_type;

DROP SCHEMA IF EXISTS code_model;

---------------------------------------------------------------------------------
-- Schema: code_model
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS code_model
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA code_model
    IS 'Entities used to describe a Model of a Codebase';

---------------------------------------------------------------------------------
-- Table: code_model.file_type
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.file_type
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "fileTypePKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.file_type
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.file_type
    IS 'Represents the type of File stored within a Codebase';

INSERT INTO code_model.file_type (name, created_uid, created_date, modified_uid, modified_date)
VALUES ('data', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('text', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('directory', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: code_model.file_node
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.file_node
(
    id                  bigserial                         NOT NULL,
    parent_file_node_id bigint,
    created_uid         text COLLATE pg_catalog."default" NOT NULL,
    created_date        date                              NOT NULL,
    modified_uid        text COLLATE pg_catalog."default" NOT NULL,
    modified_date       date                              NOT NULL,
    CONSTRAINT "fileNodePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "fileNodeParentNodeFKConstraint" FOREIGN KEY (parent_file_node_id)
        REFERENCES code_model.file_node (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.file_node
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.file_node
    IS 'Represents a Node in a tree structure modeling files within a codebase';

COMMENT ON CONSTRAINT "fileNodeParentNodeFKConstraint" ON code_model.file_node
    IS 'Links this File Node with another File Node representing its parent in the folder tree';

---------------------------------------------------------------------------------
-- Table: code_model.file
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.file
(
    id            bigserial                         NOT NULL,
    file_node_id  bigint                            NOT NULL,
    file_type_id  bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "filePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "fileNodeFKConstraint" FOREIGN KEY (file_node_id)
        REFERENCES code_model.file_node (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "fileTypeFKConstraint" FOREIGN KEY (file_type_id)
        REFERENCES code_model.file_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.file
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.file
    IS 'Represents a File within a Codebase';

COMMENT ON CONSTRAINT "fileNodeFKConstraint" ON code_model.file
    IS 'Links this File with a File Node to allow it to be represented in the file tree';
COMMENT ON CONSTRAINT "fileTypeFKConstraint" ON code_model.file
    IS 'Links this File with a File Type that defines its potential contents';

---------------------------------------------------------------------------------
-- Table: code_model.directory_file
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.directory_file
(
    id      bigserial NOT NULL,
    file_id bigint    NOT NULL,
    CONSTRAINT "directoryFilePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "directoryFileUnderlyingFileFKConstraint" FOREIGN KEY (file_id)
        REFERENCES code_model.file (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.directory_file
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.directory_file
    IS 'Represents a File that is a Directory that can contain other Files';

COMMENT ON CONSTRAINT "directoryFileUnderlyingFileFKConstraint" ON code_model.directory_file
    IS 'Links this Directory File with its underlying File Entity';

---------------------------------------------------------------------------------
-- Table: code_model.data_file
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.data_file
(
    id       bigserial NOT NULL,
    file_id  bigint    NOT NULL,
    contents bytea     NOT NULL,
    CONSTRAINT "dataFilePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dataFileUnderlyingFileFKConstraint" FOREIGN KEY (file_id)
        REFERENCES code_model.file (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.data_file
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.data_file
    IS 'Represents a File that has raw binary as its contents';

COMMENT ON CONSTRAINT "dataFileUnderlyingFileFKConstraint" ON code_model.data_file
    IS 'Links this Data File with its underlying File Entity';

---------------------------------------------------------------------------------
-- Table: code_model.text_file
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.text_file
(
    id       bigserial NOT NULL,
    file_id  bigint    NOT NULL,
    contents text      NOT NULL,
    CONSTRAINT "textFilePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "textFileUnderlyingFileFKConstraint" FOREIGN KEY (file_id)
        REFERENCES code_model.file (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.text_file
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.text_file
    IS 'Represents a File that has text as its contents';

COMMENT ON CONSTRAINT "textFileUnderlyingFileFKConstraint" ON code_model.text_file
    IS 'Links this Text File with its underlying File Entity';

---------------------------------------------------------------------------------
-- Table: code_model.text_file_extension
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.text_file_extension
(
    id            bigserial                         NOT NULL,
    extension     text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "textFileExtensionPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.text_file_extension
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.text_file_extension
    IS 'Stores the File Extensions that correspond to a Text File';

INSERT INTO code_model.text_file_extension (extension, created_uid, created_date, modified_uid, modified_date)
VALUES ('java', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('xml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('json', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('yml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('yaml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('md', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: code_model.project
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model.project
(
    id                bigserial                         NOT NULL,
    name              text COLLATE pg_catalog."default" NOT NULL,
    root_file_node_id bigint                            NOT NULL,
    created_uid       text COLLATE pg_catalog."default" NOT NULL,
    created_date      date                              NOT NULL,
    modified_uid      text COLLATE pg_catalog."default" NOT NULL,
    modified_date     date                              NOT NULL,
    CONSTRAINT "projectPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "codeProjectNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name),
    CONSTRAINT "projectRootFileNodeFKConstraint" FOREIGN KEY (root_file_node_id)
        REFERENCES code_model.file_node (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model.project
    OWNER to postgresadmin;

COMMENT ON TABLE code_model.project
    IS 'Represents a Code Project such as an API or UI application';

COMMENT ON CONSTRAINT "codeProjectNameUniqueConstraint" ON code_model.project
    IS 'Ensures that the Name of the Code Project is unique among all Code Projects';

COMMENT ON CONSTRAINT "projectRootFileNodeFKConstraint" ON code_model.project
    IS 'Links this Code Project with a File Node that represents its root Directory';