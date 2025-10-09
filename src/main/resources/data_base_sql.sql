CREATE TABLE mae_rol (
    rol_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE mae_tipo_documento (
    tipo_documento_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE mae_documento_estado (
    documento_estado_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE mae_indicacion (
    indicacion_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE mae_area (
    area_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL UNIQUE,
    estado SMALLINT NOT NULL DEFAULT 1
);

CREATE TABLE mae_persona (
    persona_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombres VARCHAR(45) NOT NULL,
    apellidos VARCHAR(45) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    celular VARCHAR(9)
);

CREATE TABLE mae_usuario (
    usuario_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    persona_id INT NOT NULL UNIQUE,
    rol_id INT NOT NULL,
    nombre_usuario VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    estado SMALLINT NOT NULL DEFAULT 1,
    FOREIGN KEY (persona_id) REFERENCES mae_persona(persona_id),
    FOREIGN KEY (rol_id) REFERENCES mae_rol(rol_id)
);

CREATE TABLE trd_usuario_area (
    usuario_area_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    usuario_id INT NOT NULL,
    area_id INT NOT NULL,
    fecha_ingreso TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_salida TIMESTAMP,
    estado CHAR(1) NOT NULL DEFAULT 'a',
    FOREIGN KEY (usuario_id) REFERENCES mae_usuario(usuario_id),
    FOREIGN KEY (area_id) REFERENCES mae_area(area_id)
);

CREATE TABLE mae_documento (
    documento_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo_documento_id INT NOT NULL,
    usuario_area_id INT NOT NULL,
    numero_documento VARCHAR(45) NOT NULL UNIQUE,
    asunto VARCHAR(255) NOT NULL,
    folios INT NOT NULL,
    fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    documento_estado_id INT NOT NULL,
    FOREIGN KEY (tipo_documento_id) REFERENCES mae_tipo_documento(tipo_documento_id),
    FOREIGN KEY (usuario_area_id) REFERENCES trd_usuario_area(usuario_area_id),
    FOREIGN KEY (documento_estado_id) REFERENCES mae_documento_estado(documento_estado_id)
);

CREATE TABLE trs_envio (
    envio_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    documento_id INT NOT NULL,
    usuario_area_origen_id INT NOT NULL,
    usuario_area_destino_id INT NOT NULL,
    indicacion_id INT NOT NULL,
    folios INT NOT NULL,
    observacion TEXT,
    fecha_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    documento_estado_id INT NOT NULL,
    FOREIGN KEY (documento_id) REFERENCES mae_documento(documento_id),
    FOREIGN KEY (usuario_area_origen_id) REFERENCES trd_usuario_area(usuario_area_id),
    FOREIGN KEY (usuario_area_destino_id) REFERENCES trd_usuario_area(usuario_area_id),
    FOREIGN KEY (indicacion_id) REFERENCES mae_indicacion(indicacion_id),
    FOREIGN KEY (documento_estado_id) REFERENCES mae_documento_estado(documento_estado_id)
);

CREATE TABLE trs_recepcion (
    recepcion_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    envio_id INT NOT NULL,
    usuario_area_id INT NOT NULL,
    fecha_recepcion TIMESTAMP,
    documento_estado_id INT NOT NULL,
    FOREIGN KEY (envio_id) REFERENCES trs_envio(envio_id),
    FOREIGN KEY (usuario_area_id) REFERENCES trd_usuario_area(usuario_area_id),
    FOREIGN KEY (documento_estado_id) REFERENCES mae_documento_estado(documento_estado_id)
);
