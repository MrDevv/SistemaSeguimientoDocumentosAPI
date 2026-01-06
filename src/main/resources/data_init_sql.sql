----------- INSERT DE DATOS INICIALES DEL SISTEMA

-- Estados del sistema
insert into mae_documento_estado(descripcion) values('nuevo'), ('recepcionado'), ('enviado'), ('pendiente recepcion'), ('seguimiento finalizado'), ('en seguimiento');

-- Roles del sistema
insert into mae_rol(descripcion) values('administrador'), ('administrador área'), ('usuario');

-- Movimientos del sistema
insert into mae_indicacion(descripcion) values('Solicitar'),('Conocimientos  y Fines'),('Opinar y/o Informar'), ('Inspección Ocular'), ('Adjuntar Antecedentes');

-- Areas del Sistema
insert into mae_area(descripcion) values('SubGerencia de Informática y Sistemas'), ('Gerencia de Recursos Humanos');

-- Tipo Documento
insert into mae_tipo_documento(descripcion) values('Oficio'),('Acta de Constatacion'),('Carta'),('Citación'),('Constancia'), ('Denuncia');

-- Personas
insert into mae_persona (nombres, apellidos, celular, dni)
values
('Miguel', 'Vega Perez', '981029312', '78192013');

insert into mae_usuario (nombre_usuario, password, rol_id, persona_id)
values
('mvegap', 'admin', 1, 1);

insert into trd_usuario_area (usuario_id, area_id)
values
(1, 1);

select * from mae_persona
-- select * from mae_rol

--truncate table mae_area, mae_documento_estado, mae_indicacion, mae_persona, mae_rol, mae_tipo_documento, mae_usuario, trd_usuario_area, trs_envio, trs_recepcion restart identity cascade
