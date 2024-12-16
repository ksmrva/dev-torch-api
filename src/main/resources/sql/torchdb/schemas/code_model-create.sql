-- <editor-fold desc="Source">
-- ******************************************************************************
---------------------------------------------------------------------------------
-- Source
---------------------------------------------------------------------------------
-- ******************************************************************************

DROP TABLE IF EXISTS code_model_source.file_data;
DROP TABLE IF EXISTS code_model_source.file;
DROP TABLE IF EXISTS code_model_source.project;
DROP TABLE IF EXISTS code_model_source.file_tree_node;
DROP TABLE IF EXISTS code_model_source.language_file_extension;
DROP TABLE IF EXISTS code_model_source.language;

DROP SCHEMA IF EXISTS code_model_source;

---------------------------------------------------------------------------------
-- Schema: code_model_source
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS code_model_source
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA code_model_source
    IS 'Entities used to define the configuration details of a Source that can be used to create Details for a Code Model';

---------------------------------------------------------------------------------
-- Table: code_model_source.language
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model_source.language
(
    id            bigserial                         NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "languagePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "languageNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model_source.language
    OWNER to postgresadmin;

COMMENT ON TABLE code_model_source.language
    IS 'Represents a Programming Language used to classify various records for a Code Model';

COMMENT ON CONSTRAINT "languageNameUniqueConstraint" ON code_model_source.language
    IS 'Ensures that the Name value is unique among all Languages';

INSERT INTO code_model_source.language (name, created_uid, created_date, modified_uid, modified_date)
VALUES ('abap', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('apex', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('azcli', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('bat', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('bicep', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('cameligo', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('clojure', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('coffee', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('cpp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('csharp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('csp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('css', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('cypher', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('dart', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('dockerfile', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('ecl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('elixir', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('flow9', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('freemarker2', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('fsharp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('go', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('graphql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('handlebars', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('hcl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('html', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('ini', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('java', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('javascript', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('julia', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('kotlin', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('less', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('lexon', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('liquid', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('lua', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('m3', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('markdown', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mdx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mips', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('msdax', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('mysql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('objective-c', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('pascal', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('pascaligo', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('perl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('pgsql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('php', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('pla', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('postiats', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('powerquery', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('powershell', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('protobuf', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('pug', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('python', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('qsharp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('r', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('razor', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('redis', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('redshift', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('restructuredtext', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('ruby', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('rust', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('sb', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('scala', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('scheme', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('scss', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('shell', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('solidity', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('sophia', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('sparql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('sql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('st', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('swift', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('systemverilog', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('tcl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('twig', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('typescript', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('typespec', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('vb', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('verilog', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('wgsl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('xml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ('yaml', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: code_model_source.language_file_extension
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model_source.language_file_extension
(
    id            bigserial                         NOT NULL,
    language_id   bigint                            NOT NULL,
    extension     text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sourceFileLanguageExtensionPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceFileLanguageExtensionUniqueConstraint" UNIQUE (extension)
        INCLUDE (extension),
    CONSTRAINT "sourceFileLanguageExtensionUnderlyingLanguageFKConstraint" FOREIGN KEY (language_id)
        REFERENCES code_model_source.language (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model_source.language_file_extension
    OWNER to postgresadmin;

COMMENT ON TABLE code_model_source.language_file_extension
    IS 'Stores the various File Extensions that match known Code Languages';

COMMENT ON CONSTRAINT "sourceFileLanguageExtensionUniqueConstraint" ON code_model_source.language_file_extension
    IS 'Ensures that the File Extension value is unique among all Language File Extensions';

COMMENT ON CONSTRAINT "sourceFileLanguageExtensionUnderlyingLanguageFKConstraint" ON code_model_source.language_file_extension
    IS 'Links this Language File Extension with its underlying Language';

INSERT INTO code_model_source.language_file_extension (language_id, extension, created_uid, created_date, modified_uid, modified_date)
VALUES ((SELECT id FROM code_model_source.language WHERE name = 'abap'), 'abap', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'apex'), 'cls', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'azcli'), 'azcli', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'bat'), 'bat', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'bat'), 'cmd', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'bicep'), 'bicep', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'cameligo'), 'mligo', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'clojure'), 'clj', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'clojure'), 'cljs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'clojure'), 'cljc', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'clojure'), 'edn', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'coffee'), 'coffee', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'cpp'), 'c', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'cpp'), 'h', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'csharp'), 'cs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'csharp'), 'csx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'csharp'), 'cake', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'csp'), 'csp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'css'), 'css', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'cypher'), 'cypher', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'cypher'), 'cyp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'dart'), 'dart', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'dockerfile'), 'dockerfile', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ecl'), 'ecl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'elixir'), 'ex', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'elixir'), 'exs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'flow9'), 'flow', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'freemarker2'), 'ftl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'freemarker2'), 'ftlh', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'freemarker2'), 'ftlx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'fsharp'), 'fs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'fsharp'), 'fsi', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'fsharp'), 'ml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'fsharp'), 'mli', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'fsharp'), 'fsx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'fsharp'), 'fsscript', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'go'), 'go', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'graphql'), 'graphql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'graphql'), 'gql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'handlebars'), 'handlebars', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'handlebars'), 'hbs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'hcl'), 'tf', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'hcl'), 'tfvars', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'hcl'), 'hcl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'html', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'htm', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'shtml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'xhtml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'mdoc', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'jsp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'asp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'aspx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'html'), 'jshtm', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ini'), 'ini', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ini'), 'properties', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ini'), 'gitconfig', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'java'), 'java', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'java'), 'jav', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'javascript'), 'js', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'javascript'), 'es6', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'javascript'), 'jsx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'javascript'), 'mjs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'javascript'), 'cjs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'julia'), 'jl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'kotlin'), 'kt', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'kotlin'), 'kts', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'less'), 'less', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'lexon'), 'lex', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'liquid'), 'liquid', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'liquid'), 'html.liquid', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'lua'), 'lua', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'm3'), 'm3', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'm3'), 'i3', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'm3'), 'mg', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'm3'), 'ig', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'md', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'markdown', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'mdown', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'mkdn', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'mkd', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'mdwn', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'mdtxt', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'markdown'), 'mdtext', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'mdx'), 'mdx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'mips'), 's', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'msdax'), 'dax', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'msdax'), 'msdax', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'objective-c'), 'm', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'pascal'), 'pas', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'pascal'), 'p', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'pascal'), 'pp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'pascaligo'), 'ligo', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'perl'), 'pl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'perl'), 'pm', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'php'), 'php', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'php'), 'php4', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'php'), 'php5', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'php'), 'phtml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'php'), 'ctp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'pla'), 'pla', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'postiats'), 'dats', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'postiats'), 'sats', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'postiats'), 'hats', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'powerquery'), 'pq', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'powerquery'), 'pqm', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'powershell'), 'ps1', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'powershell'), 'psm1', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'powershell'), 'psd1', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'protobuf'), 'proto', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'pug'), 'jade', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'pug'), 'pug', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'python'), 'py', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'python'), 'rpy', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'python'), 'pyw', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'python'), 'cpy', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'python'), 'gyp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'python'), 'gypi', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'qsharp'), 'qs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'r'), 'r', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'r'), 'rhistory', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'r'), 'rmd', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'r'), 'rprofile', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'r'), 'rt', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'razor'), 'cshtml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'redis'), 'redis', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'restructuredtext'), 'rst', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ruby'), 'rb', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ruby'), 'rbx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ruby'), 'rjs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ruby'), 'gemspec', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'ruby'), 'ru', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'rust'), 'rs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'rust'), 'rlib', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'sb'), 'sb', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scala'), 'scala', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scala'), 'sc', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scala'), 'sbt', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scheme'), 'scm', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scheme'), 'ss', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scheme'), 'sch', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scheme'), 'rkt', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'scss'), 'scss', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'shell'), 'sh', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'shell'), 'bash', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'solidity'), 'sol', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'sophia'), 'aes', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'sparql'), 'rq', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'sql'), 'sql', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'st', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'iecst', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'iecplc', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'lc3lib', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'TcPOU', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'TcDUT', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'TcGVL', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'st'), 'TcIO', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'swift'), 'swift', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'systemverilog'), 'sv', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'systemverilog'), 'svh', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'tcl'), 'tcl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'twig'), 'twig', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'typescript'), 'ts', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'typescript'), 'tsx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'typescript'), 'cts', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'typescript'), 'mts', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'typespec'), 'tsp', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'vb'), 'vb', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'verilog'), 'v', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'verilog'), 'vh', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'wgsl'), 'wgsl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'xml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'xsd', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'dtd', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'ascx', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'csproj', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'config', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'props', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'targets', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'wxi', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'wxl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'wxs', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'xaml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'svg', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'svgz', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'opf', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'xslt', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'xml'), 'xsl', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'yaml'), 'yaml', 'kmark', '2024-09-23', 'kmark', '2024-09-23'),
       ((SELECT id FROM code_model_source.language WHERE name = 'yaml'), 'yml', 'kmark', '2024-09-23', 'kmark', '2024-09-23');

