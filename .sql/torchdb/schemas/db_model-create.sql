DROP TABLE IF EXISTS db_model_detail.sql_foreign_key;
DROP TABLE IF EXISTS db_model_detail.sql_primary_key_column;
DROP TABLE IF EXISTS db_model_detail.sql_primary_key;
DROP TABLE IF EXISTS db_model_detail.sql_column;
DROP TABLE IF EXISTS db_model_detail.sql_table;
DROP TABLE IF EXISTS db_model_detail.sql_database;
DROP TABLE IF EXISTS db_model_detail.field_category;
DROP TABLE IF EXISTS db_model_detail.collection_category;

DROP SCHEMA IF EXISTS db_model_detail;

DROP TABLE IF EXISTS db_model_source.data_type;
DROP TABLE IF EXISTS db_model_source.config;
DROP TABLE IF EXISTS db_model_source.url;
DROP TABLE IF EXISTS db_model_source.preset;
DROP TABLE IF EXISTS db_model_source.url_supported_provider;
DROP TABLE IF EXISTS db_model_source.url_supported_scheme;
DROP TABLE IF EXISTS db_model_source.config_supported_driver;

DROP SCHEMA IF EXISTS db_model_source;

-- <editor-fold desc="Source">
-- ******************************************************************************
---------------------------------------------------------------------------------
-- Source
---------------------------------------------------------------------------------
-- ******************************************************************************

---------------------------------------------------------------------------------
-- Schema: db_model_source
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS db_model_source
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA db_model_source
    IS 'Entities used to define the configuration details of a Source that can be used to create Details for a Database Model';

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
    IS 'Stores preset combinations of values that work together when creating Database Model Source entries';

COMMENT ON CONSTRAINT "sourcePresetNameUniqueConstraint" ON db_model_source.preset
    IS 'Ensures that the Name value is unique among all Database Model Source Preset entries';

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
-- </editor-fold>

-- <editor-fold desc="Detail">
-- ******************************************************************************
---------------------------------------------------------------------------------
-- Detail
---------------------------------------------------------------------------------
-- ******************************************************************************

---------------------------------------------------------------------------------
-- Schema: db_model_detail
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS db_model_detail
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA db_model_detail
    IS 'Entities used to describe the Details of a Database Model used to construct Documentation and Diagrams';

---------------------------------------------------------------------------------
-- Table: db_model_detail.collection_category
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.collection_category
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "collectionCategoryPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.collection_category
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.collection_category
    IS 'A Category that can be assigned to an Database Collection (such as a Table for SQL or a Collection of Documents for NoSQL) that defines how its data relates to the overall Domain; For instance, an Entity Table corresponds with core business objects, such as a Product or Purchase object for a retail website';

