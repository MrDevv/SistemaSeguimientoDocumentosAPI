CREATE OR REPLACE FUNCTION sp_listar_documentos(
    v_estado_documento varchar(30),
    v_numero_documento varchar(200),
    v_usuario_area_id bigint,
    v_area_id bigint,
    v_fecha_inicio varchar,
    v_fecha_fin varchar
)
RETURNS TABLE (
	documento_id INT,
	numero_documento VARCHAR(200),
	tipo_documento_id INT,
	tipo_documento VARCHAR(200),
	usuario_registrador VARCHAR(255),
	area VARCHAR(200),
	asunto VARCHAR(255),
	folios INT,
	fecha_registro TIMESTAMP,
	estado_documento VARCHAR(100)
)
LANGUAGE plpgsql
AS $$
BEGIN
	RETURN QUERY
	SELECT 
    d.documento_id, 
    d.numero_documento,
    td.tipo_documento_id,
    td.descripcion,
    CONCAT(p.nombres, ' ' ,p.apellidos)::VARCHAR(100),
    a.descripcion,
    d.asunto,
    d.folios,
    d.fecha_registro,
    de.descripcion
    FROM mae_documento d
    INNER JOIN mae_documento_estado ed ON d.documento_estado_id = ed.documento_estado_id
    INNER JOIN mae_tipo_documento td ON d.tipo_documento_id = td.tipo_documento_id
    INNER JOIN trd_usuario_area tua ON d.usuario_area_id = tua.usuario_area_id
    INNER JOIN mae_usuario u ON tua.usuario_id = u.usuario_id
    INNER JOIN mae_persona p ON u.persona_id = p.persona_id
    INNER JOIN mae_area a ON tua.area_id = a.area_id
    INNER JOIN mae_documento_estado de ON d.documento_estado_id = de.documento_estado_id
    WHERE
    (v_estado_documento IS NULL OR ed.descripcion LIKE CONCAT('%', v_estado_documento, '%'))
    AND
    (v_numero_documento IS NULL OR d.numero_documento LIKE CONCAT('%', v_numero_documento, '%'))
    AND
    (v_usuario_area_id IS NULL OR tua.usuario_area_id = v_usuario_area_id)
    AND
    (v_area_id IS NULL OR a.area_id = v_area_id)
    AND
    (v_fecha_inicio IS NULL OR d.fecha_registro >= v_fecha_inicio::timestamp)
    AND
    (v_fecha_fin IS NULL OR d.fecha_registro < v_fecha_fin::timestamp);
END;
$$;
