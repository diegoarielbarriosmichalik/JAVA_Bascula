--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Online
-- Project :      stock 1.0.dm1
-- Author :       Dell
--
-- Date Created : Thursday, March 17, 2016 17:35:43
-- Target DBMS : PostgreSQL 8.0
--

-- 
-- TABLE: camion 
--

--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Online
-- Project :      stock 1.0.dm1
-- Author :       Dell
--
-- Date Created : Thursday, March 17, 2016 18:16:10
-- Target DBMS : PostgreSQL 8.0
--

-- 
-- TABLE: reporte_recepcion 
--

CREATE TABLE reporte_recepcion(
    id_reporte_recepcion    int4              NOT NULL,
    numero                  int4,
    fecha                   char(100),
    cliente                 char(100),
    camion                  char(100),
    carreta                 char(100),
    chofer                  char(100),
    peso_bruto_comp         int4,
    peso_bruto              numeric(10, 0),
    peso_tara_comp          int4,
    peso_tara               numeric(10, 0),
    peso_neto               numeric(10, 0),
    CONSTRAINT "PK52" PRIMARY KEY (id_reporte_recepcion)
)
;





CREATE TABLE camion(
    id_camion    int4         NOT NULL,
    marca        char(100),
    modelo       char(100),
    anho         int4,
    chapa        char(18),
    borrado      int4,
    CONSTRAINT "PK31" PRIMARY KEY (id_camion)
)
;



-- 
-- TABLE: carreta 
--

CREATE TABLE carreta(
    id_carreta    int4         NOT NULL,
    marca         char(100),
    modelo        char(100),
    anho          char(10),
    chapa         char(100),
    borrado       int4,
    CONSTRAINT "PK32" PRIMARY KEY (id_carreta)
)
;



-- 
-- TABLE: chofer 
--

CREATE TABLE chofer(
    id_chofer    int4         NOT NULL,
    nombre       char(100),
    ci           char(100),
    telefono     char(100),
    direccion    char(100),
    borrado      int4,
    CONSTRAINT "PK30" PRIMARY KEY (id_chofer)
)
;



-- 
-- TABLE: cliente 
--

CREATE TABLE cliente(
    id_cliente    int4         NOT NULL,
    nombre        char(100),
    direccion     char(100),
    telefono      char(100),
    obs           char(100),
    ruc           char(100),
    borrado       int4,
    CONSTRAINT "PK1" PRIMARY KEY (id_cliente)
)
;



-- 
-- TABLE: direccion 
--

CREATE TABLE direccion(
    id_direccion    int4         NOT NULL,
    direccion       char(100),
    borrado         char(2),
    CONSTRAINT "PK37" PRIMARY KEY (id_direccion)
)
;



-- 
-- TABLE: estado 
--

CREATE TABLE estado(
    id_estado    char(10)    NOT NULL,
    estado       char(10),
    CONSTRAINT "PK5" PRIMARY KEY (id_estado)
)
;



-- 
-- TABLE: motivo_traslado 
--

CREATE TABLE motivo_traslado(
    id_motivo    int4         NOT NULL,
    motivo       char(100),
    CONSTRAINT "PK36" PRIMARY KEY (id_motivo)
)
;



-- 
-- TABLE: recepcion 
--

CREATE TABLE recepcion(
    id_recepcion         int4         NOT NULL,
    numero               int8,
    fecha                char(100),
    comp_pesaje_bruto    int4,
    peso_bruto           int8,
    comp_pesaje_tara     char(10),
    peso_tara            int8,
    peso_neto            int8,
    print                int4,
    estado               int4,
    borrado              int4,
    CONSTRAINT "PK42" PRIMARY KEY (id_recepcion)
)
;



-- 
-- TABLE: recepcion_detalle 
--