---------------------------------------------------------------------------------
-- Table: code_model_source.file_tree_node
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model_source.file_tree_node
(
    id             bigserial                         NOT NULL,
    parent_node_id bigint,
    created_uid    text COLLATE pg_catalog."default" NOT NULL,
    created_date   date                              NOT NULL,
    modified_uid   text COLLATE pg_catalog."default" NOT NULL,
    modified_date  date                              NOT NULL,
    CONSTRAINT "sourceFileTreeNodePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceFileTreeNodeParentNodeFKConstraint" FOREIGN KEY (parent_node_id)
        REFERENCES code_model_source.file_tree_node (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model_source.file_tree_node
    OWNER to postgresadmin;

COMMENT ON TABLE code_model_source.file_tree_node
    IS 'Captures the File Structure of a Codebase''s Source Files by representing each Source File as a Node within a Tree';

COMMENT ON CONSTRAINT "sourceFileTreeNodeParentNodeFKConstraint" ON code_model_source.file_tree_node
    IS 'Links this Source File Node with another Source File Node which represents its Parent Node in a File Tree';

---------------------------------------------------------------------------------
-- Table: code_model_source.file
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model_source.file
(
    id            bigserial                         NOT NULL,
    tree_node_id  bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    is_directory  boolean                           NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "sourceFilePKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceFileTreeNodeFKConstraint" FOREIGN KEY (tree_node_id)
        REFERENCES code_model_source.file_tree_node (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model_source.file
    OWNER to postgresadmin;

COMMENT ON TABLE code_model_source.file
    IS 'Represents a File within a Codebase that acts as a Source to create Details of a Code Model';

COMMENT ON CONSTRAINT "sourceFileTreeNodeFKConstraint" ON code_model_source.file
    IS 'Links this Source File with a File Tree Node entity which defines the backing File Structure of the Files within a Codebase';

---------------------------------------------------------------------------------
-- Table: code_model_source.file_data
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model_source.file_data
(
    id                 bigserial                         NOT NULL,
    file_id            bigint                            NOT NULL,
    is_binary          boolean                           NOT NULL,
    binary_content_uri text COLLATE pg_catalog."default",
    text_content       text COLLATE pg_catalog."default",
    created_uid        text COLLATE pg_catalog."default" NOT NULL,
    created_date       date                              NOT NULL,
    modified_uid       text COLLATE pg_catalog."default" NOT NULL,
    modified_date      date                              NOT NULL,
    CONSTRAINT "sourceFileDataPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceFileDataUnderlyingFileFKConstraint" FOREIGN KEY (file_id)
        REFERENCES code_model_source.file (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model_source.file_data
    OWNER to postgresadmin;

COMMENT ON TABLE code_model_source.file_data
    IS 'Stores the Data contents of a non-Directory Source File, either as a String or a URI pointer to Binary Data';

COMMENT ON CONSTRAINT "sourceFileDataUnderlyingFileFKConstraint" ON code_model_source.file_data
    IS 'Links this Source File Data with its underlying Source File';

---------------------------------------------------------------------------------
-- Table: code_model_source.project
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS code_model_source.project
(
    id                bigserial                         NOT NULL,
    name              text COLLATE pg_catalog."default" NOT NULL,
    root_tree_node_id bigint                            NOT NULL,
    created_uid       text COLLATE pg_catalog."default" NOT NULL,
    created_date      date                              NOT NULL,
    modified_uid      text COLLATE pg_catalog."default" NOT NULL,
    modified_date     date                              NOT NULL,
    CONSTRAINT "sourceProjectPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "sourceProjectNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name),
    CONSTRAINT "sourceProjectRootTreeNodeFKConstraint" FOREIGN KEY (root_tree_node_id)
        REFERENCES code_model_source.file_tree_node (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS code_model_source.project
    OWNER to postgresadmin;

COMMENT ON TABLE code_model_source.project
    IS 'Represents a Project within a Codebase containing Source Code Files';

COMMENT ON CONSTRAINT "sourceProjectNameUniqueConstraint" ON code_model_source.project
    IS 'Ensures that the Name of the Source Project is unique among all Source Projects';

COMMENT ON CONSTRAINT "sourceProjectRootTreeNodeFKConstraint" ON code_model_source.project
    IS 'Links this Source Project with a File Tree Node that represents the Root File Node for the Project';
-- </editor-fold>

-- <editor-fold desc="Detail">
-- ******************************************************************************
---------------------------------------------------------------------------------
-- Detail
---------------------------------------------------------------------------------
-- ******************************************************************************
-- </editor-fold>