DROP TABLE IF EXISTS houseinv.appliance;
DROP TABLE IF EXISTS houseinv.personal_resource;
DROP TABLE IF EXISTS houseinv.tenant;
DROP TABLE IF EXISTS houseinv.resource;
DROP TABLE IF EXISTS houseinv.house;
DROP TABLE IF EXISTS houseinv.person;

---------------------------------------------------------------------------------
-- Table: houseinv.person
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS houseinv.person
(
    id            bigserial                         NOT NULL,
    first_name    text COLLATE pg_catalog."default" NOT NULL,
    last_name     text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "personPKConstraint" PRIMARY KEY (id)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS houseinv.person
    OWNER to postgresadmin;

COMMENT ON TABLE houseinv.person
    IS 'Representation of a Person';

---------------------------------------------------------------------------------
-- Table: houseinv.house
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS houseinv.house
(
    id            bigserial                         NOT NULL,
    owner_id      bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    address1      text COLLATE pg_catalog."default" NOT NULL,
    address2      text COLLATE pg_catalog."default",
    city          text COLLATE pg_catalog."default" NOT NULL,
    state         text COLLATE pg_catalog."default" NOT NULL,
    zip           text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "housePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "ownerIdPersonFKConstraint" FOREIGN KEY (owner_id)
        REFERENCES houseinv.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS houseinv.house
    OWNER to postgresadmin;

COMMENT ON TABLE houseinv.house
    IS 'Represents a House that can have Resources associated with it';

COMMENT ON CONSTRAINT "ownerIdPersonFKConstraint" ON houseinv.house
    IS 'Links this House with a Person record, representing the Owner of the property';

---------------------------------------------------------------------------------
-- Table: houseinv.resource
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS houseinv.resource
(
    id            bigserial                         NOT NULL,
    house_id      bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "resourcePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "houseIdHouseFKConstraint" FOREIGN KEY (house_id)
        REFERENCES houseinv.house (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS houseinv.resource
    OWNER to postgresadmin;

COMMENT ON TABLE houseinv.resource
    IS 'Represents a generic Resource that is associated with a House';

COMMENT ON CONSTRAINT "houseIdHouseFKConstraint" ON houseinv.resource
    IS 'Links this Resource with a House in which the Resource resides';

---------------------------------------------------------------------------------
-- Table: houseinv.tenant
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS houseinv.tenant
(
    id            bigserial                         NOT NULL,
    house_id      bigint                            NOT NULL,
    person_id     bigint                            NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "tenantPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "houseIdHouseFKConstraint" FOREIGN KEY (house_id)
        REFERENCES houseinv.house (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "personIdPersonFKConstraint" FOREIGN KEY (person_id)
        REFERENCES houseinv.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS houseinv.tenant
    OWNER to postgresadmin;

COMMENT ON TABLE houseinv.tenant
    IS 'Represents a relationship between a House and a Person in which the Person lives in the House';

COMMENT ON CONSTRAINT "houseIdHouseFKConstraint" ON houseinv.tenant
    IS 'Links this Tenant to the House in which they live';
COMMENT ON CONSTRAINT "personIdPersonFKConstraint" ON houseinv.tenant
    IS 'Links this Tenant with a Person record';

---------------------------------------------------------------------------------
-- Table: houseinv.personal_resource
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS houseinv.personal_resource
(
    id            bigserial                         NOT NULL,
    tenant_id     bigint                            NOT NULL,
    resource_id   bigint                            NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "personalResourcePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "resourceIdResourceFKConstraint" FOREIGN KEY (resource_id)
        REFERENCES houseinv.resource (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "tenantIdTenantFKConstraint" FOREIGN KEY (tenant_id)
        REFERENCES houseinv.tenant (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS houseinv.personal_resource
    OWNER to postgresadmin;

COMMENT ON TABLE houseinv.personal_resource
    IS 'Represents a Resource of a House that belongs directly to a Tenant';

COMMENT ON CONSTRAINT "resourceIdResourceFKConstraint" ON houseinv.personal_resource
    IS 'Links this Personal Resource with an underlying Resource record';
COMMENT ON CONSTRAINT "tenantIdTenantFKConstraint" ON houseinv.personal_resource
    IS 'Links this Personal Resource to the Tenant that owns it';

---------------------------------------------------------------------------------
-- Table: houseinv.appliance
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS houseinv.appliance
(
    id                   bigserial                         NOT NULL,
    brand                text COLLATE pg_catalog."default" NOT NULL,
    price                money                             NOT NULL,
    purchase_date        date                              NOT NULL,
    installation_date    date,
    personal_resource_id bigint                            NOT NULL,
    created_uid          text COLLATE pg_catalog."default" NOT NULL,
    created_date         date                              NOT NULL,
    modified_uid         text COLLATE pg_catalog."default" NOT NULL,
    modified_date        date                              NOT NULL,
    CONSTRAINT "appliancePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "personalResourceIdPersonalResourceForeignKey" FOREIGN KEY (personal_resource_id)
        REFERENCES houseinv.personal_resource (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS houseinv.appliance
    OWNER to postgresadmin;

COMMENT ON TABLE houseinv.appliance
    IS 'Represents an Appliance that resides within a House';

COMMENT ON CONSTRAINT "personalResourceIdPersonalResourceForeignKey" ON houseinv.appliance
    IS 'Links this Appliance to an underlying Personal Resource record';