CREATE TABLE recepcion_detalle(
    id_recepcion_detalle    int4    NOT NULL,
    id_recepcion            int4    NOT NULL,
    id_cliente              int4    NOT NULL,
    id_chofer               int4    NOT NULL,
    id_carreta              int4    NOT NULL,
    id_camion               int4    NOT NULL,
    borrado                 int4,
    CONSTRAINT "PK51" PRIMARY KEY (id_recepcion_detalle)
)
;



-- 
-- TABLE: remision 
--

CREATE TABLE remision(
    id_remision               int4         NOT NULL,
    numero                    char(10),
    fecha                     char(10),
    id_direccion              int4         NOT NULL,
    fecha_inicio_traslado     char(10),
    fecha_termino_traslado    char(10),
    id_chofer                 int4         NOT NULL,
    id_camion                 int4         NOT NULL,
    id_carreta                int4         NOT NULL,
    id_motivo                 int4         NOT NULL,
    cantidad                  char(10),
    descripcion               char(100),
    borrado                   int4,
    CONSTRAINT "PK34" PRIMARY KEY (id_remision)
)
;



-- 
-- TABLE: reportecaja 
--

CREATE TABLE reportecaja(
    id_reporte_caja    int4         NOT NULL,
    comprobante        char(100),
    factura            char(100),
    cliente            char(100),
    fecha              char(100),
    total              int8,
    CONSTRAINT "PK28" PRIMARY KEY (id_reporte_caja)
)
;



-- 
-- TABLE: usuario 
--

CREATE TABLE usuario(
    id_usuario     int4         NOT NULL,
    nombre         char(100),
    contrasenha    char(100),
    privilegio     int4,
    CONSTRAINT "PK16" PRIMARY KEY (id_usuario)
)
;



-- 
-- TABLE: recepcion_detalle 
--

ALTER TABLE recepcion_detalle ADD CONSTRAINT "Refchofer43" 
    FOREIGN KEY (id_chofer)
    REFERENCES chofer(id_chofer)
;

ALTER TABLE recepcion_detalle ADD CONSTRAINT "Refcliente44" 
    FOREIGN KEY (id_cliente)
    REFERENCES cliente(id_cliente)
;

ALTER TABLE recepcion_detalle ADD CONSTRAINT "Refcarreta45" 
    FOREIGN KEY (id_carreta)
    REFERENCES carreta(id_carreta)
;

ALTER TABLE recepcion_detalle ADD CONSTRAINT "Refcamion46" 
    FOREIGN KEY (id_camion)
    REFERENCES camion(id_camion)
;

ALTER TABLE recepcion_detalle ADD CONSTRAINT "Refrecepcion47" 
    FOREIGN KEY (id_recepcion)
    REFERENCES recepcion(id_recepcion)
;


-- 
-- TABLE: remision 
--

ALTER TABLE remision ADD CONSTRAINT "Refchofer23" 
    FOREIGN KEY (id_chofer)
    REFERENCES chofer(id_chofer)
;

ALTER TABLE remision ADD CONSTRAINT "Refcamion24" 
    FOREIGN KEY (id_camion)
    REFERENCES camion(id_camion)
;

ALTER TABLE remision ADD CONSTRAINT "Refcarreta25" 
    FOREIGN KEY (id_carreta)
    REFERENCES carreta(id_carreta)
;

ALTER TABLE remision ADD CONSTRAINT "Refmotivo_traslado27" 
    FOREIGN KEY (id_motivo)
    REFERENCES motivo_traslado(id_motivo)
;

ALTER TABLE remision ADD CONSTRAINT "Refdireccion28" 
    FOREIGN KEY (id_direccion)
    REFERENCES direccion(id_direccion)
;






insert into usuario values
(1, 'admin', 'admin', 1);

insert into cliente values
(0, '0', '0', '0', '0', '0', 0);

insert into transportista values
(0, '0', '0', '0', '0', '0', 0);

insert into camion values
(0, '0', '0', 0, '0', 0, 0);

insert into carreta values
(0, '0', '0', '0', '0', 0);

insert into chofer values
(0, '0', '0', '0', '0', 0);
