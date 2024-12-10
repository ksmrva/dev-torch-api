-- ******************************************************************************
---------------------------------------------------------------------------------
-- Source
---------------------------------------------------------------------------------
-- ******************************************************************************

DROP TABLE IF EXISTS db_model_source.data_type;
DROP TABLE IF EXISTS db_model_source.config;
DROP TABLE IF EXISTS db_model_source.url;
DROP TABLE IF EXISTS db_model_source.preset;
DROP TABLE IF EXISTS db_model_source.url_supported_provider;
DROP TABLE IF EXISTS db_model_source.url_supported_scheme;
DROP TABLE IF EXISTS db_model_source.config_supported_driver;

DROP SCHEMA IF EXISTS db_model_source;

---------------------------------------------------------------------------------
-- Schema: db_model_source
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS db_model_source
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA db_model_source
    IS 'Entities used to define the configuration details of a Source that can be used to create Components for a Database Model';

---------------------------------------------------------------------------------
-- Table: db_model_source.config_supported_driver
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_source.config_supported_driver
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sourceConfigSupportedDriverPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceConfigSupportedDriverNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_source.config_supported_driver
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_source.config_supported_driver
    IS 'Stores information about the Drivers which are supported within any Database Model Source Config';

COMMENT ON CONSTRAINT "sourceConfigSupportedDriverNameUniqueConstraint" ON db_model_source.config_supported_driver
    IS 'Ensures that the Name value is unique among all Database Model Source Config Supported Drivers';

