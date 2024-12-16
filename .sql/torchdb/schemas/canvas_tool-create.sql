DROP TABLE IF EXISTS canvas_doc_tool.link_cell;
DROP TABLE IF EXISTS canvas_doc_tool.custom_cell;
DROP TABLE IF EXISTS canvas_doc_tool.cell;
DROP TABLE IF EXISTS canvas_doc_tool.canvas;

DROP SCHEMA IF EXISTS canvas_doc_tool;

---------------------------------------------------------------------------------
-- Schema: canvas
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS canvas_doc_tool
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA canvas_doc_tool
    IS 'Entities used to create and maintain Canvases used to Document and Diagram Code';

---------------------------------------------------------------------------------
-- Table: canvas_doc_tool.canvas
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas_doc_tool.canvas
(
    id               bigserial                         NOT NULL,
    name             text COLLATE pg_catalog."default" NOT NULL,
    height           numeric                           NOT NULL,
    width            numeric                           NOT NULL,
    background_color text COLLATE pg_catalog."default" NOT NULL,
    created_uid      text COLLATE pg_catalog."default" NOT NULL,
    created_date     date                              NOT NULL,
    modified_uid     text COLLATE pg_catalog."default" NOT NULL,
    modified_date    date                              NOT NULL,
    CONSTRAINT "canvasPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "canvasNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name)
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS canvas_doc_tool.canvas
    OWNER to postgresadmin;

COMMENT ON TABLE canvas_doc_tool.canvas
    IS 'Represents a Canvas used to diagram and document Models such as Code and Data';

COMMENT ON CONSTRAINT "canvasNameUniqueConstraint" ON canvas_doc_tool.canvas
    IS 'Ensures that the Name field is unique among all Canvases';

---------------------------------------------------------------------------------
-- Table: canvas_doc_tool.cell
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas_doc_tool.cell
(
    id            bigserial                         NOT NULL,
    canvas_id     bigint                            NOT NULL,
    global_name   text COLLATE pg_catalog."default" NOT NULL,
    local_name    text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "cellPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "cellGlobalNameUniqueConstraint" UNIQUE (global_name)
        INCLUDE (global_name),
    CONSTRAINT "localNameAndCanvasIdUniqueConstraint" UNIQUE (canvas_id, local_name)
        INCLUDE (canvas_id, local_name),
    CONSTRAINT "cellCanvasFKConstraint" FOREIGN KEY (canvas_id)
        REFERENCES canvas_doc_tool.canvas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS canvas_doc_tool.cell
    OWNER to postgresadmin;

COMMENT ON TABLE canvas_doc_tool.cell
    IS 'Represents a Cell within a Canvas';

COMMENT ON CONSTRAINT "cellGlobalNameUniqueConstraint" ON canvas_doc_tool.cell
    IS 'Ensures that the Global Name is unique among all Canvas Cells';
COMMENT ON CONSTRAINT "localNameAndCanvasIdUniqueConstraint" ON canvas_doc_tool.cell
    IS 'Ensures that the Local Name value is unique within the Canvas that the Cell is contained';
COMMENT ON CONSTRAINT "cellCanvasFKConstraint" ON canvas_doc_tool.cell
    IS 'Links this Canvas Cell to the Canvas in which it is contained';

---------------------------------------------------------------------------------
-- Table: canvas_doc_tool.custom_cell
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas_doc_tool.custom_cell
(
    id                bigserial                         NOT NULL,
    cell_id           bigint                            NOT NULL,
    html              text COLLATE pg_catalog."default" NOT NULL,
    height            numeric                           NOT NULL,
    width             numeric                           NOT NULL,
    canvas_position_x numeric                           NOT NULL DEFAULT 0,
    canvas_position_y numeric                           NOT NULL DEFAULT 0,
    canvas_position_z numeric                           NOT NULL DEFAULT 0,
    CONSTRAINT "customCellPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "customCellUnderlyingCellFKConstraint" FOREIGN KEY (cell_id)
        REFERENCES canvas_doc_tool.cell (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS canvas_doc_tool.custom_cell
    OWNER to postgresadmin;

COMMENT ON TABLE canvas_doc_tool.custom_cell
    IS 'Represents a Custom Cell within a Canvas which uses arbitrary HTML for its view';

COMMENT ON CONSTRAINT "customCellUnderlyingCellFKConstraint" ON canvas_doc_tool.custom_cell
    IS 'Links this Custom Cell to its underlying Cell record';

---------------------------------------------------------------------------------
-- Table: canvas_doc_tool.link_cell
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas_doc_tool.link_cell
(
    id                     bigserial                         NOT NULL,
    cell_id                bigint                            NOT NULL,
    source_cell_local_name text COLLATE pg_catalog."default" NOT NULL,
    target_cell_local_name text COLLATE pg_catalog."default" NOT NULL,
    link_source_dx         numeric                           NOT NULL DEFAULT 0,
    link_source_dy         numeric                           NOT NULL DEFAULT 0,
    link_target_dx         numeric                           NOT NULL DEFAULT 0,
    link_target_dy         numeric                           NOT NULL DEFAULT 0,
    CONSTRAINT "linkCellPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "linkCellUnderlyingCellFKConstraint" FOREIGN KEY (cell_id)
        REFERENCES canvas_doc_tool.cell (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS canvas_doc_tool.link_cell
    OWNER to postgresadmin;

COMMENT ON TABLE canvas_doc_tool.link_cell
    IS 'Represents a Cell that links together two other Cells within a Canvas; for instance an arrow between two Database Tables';

COMMENT ON CONSTRAINT "linkCellUnderlyingCellFKConstraint" ON canvas_doc_tool.link_cell
    IS 'Ties this Link Cell to its underlying Cell record';