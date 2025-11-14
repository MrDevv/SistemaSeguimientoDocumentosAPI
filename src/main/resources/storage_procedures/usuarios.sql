CREATE OR REPLACE FUNCTION sp_listar_usuarios_area(
)
RETURNS TABLE(
	usuario_area_id INT,
	fecha_ingreso TIMESTAMP,
	area VARCHAR,
	usuario_id INT,
	usuario VARCHAR,
	persona_id INT,
	nombres VARCHAR,
	apellidos VARCHAR,
	dni VARCHAR,
	celular VARCHAR,
	rol_id INT,
	rol VARCHAR,
	estado CHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
	RETURN QUERY
	SELECT 
    ua.usuario_area_id, 
    ua.fecha_ingreso,     
    a.descripcion,     
    u.usuario_id, U.nombre_usuario AS usuario,
    p.persona_id, p.nombres, p.apellidos, p.dni, p.celular,
    r.rol_id, r.descripcion,
    ua.estado
    FROM trd_usuario_area ua
    INNER JOIN mae_area a ON ua.area_id = a.area_id
    INNER JOIN mae_usuario u ON ua.usuario_id = u.usuario_id
    INNER JOIN mae_persona p ON u.persona_id = p.persona_id
    INNER JOIN mae_rol r ON u.rol_id = r.rol_id;
END;
$$;