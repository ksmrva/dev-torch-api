-- <editor-fold desc="Source">
-- ******************************************************************************
---------------------------------------------------------------------------------
-- Source
---------------------------------------------------------------------------------
-- ******************************************************************************

DROP TABLE IF EXISTS domain_model_source.schema_property;
DROP TABLE IF EXISTS domain_model_source.schema;

DROP SCHEMA IF EXISTS domain_model_source;

---------------------------------------------------------------------------------
-- Schema: domain_model_source
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS domain_model_source
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA domain_model_source
    IS 'Entities used to hold the schemas and metadata that define the Domain of a software environment';

---------------------------------------------------------------------------------
-- Table: domain_model_source.schema
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS domain_model_source.schema
(
    id            bigserial                         NOT NULL,
    draft         text COLLATE pg_catalog."default" NOT NULL,
    uri           text COLLATE pg_catalog."default" NOT NULL,
    title         text COLLATE pg_catalog."default",
    description   text COLLATE pg_catalog."default",
    type          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "schemaPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS domain_model_source.schema
    OWNER to postgresadmin;

COMMENT ON TABLE domain_model_source.schema
    IS 'Stores JSON Schema information that helps define the Domain Objects';

---------------------------------------------------------------------------------
-- Table: domain_model_source.schema_property
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS domain_model_source.schema_property
(
    id            bigserial                         NOT NULL,
    schema_id     bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    description   text COLLATE pg_catalog."default",
    type          text COLLATE pg_catalog."default" NOT NULL,
    required      boolean                           NOT NULL,
    custom_json   json                              NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "schemaPropertyPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "schemaPropertyNameUniqueWithinSchemaUniqueConstraint" UNIQUE (schema_id, name)
        INCLUDE (schema_id, name),
    CONSTRAINT "schemaPropertyUnderlyingSchemaFKConstraint" FOREIGN KEY (schema_id)
        REFERENCES domain_model_source.schema (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS domain_model_source.schema_property
    OWNER to postgresadmin;

COMMENT ON TABLE domain_model_source.schema_property
    IS 'Defines a Property that is used to construct a JSON Schema for the Domain';

COMMENT ON CONSTRAINT "schemaPropertyNameUniqueWithinSchemaUniqueConstraint" ON domain_model_source.schema_property
    IS 'Ensures that the Name of the Schema Property is unique within the Schema to which it belongs';

COMMENT ON CONSTRAINT "schemaPropertyUnderlyingSchemaFKConstraint" ON domain_model_source.schema_property
    IS 'Links this Schema Property with the Schema to which it belongs';

-- </editor-fold>

-- <editor-fold desc="Detail">
-- ******************************************************************************
---------------------------------------------------------------------------------
-- Detail
---------------------------------------------------------------------------------
-- ******************************************************************************
-- </editor-fold>