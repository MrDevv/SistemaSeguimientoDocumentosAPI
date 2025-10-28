CREATE OR REPLACE FUNCTION sp_listar_areas(
    estado_area SMALLINT,
    nombre_area VARCHAR(200)
)
RETURNS TABLE (
    area_id INT,
    descripcion VARCHAR,
    estado SMALLINT
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT a.area_id, a.descripcion, a.estado
    FROM mae_area a
    WHERE
        (estado_area IS NULL OR a.estado = estado_area)
        AND
        (nombre_area IS NULL OR a.descripcion ILIKE '%' || nombre_area || '%');
END;
$$;

-- deshabilitar area por id
CREATE OR REPLACE FUNCTION sp_deshabilitar_area(
    id_area INTEGER
)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE mae_area SET estado = 0 WHERE area_id = id_area;
END;
$$;

-- habilitar area por id
CREATE OR REPLACE FUNCTION sp_habilitar_area(
    id_area INTEGER
)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE mae_area SET estado = 1 WHERE area_id = id_area;
END;
$$;

CREATE OR REPLACE FUNCTION sp_listar_usuarios_activos_por_area(
    id_area SMALLINT
)
RETURNS TABLE (
    usuario_area_id INT,
    nombres VARCHAR,
    apellidos VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT
        ua.usuario_area_id,
        p.nombres,
        p.apellidos
    FROM trd_usuario_area ua
    INNER JOIN mae_usuario u ON ua.usuario_id = u.usuario_id
    INNER JOIN mae_persona p ON u.persona_id = p.persona_id
    WHERE ua.area_id = id_area
      AND ua.estado = 'a';
END;
$$;