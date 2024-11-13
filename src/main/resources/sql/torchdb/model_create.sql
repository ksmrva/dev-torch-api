DROP TABLE IF EXISTS model.db_foreign_key_model;
DROP TABLE IF EXISTS model.db_primary_key_column_model;
DROP TABLE IF EXISTS model.db_primary_key_model;
DROP TABLE IF EXISTS model.db_column_model;
DROP TABLE IF EXISTS model.db_column_category;
DROP TABLE IF EXISTS model.db_table_model;
DROP TABLE IF EXISTS model.db_table_category;
DROP TABLE IF EXISTS model.db_model;
DROP TABLE IF EXISTS model.db_data_type;
DROP TABLE IF EXISTS model.db_model_source_config;
DROP TABLE IF EXISTS model.db_model_source_url;
DROP TABLE IF EXISTS model.db_model_source_preset;
DROP TABLE IF EXISTS model.db_model_source_url_supported_provider;
DROP TABLE IF EXISTS model.db_model_source_url_supported_scheme;
DROP TABLE IF EXISTS model.db_model_source_config_supported_driver;

DROP SCHEMA IF EXISTS model;

---------------------------------------------------------------------------------
-- Schema: model
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS model
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA model
    IS 'Data for Model related Objects';

---------------------------------------------------------------------------------
-- Table: model.db_model_source_config_supported_driver
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_model_source_config_supported_driver
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbModelSourceConfigSupportedDriverPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dbModelSourceConfigSupportedDriverNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_model_source_config_supported_driver
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_model_source_config_supported_driver
    IS 'Stores information about the Drivers which are supported within the Data Source Config for connecting to a Database to obtain Model data';

COMMENT ON CONSTRAINT "dbModelSourceConfigSupportedDriverNameUniqueConstraint" ON model.db_model_source_config_supported_driver
    IS 'Ensures that the Name value is unique among all Database Model Source Config Supported Drivers';