INSERT INTO db_model_source.config_supported_driver (name, created_uid, created_date, modified_uid, modified_date)
VALUES ('org.postgresql.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('oracle.jdbc.OracleDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('com.mysql.cj.jdbc.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mongodb.jdbc.MongoDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: db_model_source.url_supported_provider
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_source.url_supported_provider
(
    id            bigserial                         NOT NULL,
    provider      text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sourceUrlSupportedProviderPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceUrlSupportedProviderUniqueConstraint" UNIQUE (provider)
        INCLUDE (provider)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_source.url_supported_provider
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_source.url_supported_provider
    IS 'Stores information about the Providers which are supported within any Database Model Source URL';

COMMENT ON CONSTRAINT "sourceUrlSupportedProviderUniqueConstraint" ON db_model_source.url_supported_provider
    IS 'Ensures that the Provider value is unique among all Database Model Source URL Supported Providers';

INSERT INTO db_model_source.url_supported_provider (provider, created_uid, created_date, modified_uid, modified_date)
VALUES ('postgresql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('oracle', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mysql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mongodb', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: db_model_source.url_supported_scheme
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_source.url_supported_scheme
(
    id            bigserial                         NOT NULL,
    scheme        text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sourceUrlSupportedSchemePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceUrlSupportedSchemeUniqueConstraint" UNIQUE (scheme)
        INCLUDE (scheme)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_source.url_supported_scheme
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_source.url_supported_scheme
    IS 'Stores information about the Schemes which are supported within any Database Model Source URL';

COMMENT ON CONSTRAINT "sourceUrlSupportedSchemeUniqueConstraint" ON db_model_source.url_supported_scheme
    IS 'Ensures that the Scheme value is unique among all Database Model Source Supported Schemes';

INSERT INTO db_model_source.url_supported_scheme (scheme, created_uid, created_date, modified_uid, modified_date)
VALUES ('jdbc', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: db_model_source.preset
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_source.preset
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
    CONSTRAINT "sourcePresetPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourcePresetNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name),
    CONSTRAINT "sourcePresetDriverNameFKConstraint" FOREIGN KEY (driver_name)
        REFERENCES db_model_source.config_supported_driver (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sourcePresetProviderFKConstraint" FOREIGN KEY (provider)
        REFERENCES db_model_source.url_supported_provider (provider) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sourcePresetSchemeFKConstraint" FOREIGN KEY (scheme)
        REFERENCES db_model_source.url_supported_scheme (scheme) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_source.preset
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_source.preset
    IS 'Stores preset combinations of values that work together when creating Database Model Source records';

COMMENT ON CONSTRAINT "sourcePresetNameUniqueConstraint" ON db_model_source.preset
    IS 'Ensures that the Name value is unique among all Database Model Source Preset records';

COMMENT ON CONSTRAINT "sourcePresetDriverNameFKConstraint" ON db_model_source.preset
    IS 'Links the Driver Name field with a value from the Database Model Source Supported Driver table';
COMMENT ON CONSTRAINT "sourcePresetProviderFKConstraint" ON db_model_source.preset
    IS 'Links the Provider field with a value from the Database Model Source URL Supported Provider table';
COMMENT ON CONSTRAINT "sourcePresetSchemeFKConstraint" ON db_model_source.preset
    IS 'Links the Scheme field with a value from the Database Model Source URL Supported Scheme table';

INSERT INTO db_model_source.preset (name, scheme, provider, driver_name, created_uid, created_date, modified_uid, modified_date)
VALUES ('postgresql', 'jdbc', 'postgresql', 'org.postgresql.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('oracle', 'jdbc', 'oracle', 'oracle.jdbc.OracleDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mysql', 'jdbc', 'mysql', 'com.mysql.cj.jdbc.Driver', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mongodb', 'jdbc', 'mongodb', 'mongodb.jdbc.MongoDriver', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: db_model_source.url
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_source.url
(
    id            bigserial                         NOT NULL,
    scheme        text COLLATE pg_catalog."default" NOT NULL,
    provider      text COLLATE pg_catalog."default" NOT NULL,
    hostname      text COLLATE pg_catalog."default" NOT NULL,
    port          text COLLATE pg_catalog."default" NOT NULL,
    admin_db_name text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sourceUrlPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "allUrlComponentsUniqueConstraint" UNIQUE (scheme, provider, hostname, port, admin_db_name)
        INCLUDE (scheme, provider, hostname, port, admin_db_name),
    CONSTRAINT "sourceUrlSupportedProviderFKConstraint" FOREIGN KEY (provider)
        REFERENCES db_model_source.url_supported_provider (provider) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sourceUrlSupportedSchemeFKConstraint" FOREIGN KEY (scheme)
        REFERENCES db_model_source.url_supported_scheme (scheme) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_source.url
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_source.url
    IS 'Represents a URL that can be used to connect to a Database to obtain Model information';

COMMENT ON CONSTRAINT "allUrlComponentsUniqueConstraint" ON db_model_source.url
    IS 'Ensures that the various components of the URL are unique when combined';

COMMENT ON CONSTRAINT "sourceUrlSupportedProviderFKConstraint" ON db_model_source.url
    IS 'Links the Provider field with a value from the Database Model Source URL Supported Provider table';
COMMENT ON CONSTRAINT "sourceUrlSupportedSchemeFKConstraint" ON db_model_source.url
    IS 'Links the Scheme field with a value from the Database Model Source URL Supported Scheme table';

---------------------------------------------------------------------------------
-- Table: db_model_source.config
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_source.config
(
    id            bigserial                         NOT NULL,
    url_id        bigint                            NOT NULL,
    driver_name   text COLLATE pg_catalog."default" NOT NULL,
    username      text COLLATE pg_catalog."default" NOT NULL,
    password      text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sourceConfigPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "allSourceComponentsUniqueConstraint" UNIQUE (url_id, driver_name, username, password)
        INCLUDE (url_id, driver_name, username, password),
    CONSTRAINT "sourceConfigSupportedDriverFKConstraint" FOREIGN KEY (driver_name)
        REFERENCES db_model_source.config_supported_driver (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sourceUrlFKConstraint" FOREIGN KEY (url_id)
        REFERENCES db_model_source.url (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_source.config
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_source.config
    IS 'Represents the configuration details for a connection to a Database to obtain Model data';

COMMENT ON CONSTRAINT "allSourceComponentsUniqueConstraint" ON db_model_source.config
    IS 'Ensures that the various components that makeup the Database Model Source Config are unique when combined';

COMMENT ON CONSTRAINT "sourceConfigSupportedDriverFKConstraint" ON db_model_source.config
    IS 'Links the Driver Name field with a value from the Database Model Source Config Supported Driver table';
COMMENT ON CONSTRAINT "sourceUrlFKConstraint" ON db_model_source.config
    IS 'Links this Database Model Source Config with a Database Model Source URL';

---------------------------------------------------------------------------------
-- Table: db_model_source.data_type
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_source.data_type
(
    id                bigserial                         NOT NULL,
    name              text COLLATE pg_catalog."default" NOT NULL,
    java_sql_constant numeric                           NOT NULL,
    created_uid       text COLLATE pg_catalog."default" NOT NULL,
    created_date      date                              NOT NULL,
    modified_uid      text COLLATE pg_catalog."default" NOT NULL,
    modified_date     date                              NOT NULL,
    CONSTRAINT "dataTypePKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_source.data_type
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_source.data_type
    IS 'Stores information about the various types of Data that the can be stored within any given Database';

-- ******************************************************************************
---------------------------------------------------------------------------------
-- Component
---------------------------------------------------------------------------------
-- ******************************************************************************

DROP TABLE IF EXISTS db_model_component.foreign_key;
DROP TABLE IF EXISTS db_model_component.primary_key_column;
DROP TABLE IF EXISTS db_model_component.primary_key;
DROP TABLE IF EXISTS db_model_component.column;
DROP TABLE IF EXISTS db_model_component.column_category;
DROP TABLE IF EXISTS db_model_component.table;
DROP TABLE IF EXISTS db_model_component.table_category;
DROP TABLE IF EXISTS db_model_component.database;

DROP SCHEMA IF EXISTS db_model_component;

---------------------------------------------------------------------------------
-- Schema: db_model_component
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS db_model_component
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA db_model_component
    IS 'Entities used to describe the Components of a Database Model used to construct Documentation and Diagrams';

---------------------------------------------------------------------------------
-- Table: db_model_component.database
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.database
(
    id               bigserial                         NOT NULL,
    source_config_id bigint                            NOT NULL,
    name             text COLLATE pg_catalog."default" NOT NULL,
    schema_name      text COLLATE pg_catalog."default" NOT NULL,
    description      text COLLATE pg_catalog."default",
    created_uid      text COLLATE pg_catalog."default" NOT NULL,
    created_date     date                              NOT NULL,
    modified_uid     text COLLATE pg_catalog."default" NOT NULL,
    modified_date    date                              NOT NULL,
    CONSTRAINT "dbComponentPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "dbComponentDbFieldsUniqueConstraint" UNIQUE (name, schema_name)
        INCLUDE (source_config_id, name, schema_name),
    CONSTRAINT "sourceConfigFKConstraint" FOREIGN KEY (source_config_id)
        REFERENCES db_model_source.config (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.database
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.database
    IS 'Stores metadata describing the structure and contents of a Database';

COMMENT ON CONSTRAINT "dbComponentDbFieldsUniqueConstraint" ON db_model_component.database
    IS 'Ensures that the combination of Database Name, Schema Name, and Database Model Source Config are unique among all Databases';

COMMENT ON CONSTRAINT "sourceConfigFKConstraint" ON db_model_component.database
    IS 'Links this Database Model with a Database Model Source Config';

---------------------------------------------------------------------------------
-- Table: db_model_component.table_category
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.table_category
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "tableCategoryPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.table_category
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.table_category
    IS 'A Category that can be assigned to a Table that defines how the Table''s data relates to overall Domain, such as an Entity Table that stores the major entity Objects such as a Table named Product storing the products for a retail store';

INSERT INTO db_model_component.table_category (name, created_uid, created_date, modified_uid, modified_date)
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
-- Table: db_model_component.table
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.table
(
    id            bigserial                         NOT NULL,
    database_id   bigint                            NOT NULL,
    category_id   bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    description   text COLLATE pg_catalog."default",
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "tableComponentPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "tableComponentUnderlyingDatabaseFKConstraint" FOREIGN KEY (database_id)
        REFERENCES db_model_component.database (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "tableCategoryFKConstraint" FOREIGN KEY (category_id)
        REFERENCES db_model_component.table_category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.table
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.table
    IS 'Stores metadata describing the structure and contents of a Database Table';

COMMENT ON CONSTRAINT "tableComponentUnderlyingDatabaseFKConstraint" ON db_model_component.table
    IS 'Links this Table Component to a Database Component';
COMMENT ON CONSTRAINT "tableCategoryFKConstraint" ON db_model_component.table
    IS 'Links this Table Component to a Table Category';

---------------------------------------------------------------------------------
-- Table: db_model_component.column_category
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.column_category
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "columnCategoryPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.column_category
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.column_category
    IS 'A Category that can be assigned to a Column that defines how the Column''s data relates to its Database Table, such as an Identity Column relates the Identity of the Table';

INSERT INTO db_model_component.column_category(name, created_uid, created_date, modified_uid, modified_date)
VALUES ('Identity', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Domain', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Audit', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Undefined', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: db_model_component.column
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.column
(
    id                bigserial                         NOT NULL,
    table_id          bigint                            NOT NULL,
    data_type_id      bigint                            NOT NULL,
    category_id       bigint                            NOT NULL,
    name              text COLLATE pg_catalog."default" NOT NULL,
    description       text COLLATE pg_catalog."default",
    is_nullable       boolean                           NOT NULL,
    is_auto_increment boolean                           NOT NULL,
    column_index      numeric                           NOT NULL,
    created_uid       text COLLATE pg_catalog."default" NOT NULL,
    created_date      date                              NOT NULL,
    modified_uid      text COLLATE pg_catalog."default" NOT NULL,
    modified_date     date                              NOT NULL,
    CONSTRAINT "columnComponentPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "tableAndColumnIndexUniqueConstraint" UNIQUE (table_id, column_index)
        INCLUDE (table_id, column_index),
    CONSTRAINT "columnCategoryFKConstraint" FOREIGN KEY (category_id)
        REFERENCES db_model_component.column_category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "dataTypeFKConstraint" FOREIGN KEY (data_type_id)
        REFERENCES db_model_source.data_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "columnComponentUnderlyingTableFKConstraint" FOREIGN KEY (table_id)
        REFERENCES db_model_component.table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.column
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.column
    IS 'Stores metadata describing the structure and contents of a Database Column';

COMMENT ON CONSTRAINT "tableAndColumnIndexUniqueConstraint" ON db_model_component.column
    IS 'Ensures that within any given Table, all the Column Indices are unique';

COMMENT ON CONSTRAINT "columnCategoryFKConstraint" ON db_model_component.column
    IS 'Links this Column Component to a Column Category';
COMMENT ON CONSTRAINT "dataTypeFKConstraint" ON db_model_component.column
    IS 'Links this Column Component to a Data Type';
COMMENT ON CONSTRAINT "columnComponentUnderlyingTableFKConstraint" ON db_model_component.column
    IS 'Links this Column Component to its underlying Table Component';

---------------------------------------------------------------------------------
-- Table: db_model_component.primary_key
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.primary_key
(
    id            bigserial                         NOT NULL,
    table_id      bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    description   text COLLATE pg_catalog."default",
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "primaryKeyComponentPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "primaryKeyUnderlyingTableFKConstraint" FOREIGN KEY (table_id)
        REFERENCES db_model_component.table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.primary_key
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.primary_key
    IS 'Holds metadata about a Database Table''s Primary Key Constraint';

COMMENT ON CONSTRAINT "primaryKeyUnderlyingTableFKConstraint" ON db_model_component.primary_key
    IS 'Links this Primary Key Constraint to a Table Component';

---------------------------------------------------------------------------------
-- Table: db_model_component.primary_key_column
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.primary_key_column
(
    id             bigserial                         NOT NULL,
    primary_key_id bigint                            NOT NULL,
    column_id      bigint                            NOT NULL,
    created_uid    text COLLATE pg_catalog."default" NOT NULL,
    created_date   date                              NOT NULL,
    modified_uid   text COLLATE pg_catalog."default" NOT NULL,
    modified_date  date                              NOT NULL,
    CONSTRAINT "primaryKeyColumnComponentPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "primaryKeyColumnUnderlyingColumnFKConstraint" FOREIGN KEY (column_id)
        REFERENCES db_model_component.column (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "primaryKeyColumnUnderlyingPrimaryKeyFKConstraint" FOREIGN KEY (primary_key_id)
        REFERENCES db_model_component.primary_key (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.primary_key_column_model
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.primary_key_column
    IS 'Maps a Column Component to a Primary Key Constraint Component';

COMMENT ON CONSTRAINT "primaryKeyColumnUnderlyingColumnFKConstraint" ON db_model_component.primary_key_column
    IS 'Links this Primary Key Component to a Column Component';
COMMENT ON CONSTRAINT "primaryKeyColumnUnderlyingPrimaryKeyFKConstraint" ON db_model_component.primary_key_column
    IS 'Links this Primary Key Component to a Primary Key Component';

---------------------------------------------------------------------------------
-- Table: db_model_component.foreign_key
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_component.foreign_key
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
    CONSTRAINT "foreignKeyComponentPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "localColumnFKConstraint" FOREIGN KEY (local_column_id)
        REFERENCES db_model_component.column (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "referencedColumnFKConstraint" FOREIGN KEY (referenced_column_id)
        REFERENCES db_model_component.column (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "referencedTableFKConstraint" FOREIGN KEY (referenced_table_id)
        REFERENCES db_model_component.table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "foreignKeyComponentUnderlyingTableFKConstraint" FOREIGN KEY (table_id)
        REFERENCES db_model_component.table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "localColumnNotEqualToReferencedColumnUniqueConstraint" CHECK (local_column_id <> referenced_column_id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_component.foreign_key
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_component.foreign_key
    IS 'Holds metadata about a Database Table''s Foreign Key Constraints';

COMMENT ON CONSTRAINT "localColumnFKConstraint" ON db_model_component.foreign_key
    IS 'Links this Foreign Key Constraint to the Column Component for which the Foreign Key is applied';
COMMENT ON CONSTRAINT "referencedColumnFKConstraint" ON db_model_component.foreign_key
    IS 'Links this Foreign Key Constraint to the Column Component for which the Foreign Key references';
COMMENT ON CONSTRAINT "referencedTableFKConstraint" ON db_model_component.foreign_key
    IS 'Links this Foreign Key Component to the Table it references';
COMMENT ON CONSTRAINT "foreignKeyComponentUnderlyingTableFKConstraint" ON db_model_component.foreign_key
    IS 'Links this Foreign Key Constraint to a Table Component';

COMMENT ON CONSTRAINT "localColumnNotEqualToReferencedColumnUniqueConstraint" ON db_model_component.foreign_key
    IS 'Ensures that the Local Column is not the same as the Referenced Column';