INSERT INTO db_model_detail.collection_category (name, created_uid, created_date, modified_uid, modified_date)
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
       ('Misc', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Various', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Undefined', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: db_model_detail.field_category
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.field_category
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "fieldCategoryPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.field_category
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.field_category
    IS 'A Category that can be assigned to an Database Field (such as a Column for SQL or a Field for NoSQL) that defines how its data relates to its containing Collection; For instance, the SQL Column used in a Primary Key serves as the identifying Field for a Table';

INSERT INTO db_model_detail.field_category(name, created_uid, created_date, modified_uid, modified_date)
VALUES ('Identity', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Domain', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Audit', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('Undefined', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

-- <editor-fold desc="Detail-SQL">
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
-- Detail-SQL
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------

---------------------------------------------------------------------------------
-- Table: db_model_detail.sql_database
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.sql_database
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
    CONSTRAINT "sqlDbDetailPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sqlDbPathUniqueConstraint" UNIQUE (name, schema_name)
        INCLUDE (source_config_id, name, schema_name),
    CONSTRAINT "sqlDbSourceConfigFKConstraint" FOREIGN KEY (source_config_id)
        REFERENCES db_model_source.config (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.sql_database
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.sql_database
    IS 'Stores metadata describing the structure of an SQL Database';

COMMENT ON CONSTRAINT "sqlDbPathUniqueConstraint" ON db_model_detail.sql_database
    IS 'Ensures that the combination of Database Name, Schema Name, and Database Model Source Config (considered here to be the Path to the Database) are unique among all SQL Databases';

COMMENT ON CONSTRAINT "sqlDbSourceConfigFKConstraint" ON db_model_detail.sql_database
    IS 'Links this SQL Database with a Database Model Source Config';

---------------------------------------------------------------------------------
-- Table: db_model_detail.sql_table
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.sql_table
(
    id                     bigserial                         NOT NULL,
    database_id            bigint                            NOT NULL,
    collection_category_id bigint                            NOT NULL,
    name                   text COLLATE pg_catalog."default" NOT NULL,
    description            text COLLATE pg_catalog."default",
    created_uid            text COLLATE pg_catalog."default" NOT NULL,
    created_date           date                              NOT NULL,
    modified_uid           text COLLATE pg_catalog."default" NOT NULL,
    modified_date          date                              NOT NULL,
    CONSTRAINT "sqlTableDetailPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sqlTableUnderlyingDatabaseFKConstraint" FOREIGN KEY (database_id)
        REFERENCES db_model_detail.sql_database (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlTableCollectionCategoryFKConstraint" FOREIGN KEY (collection_category_id)
        REFERENCES db_model_detail.collection_category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.sql_table
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.sql_table
    IS 'Stores metadata describing the structure of an SQL Table';

COMMENT ON CONSTRAINT "sqlTableUnderlyingDatabaseFKConstraint" ON db_model_detail.sql_table
    IS 'Links this SQL Table Detail to the Database that contains the Table';
COMMENT ON CONSTRAINT "sqlTableCollectionCategoryFKConstraint" ON db_model_detail.sql_table
    IS 'Links this SQL Table Detail to a Database Collection Category';

---------------------------------------------------------------------------------
-- Table: db_model_detail.sql_column
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.sql_column
(
    id                bigserial                         NOT NULL,
    table_id          bigint                            NOT NULL,
    data_type_id      bigint                            NOT NULL,
    field_category_id bigint                            NOT NULL,
    name              text COLLATE pg_catalog."default" NOT NULL,
    description       text COLLATE pg_catalog."default",
    is_nullable       boolean                           NOT NULL,
    is_auto_increment boolean                           NOT NULL,
    column_index      numeric                           NOT NULL,
    created_uid       text COLLATE pg_catalog."default" NOT NULL,
    created_date      date                              NOT NULL,
    modified_uid      text COLLATE pg_catalog."default" NOT NULL,
    modified_date     date                              NOT NULL,
    CONSTRAINT "sqlColumnDetailPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sqlColumnUniqueIndexWithinTableConstraint" UNIQUE (table_id, column_index)
        INCLUDE (table_id, column_index),
    CONSTRAINT "sqlColumnFieldCategoryFKConstraint" FOREIGN KEY (field_category_id)
        REFERENCES db_model_detail.field_category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlColumnDataTypeFKConstraint" FOREIGN KEY (data_type_id)
        REFERENCES db_model_source.data_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlColumnUnderlyingTableFKConstraint" FOREIGN KEY (table_id)
        REFERENCES db_model_detail.sql_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.sql_column
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.sql_column
    IS 'Stores metadata describing the structure of an SQL Column';

COMMENT ON CONSTRAINT "sqlColumnUniqueIndexWithinTableConstraint" ON db_model_detail.sql_column
    IS 'Ensures that the declared Index of this SQL Column is unique within its contained Table';

COMMENT ON CONSTRAINT "sqlColumnFieldCategoryFKConstraint" ON db_model_detail.sql_column
    IS 'Links this SQL Column Detail to a Field Category';
COMMENT ON CONSTRAINT "sqlColumnDataTypeFKConstraint" ON db_model_detail.sql_column
    IS 'Links this SQL Column Detail to a Data Type';
COMMENT ON CONSTRAINT "sqlColumnUnderlyingTableFKConstraint" ON db_model_detail.sql_column
    IS 'Links this SQL Column Detail to the Table that contains it';

---------------------------------------------------------------------------------
-- Table: db_model_detail.sql_primary_key
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.sql_primary_key
(
    id            bigserial                         NOT NULL,
    table_id      bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    description   text COLLATE pg_catalog."default",
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sqlPrimaryKeyDetailPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sqlPrimaryKeyUnderlyingTableFKConstraint" FOREIGN KEY (table_id)
        REFERENCES db_model_detail.sql_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.sql_primary_key
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.sql_primary_key
    IS 'Holds metadata about a Primary Key Constraint for an SQL Database Table';

COMMENT ON CONSTRAINT "sqlPrimaryKeyUnderlyingTableFKConstraint" ON db_model_detail.sql_primary_key
    IS 'Links this SQL Primary Key Constraint to the Table that contains it';

---------------------------------------------------------------------------------
-- Table: db_model_detail.sql_primary_key_column
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.sql_primary_key_column
(
    id             bigserial                         NOT NULL,
    primary_key_id bigint                            NOT NULL,
    column_id      bigint                            NOT NULL,
    created_uid    text COLLATE pg_catalog."default" NOT NULL,
    created_date   date                              NOT NULL,
    modified_uid   text COLLATE pg_catalog."default" NOT NULL,
    modified_date  date                              NOT NULL,
    CONSTRAINT "sqlPrimaryKeyColumnDetailPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sqlPrimaryKeyColumnUnderlyingColumnFKConstraint" FOREIGN KEY (column_id)
        REFERENCES db_model_detail.sql_column (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlPrimaryKeyColumnUnderlyingPrimaryKeyFKConstraint" FOREIGN KEY (primary_key_id)
        REFERENCES db_model_detail.sql_primary_key (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.sql_primary_key_column
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.sql_primary_key_column
    IS 'Links an SQL Primary Key Constraint with a Column that is used within its definition';

COMMENT ON CONSTRAINT "sqlPrimaryKeyColumnUnderlyingColumnFKConstraint" ON db_model_detail.sql_primary_key_column
    IS 'Links this SQL Primary Key Column to the Column on which it is applied';
COMMENT ON CONSTRAINT "sqlPrimaryKeyColumnUnderlyingPrimaryKeyFKConstraint" ON db_model_detail.sql_primary_key_column
    IS 'Links this SQL Primary Key Column to a Primary Key Constraint Detail';

---------------------------------------------------------------------------------
-- Table: db_model_detail.sql_foreign_key
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS db_model_detail.sql_foreign_key
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
    CONSTRAINT "sqlForeignKeyDetailPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sqlForeignKeyLocalColumnFKConstraint" FOREIGN KEY (local_column_id)
        REFERENCES db_model_detail.sql_column (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlForeignKeyReferencedColumnFKConstraint" FOREIGN KEY (referenced_column_id)
        REFERENCES db_model_detail.sql_column (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlForeignKeyReferencedTableFKConstraint" FOREIGN KEY (referenced_table_id)
        REFERENCES db_model_detail.sql_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlForeignKeyUnderlyingTableFKConstraint" FOREIGN KEY (table_id)
        REFERENCES db_model_detail.sql_table (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "sqlForeignKeyLocalColumnNotEqualToReferencedCheckConstraint" CHECK (local_column_id <> referenced_column_id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS db_model_detail.sql_foreign_key
    OWNER to postgresadmin;

COMMENT ON TABLE db_model_detail.sql_foreign_key
    IS 'Holds metadata about a Foreign Key Constraint for an SQL Database Table';

COMMENT ON CONSTRAINT "sqlForeignKeyLocalColumnFKConstraint" ON db_model_detail.sql_foreign_key
    IS 'Links this SQL Foreign Key Constraint to the Column on which is applied';
COMMENT ON CONSTRAINT "sqlForeignKeyReferencedColumnFKConstraint" ON db_model_detail.sql_foreign_key
    IS 'Links this SQL Foreign Key Constraint to the Column for which the Foreign Key references';
COMMENT ON CONSTRAINT "sqlForeignKeyReferencedTableFKConstraint" ON db_model_detail.sql_foreign_key
    IS 'Links this SQL Foreign Key Constraint to the Table it references';
COMMENT ON CONSTRAINT "sqlForeignKeyUnderlyingTableFKConstraint" ON db_model_detail.sql_foreign_key
    IS 'Links this SQL Foreign Key Constraint to the Table that contains it';

COMMENT ON CONSTRAINT "sqlForeignKeyLocalColumnNotEqualToReferencedCheckConstraint" ON db_model_detail.sql_foreign_key
    IS 'Ensures that this SQL Foreign Key''s Local Column is not the same as its Referenced Column';
-- </editor-fold>

-- <editor-fold desc="Detail-NoSQL">
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
-- Detail-NoSQL
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
-- </editor-fold>

-- </editor-fold>