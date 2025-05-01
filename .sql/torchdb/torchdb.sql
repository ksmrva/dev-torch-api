-- Database: torchdb

-- DROP DATABASE IF EXISTS torchdb;

CREATE DATABASE torchdb
    WITH
    OWNER = postgresadmin
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE torchdb
    IS 'Database for the devTorch application';