INSERT INTO model.db_model_source_config_supported_driver (name, created_uid, created_date, modified_uid, modified_date)
VALUES ('org.postgresql.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('oracle.jdbc.OracleDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('com.mysql.cj.jdbc.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mongodb.jdbc.MongoDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: model.db_model_source_url_supported_provider
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_model_source_url_supported_provider
(
    id            bigserial                         NOT NULL,
    provider      text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbModelSourceUrlSupportedProviderPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dbModelSourceUrlSupportedProviderUniqueConstraint" UNIQUE (provider)
        INCLUDE (provider)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_model_source_url_supported_provider
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_model_source_url_supported_provider
    IS 'Stores API-supported values for a Database Model Source Url''s Provider field';

COMMENT ON CONSTRAINT "dbModelSourceUrlSupportedProviderUniqueConstraint" ON model.db_model_source_url_supported_provider
    IS 'Ensures that the Provider value is unique among all Database Model Source URL Supported Providers';

INSERT INTO model.db_model_source_url_supported_provider (provider, created_uid, created_date, modified_uid, modified_date)
VALUES ('postgresql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('oracle', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mysql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mongodb', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: model.db_model_source_url_supported_scheme
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_model_source_url_supported_scheme
(
    id            bigserial                         NOT NULL,
    scheme        text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbModelSourceUrlSupportedSchemePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dbModelSourceUrlSupportedSchemeUniqueConstraint" UNIQUE (scheme)
        INCLUDE (scheme)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_model_source_url_supported_scheme
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_model_source_url_supported_scheme
    IS 'Stores API-supported values for a Database Model Source Url''s Scheme field';

COMMENT ON CONSTRAINT "dbModelSourceUrlSupportedSchemeUniqueConstraint" ON model.db_model_source_url_supported_scheme
    IS 'Ensures that the Scheme value is unique among all Database Model Source Supported Schemes';

INSERT INTO model.db_model_source_url_supported_scheme (scheme, created_uid, created_date, modified_uid, modified_date)
VALUES ('jdbc', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: model.db_model_source_preset
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_model_source_preset
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    scheme        text COLLATE pg_catalog."default",
    provider      text COLLATE pg_catalog."default",
    driver_name   text COLLATE pg_catalog."default",
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbModelSourcePresetPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dbModelSourcePresetNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name),
    CONSTRAINT "dbModelSourcePresetDriverNameFKConstraint" FOREIGN KEY (driver_name)
        REFERENCES model.db_model_source_config_supported_driver (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "dbModelSourcePresetProviderFKConstraint" FOREIGN KEY (provider)
        REFERENCES model.db_model_source_url_supported_provider (provider) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "dbModelSourcePresetSchemeFKConstraint" FOREIGN KEY (scheme)
        REFERENCES model.db_model_source_url_supported_scheme (scheme) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_model_source_preset
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_model_source_preset
    IS 'Stores preset combinations of values that work together when creating Database Model Source records';

COMMENT ON CONSTRAINT "dbModelSourcePresetNameUniqueConstraint" ON model.db_model_source_preset
    IS 'Ensures that the Name value is unique among all Database Model Source Preset records';

COMMENT ON CONSTRAINT "dbModelSourcePresetDriverNameFKConstraint" ON model.db_model_source_preset
    IS 'Links the Driver Name field with a value from the Database Model Source Supported Driver table';
COMMENT ON CONSTRAINT "dbModelSourcePresetProviderFKConstraint" ON model.db_model_source_preset
    IS 'Links the Provider field with a value from the Database Model Source URL Supported Provider table';
COMMENT ON CONSTRAINT "dbModelSourcePresetSchemeFKConstraint" ON model.db_model_source_preset
    IS 'Links the Scheme field with a value from the Database Model Source URL Supported Scheme table';

INSERT INTO model.db_model_source_preset (name, scheme, provider, driver_name, created_uid, created_date, modified_uid,
                                          modified_date)
VALUES ('postgresql', 'jdbc', 'postgresql', 'org.postgresql.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('oracle', 'jdbc', 'oracle', 'oracle.jdbc.OracleDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mysql', 'jdbc', 'mysql', 'com.mysql.cj.jdbc.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mongodb', 'jdbc', 'mongodb', 'mongodb.jdbc.MongoDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: model.db_model_source_url
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_model_source_url
(
    id                        bigserial                         NOT NULL,
    scheme                    text COLLATE pg_catalog."default" NOT NULL,
    provider                  text COLLATE pg_catalog."default" NOT NULL,
    hostname                  text COLLATE pg_catalog."default" NOT NULL,
    port                      text COLLATE pg_catalog."default" NOT NULL,
    maintenance_database_name text COLLATE pg_catalog."default" NOT NULL,
    created_uid               text COLLATE pg_catalog."default" NOT NULL,
    created_date              date                              NOT NULL,
    modified_uid              text COLLATE pg_catalog."default" NOT NULL,
    modified_date             date                              NOT NULL,
    CONSTRAINT "dbModelSourceUrlPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "allUrlComponentsUniqueConstraint" UNIQUE (scheme, provider, hostname, port, maintenance_database_name)
        INCLUDE (scheme, provider, hostname, port, maintenance_database_name),
    CONSTRAINT "dbModelSourceUrlSupportedProviderFKConstraint" FOREIGN KEY (provider)
        REFERENCES model.db_model_source_url_supported_provider (provider) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "dbModelSourceUrlSupportedSchemeFKConstraint" FOREIGN KEY (scheme)
        REFERENCES model.db_model_source_url_supported_scheme (scheme) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_model_source_url
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_model_source_url
    IS 'Represents a URL that can be used to connect to a Database to obtain Model information';

COMMENT ON CONSTRAINT "allUrlComponentsUniqueConstraint" ON model.db_model_source_url
    IS 'Ensures that the various components of the URL are unique when combined';

COMMENT ON CONSTRAINT "dbModelSourceUrlSupportedProviderFKConstraint" ON model.db_model_source_url
    IS 'Links the Provider field with a value from the Database Model Source URL Supported Provider table';
COMMENT ON CONSTRAINT "dbModelSourceUrlSupportedSchemeFKConstraint" ON model.db_model_source_url
    IS 'Links the Scheme field with a value from the Database Model Source URL Supported Scheme table';

---------------------------------------------------------------------------------
-- Table: model.db_model_source_config
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_model_source_config
(
    id            bigserial                         NOT NULL,
    source_url_id bigint                            NOT NULL,
    driver_name   text COLLATE pg_catalog."default" NOT NULL,
    username      text COLLATE pg_catalog."default" NOT NULL,
    password      text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbModelSourceConfigPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "allSourceComponentsUniqueConstraint" UNIQUE (source_url_id, driver_name, username, password)
        INCLUDE (source_url_id, driver_name, username, password),
    CONSTRAINT "dbModelSourceConfigSupportedDriverFKConstraint" FOREIGN KEY (driver_name)
        REFERENCES model.db_model_source_config_supported_driver (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sourceUrlFKConstraint" FOREIGN KEY (source_url_id)
        REFERENCES model.db_model_source_url (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_model_source_config
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_model_source_config
    IS 'Represents the configuration details for a connection to a Database to obtain Model data';

COMMENT ON CONSTRAINT "allSourceComponentsUniqueConstraint" ON model.db_model_source_config
    IS 'Ensures that the various components that makeup the Database Model Source Config are unique when combined';

COMMENT ON CONSTRAINT "dbModelSourceConfigSupportedDriverFKConstraint" ON model.db_model_source_config
    IS 'Links the Driver Name field with a value from the Database Model Source Config Supported Driver table';
COMMENT ON CONSTRAINT "sourceUrlFKConstraint" ON model.db_model_source_config
    IS 'Links this Database Model Source Config with a Database Model Source URL';

---------------------------------------------------------------------------------
-- Table: model.db_model
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_model
(
    id               bigserial                         NOT NULL,
    source_config_id bigint                            NOT NULL,
    database_name    text COLLATE pg_catalog."default" NOT NULL,
    schema_name      text COLLATE pg_catalog."default" NOT NULL,
    description      text COLLATE pg_catalog."default",
    created_uid      text COLLATE pg_catalog."default" NOT NULL,
    created_date     date                              NOT NULL,
    modified_uid     text COLLATE pg_catalog."default" NOT NULL,
    modified_date    date                              NOT NULL,
    CONSTRAINT "dbModelPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dbModelComponentsUniqueConstraint" UNIQUE (database_name, schema_name)
        INCLUDE (source_config_id, database_name, schema_name),
    CONSTRAINT "sourceConfigFKConstraint" FOREIGN KEY (source_config_id)
        REFERENCES model.db_model_source_config (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_model
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_model
    IS 'Stores metadata describing the structure and contents of a database';

COMMENT ON CONSTRAINT "dbModelComponentsUniqueConstraint" ON model.db_model
    IS 'Ensures that the combination of Database Name, Schema Name, and Database Model Source are unique among all Database Models';

COMMENT ON CONSTRAINT "sourceConfigFKConstraint" ON model.db_model
    IS 'Links this Database Model with a Database Model Source Config';

---------------------------------------------------------------------------------
-- Table: model.db_data_type
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_data_type
(
    id                bigserial                         NOT NULL,
    name              text COLLATE pg_catalog."default" NOT NULL,
    java_sql_constant numeric                           NOT NULL,
    created_uid       text COLLATE pg_catalog."default" NOT NULL,
    created_date      date                              NOT NULL,
    modified_uid      text COLLATE pg_catalog."default" NOT NULL,
    modified_date     date                              NOT NULL,
    CONSTRAINT "dbDataTypePKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_data_type
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_data_type
    IS 'Represents the various types of data that the can be stored within the database being modeled';

---------------------------------------------------------------------------------
-- Table: model.db_table_category
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_table_category
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbTableCategoryPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_table_category
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_table_category
    IS 'A Category that can be assigned to a Table that defines how the Table''s data relates to overall domain, such as an Entity Table that stores the major entity Objects such as a Table named Product storing the products for a retail store';

INSERT INTO model.db_table_category (name, created_uid, created_date, modified_uid, modified_date)
VALUES ('Entity', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Lookup', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Xref', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Meta', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Temporary', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('History', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('User', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Application', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Staging', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Audit', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Undefined', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: model.db_table_model
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_table_model
(
    id                bigserial                         NOT NULL,
    database_id       bigint                            NOT NULL,
    table_category_id bigint                            NOT NULL,
    name              text COLLATE pg_catalog."default" NOT NULL,
    description       text COLLATE pg_catalog."default",
    created_uid       text COLLATE pg_catalog."default" NOT NULL,
    created_date      date                              NOT NULL,
    modified_uid      text COLLATE pg_catalog."default" NOT NULL,
    modified_date     date                              NOT NULL,
    CONSTRAINT "dbTableModelPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dbFKConstraint" FOREIGN KEY (database_id)
        REFERENCES model.db_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "tableCategoryFKConstraint" FOREIGN KEY (table_category_id)
        REFERENCES model.db_table_category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_table_model
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_table_model
    IS 'Stores metadata describing the structure and contents of a database table';

COMMENT ON CONSTRAINT "dbFKConstraint" ON model.db_table_model
    IS 'Links this Table Model to a Database Model';
COMMENT ON CONSTRAINT "tableCategoryFKConstraint" ON model.db_table_model
    IS 'Links this Table Model to a Table Category';

---------------------------------------------------------------------------------
-- Table: model.db_column_category
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_column_category
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbColumnModelCategoryPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_column_category
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_column_category
    IS 'A Category that can be assigned to a Column that defines how the Column''s data relates to its database table, such as an Identity Column relates the Identity of the Table';

INSERT INTO model.db_column_category(name, created_uid, created_date, modified_uid, modified_date)
VALUES ('Identity', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Domain', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Audit', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Undefined', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: model.db_column_model
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_column_model
(
    id                 bigserial                         NOT NULL,
    table_id           bigint                            NOT NULL,
    data_type_id       bigint                            NOT NULL,
    column_category_id bigint                            NOT NULL,
    name               text COLLATE pg_catalog."default" NOT NULL,
    description        text COLLATE pg_catalog."default",
    is_nullable        boolean                           NOT NULL,
    is_auto_increment  boolean                           NOT NULL,
    column_index       numeric                           NOT NULL,
    created_uid        text COLLATE pg_catalog."default" NOT NULL,
    created_date       date                              NOT NULL,
    modified_uid       text COLLATE pg_catalog."default" NOT NULL,
    modified_date      date                              NOT NULL,
    CONSTRAINT "databaseModelColumnPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "tableAndColumnIndexUniqueConstraint" UNIQUE (table_id, column_index)
        INCLUDE (table_id, column_index),
    CONSTRAINT "columnCategoryFKConstraint" FOREIGN KEY (column_category_id)
        REFERENCES model.db_column_category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "dataTypeFKConstraint" FOREIGN KEY (data_type_id)
        REFERENCES model.db_data_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "tableModelFKConstraint" FOREIGN KEY (table_id)
        REFERENCES model.db_table_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_column_model
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_column_model
    IS 'Stores metadata describing the structure and contents of a database column';

COMMENT ON CONSTRAINT "tableAndColumnIndexUniqueConstraint" ON model.db_column_model
    IS 'Ensures that within any given Table, all the Column Indices are unique';

COMMENT ON CONSTRAINT "columnCategoryFKConstraint" ON model.db_column_model
    IS 'Links this Column Model to a Column Category';
COMMENT ON CONSTRAINT "dataTypeFKConstraint" ON model.db_column_model
    IS 'Links this Column Model to a Data Type';
COMMENT ON CONSTRAINT "tableModelFKConstraint" ON model.db_column_model
    IS 'Links this Column Model to a Table Model';

---------------------------------------------------------------------------------
-- Table: model.db_primary_key_model
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_primary_key_model
(
    id            bigserial                         NOT NULL,
    table_id      bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    description   text COLLATE pg_catalog."default",
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "dbPrimaryKeyModelPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "tableModelFKConstraint" FOREIGN KEY (table_id)
        REFERENCES model.db_table_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_primary_key_model
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_primary_key_model
    IS 'Holds metadata about a database table''s primary key constraint';

COMMENT ON CONSTRAINT "tableModelFKConstraint" ON model.db_primary_key_model
    IS 'Links this Primary Key Constraint to a Table Model';

---------------------------------------------------------------------------------
-- Table: model.db_primary_key_column_model
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_primary_key_column_model
(
    id             bigserial                         NOT NULL,
    primary_key_id bigint                            NOT NULL,
    column_id      bigint                            NOT NULL,
    created_uid    text COLLATE pg_catalog."default" NOT NULL,
    created_date   date                              NOT NULL,
    modified_uid   text COLLATE pg_catalog."default" NOT NULL,
    modified_date  date                              NOT NULL,
    CONSTRAINT "primaryKeyColumnModelPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "columnFKConstraint" FOREIGN KEY (column_id)
        REFERENCES model.db_column_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "primaryKeyFKConstraint" FOREIGN KEY (primary_key_id)
        REFERENCES model.db_primary_key_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_primary_key_column_model
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_primary_key_column_model
    IS 'Maps a Column Model to a Primary Key Constraint Model';

COMMENT ON CONSTRAINT "columnFKConstraint" ON model.db_primary_key_column_model
    IS 'Links this Primary Key Column to a Column Model';
COMMENT ON CONSTRAINT "primaryKeyFKConstraint" ON model.db_primary_key_column_model
    IS 'Links this Primary Key Column to a Primary Key Model';

---------------------------------------------------------------------------------
-- Table: model.db_foreign_key_model
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS model.db_foreign_key_model
(
    id                   bigserial                         NOT NULL,
    table_id             bigint                            NOT NULL,
    local_column_id      bigint                            NOT NULL,
    referenced_column_id bigint                            NOT NULL,
    referenced_table_id  bigint                            NOT NULL,
    name                 text COLLATE pg_catalog."default" NOT NULL,
    description          text COLLATE pg_catalog."default",
    created_uid          text COLLATE pg_catalog."default" NOT NULL,
    created_date         date                              NOT NULL,
    modified_uid         text COLLATE pg_catalog."default" NOT NULL,
    modified_date        date                              NOT NULL,
    CONSTRAINT "dbModelForeignKeyPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "localColumnFKConstraint" FOREIGN KEY (local_column_id)
        REFERENCES model.db_column_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "referencedColumnFKConstraint" FOREIGN KEY (referenced_column_id)
        REFERENCES model.db_column_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "referencedTableFKConstraint" FOREIGN KEY (referenced_table_id)
        REFERENCES model.db_table_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "tableFKConstraint" FOREIGN KEY (table_id)
        REFERENCES model.db_table_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "localColumnNotEqualToReferencedColumn" CHECK (local_column_id <> referenced_column_id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS model.db_foreign_key_model
    OWNER to postgresadmin;

COMMENT ON TABLE model.db_foreign_key_model
    IS 'Holds metadata about a database table''s foreign key constraints';

COMMENT ON CONSTRAINT "localColumnFKConstraint" ON model.db_foreign_key_model
    IS 'Links this Foreign Key Constraint to the Column Model for which it the Foreign Key is applied';
COMMENT ON CONSTRAINT "referencedColumnFKConstraint" ON model.db_foreign_key_model
    IS 'Links this Foreign Key Constraint to the Column Model for which it the Foreign Key references';
COMMENT ON CONSTRAINT "referencedTableFKConstraint" ON model.db_foreign_key_model
    IS 'Links this Foreign Key Model to the Table it references';
COMMENT ON CONSTRAINT "tableFKConstraint" ON model.db_foreign_key_model
    IS 'Links this Foreign Key Constraint to a Table Model';

COMMENT ON CONSTRAINT "localColumnNotEqualToReferencedColumn" ON model.db_foreign_key_model
    IS 'Ensures that the Local Column is not the same as the Referenced Column';