DROP TABLE IF EXISTS canvas.canvas_link_cell;
DROP TABLE IF EXISTS canvas.canvas_custom_cell;
DROP TABLE IF EXISTS canvas.canvas_cell;
DROP TABLE IF EXISTS canvas.canvas;

DROP SCHEMA IF EXISTS canvas;

---------------------------------------------------------------------------------
-- Schema: canvas
---------------------------------------------------------------------------------

CREATE SCHEMA IF NOT EXISTS canvas
    AUTHORIZATION postgresadmin;

COMMENT ON SCHEMA canvas
    IS 'Entities used to create and maintain Canvases used to document and diagram code';

---------------------------------------------------------------------------------
-- Table: canvas.canvas
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas.canvas
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

ALTER TABLE IF EXISTS canvas.canvas
    OWNER to postgresadmin;

COMMENT ON TABLE canvas.canvas
    IS 'Represents a Canvas used to diagram and document Models such as Code and Data';

COMMENT ON CONSTRAINT "canvasNameUniqueConstraint" ON canvas.canvas
    IS 'Ensures that the Name field is unique among all Canvases';

---------------------------------------------------------------------------------
-- Table: canvas.canvas_cell
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas.canvas_cell
(
    id            bigserial                         NOT NULL,
    canvas_id     bigint                            NOT NULL,
    name          text COLLATE pg_catalog."default" NOT NULL,
    local_name    text COLLATE pg_catalog."default" NOT NULL,
    created_uid   text COLLATE pg_catalog."default" NOT NULL,
    created_date  date                              NOT NULL,
    modified_uid  text COLLATE pg_catalog."default" NOT NULL,
    modified_date date                              NOT NULL,
    CONSTRAINT "canvasCellPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "canvasCellNameUniqueConstraint" UNIQUE (name)
        INCLUDE (name),
    CONSTRAINT "localNameAndCanvasIdUniqueConstraint" UNIQUE (canvas_id, local_name)
        INCLUDE (canvas_id, local_name),
    CONSTRAINT "canvasFKConstraint" FOREIGN KEY (canvas_id)
        REFERENCES canvas.canvas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS canvas.canvas_cell
    OWNER to postgresadmin;

COMMENT ON TABLE canvas.canvas_cell
    IS 'Represents a Cell within a Canvas';

COMMENT ON CONSTRAINT "canvasCellNameUniqueConstraint" ON canvas.canvas_cell
    IS 'Ensures that the Name is unique among all Canvas Cells';
COMMENT ON CONSTRAINT "localNameAndCanvasIdUniqueConstraint" ON canvas.canvas_cell
    IS 'Ensures that the Local Name value is unique within the Canvas of this Cell';
COMMENT ON CONSTRAINT "canvasFKConstraint" ON canvas.canvas_cell
    IS 'Links this Canvas Cell to a Canvas';

---------------------------------------------------------------------------------
-- Table: canvas.canvas_custom_cell
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas.canvas_custom_cell
(
    id                bigserial                         NOT NULL,
    canvas_cell_id    bigint                            NOT NULL,
    html              text COLLATE pg_catalog."default" NOT NULL,
    width             numeric                           NOT NULL,
    height            numeric                           NOT NULL,
    canvas_position_x numeric                           NOT NULL DEFAULT 0,
    canvas_position_y numeric                           NOT NULL DEFAULT 0,
    canvas_position_z numeric                           NOT NULL DEFAULT 0,
    CONSTRAINT "canvasCustomCellPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "canvasCellFKConstraint" FOREIGN KEY (canvas_cell_id)
        REFERENCES canvas.canvas_cell (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS canvas.canvas_custom_cell
    OWNER to postgresadmin;

COMMENT ON TABLE canvas.canvas_custom_cell
    IS 'Represents a Custom Canvas Cell which uses arbitrary HTML for the view';

COMMENT ON CONSTRAINT "canvasCellFKConstraint" ON canvas.canvas_custom_cell
    IS 'Ties this Canvas Custom Cell to it''s parent Canvas Cell';

---------------------------------------------------------------------------------
-- Table: canvas.canvas_link_cell
---------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS canvas.canvas_link_cell
(
    id                     bigserial                         NOT NULL,
    canvas_cell_id         bigint                            NOT NULL,
    source_cell_local_name text COLLATE pg_catalog."default" NOT NULL,
    target_cell_local_name text COLLATE pg_catalog."default" NOT NULL,
    link_source_dx         numeric                           NOT NULL DEFAULT 0,
    link_source_dy         numeric                           NOT NULL DEFAULT 0,
    link_target_dx         numeric                           NOT NULL DEFAULT 0,
    link_target_dy         numeric                           NOT NULL DEFAULT 0,
    CONSTRAINT "canvasSerializedCellPKConstraint" PRIMARY KEY (id),
    CONSTRAINT "canvasCellFKConstraint" FOREIGN KEY (canvas_cell_id)
        REFERENCES canvas.canvas_cell (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS canvas.canvas_link_cell
    OWNER to postgresadmin;

COMMENT ON TABLE canvas.canvas_link_cell
    IS 'Represents a Canvas Cell that links together two other Canvas Cells; for instance an arrow between two database tables';

COMMENT ON CONSTRAINT "canvasCellFKConstraint" ON canvas.canvas_link_cell
    IS 'Ties this Canvas Link Cell to it''s parent Canvas Cell';