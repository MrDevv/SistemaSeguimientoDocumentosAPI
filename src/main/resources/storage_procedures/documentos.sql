DELIMITER //
CREATE PROCEDURE sp_listar_documentos(
    IN estado_documento VARCHAR(30),
    IN numero_documento VARCHAR(200)
)
BEGIN
	SELECT 
    d.DOCUMENTO_ID, 
    d.NUMERO_DOCUMENTO,
    td.TIPO_DOCUMENTO_ID,
    td.DESCRIPCION 'TIPO DOCUMENTO',
    CONCAT(p.nombres, ' ' ,p.apellidos) 'USUARIO REGISTRADOR',
    a.DESCRIPCION 'AREA',
    d.ASUNTO,
    d.FOLIOS,
    d.FECHA_REGISTRO,
    de.DESCRIPCION 'ESTADO DOCUMENTO'
    FROM MAE_DOCUMENTO d
    INNER JOIN MAE_DOCUMENTO_ESTADO ed ON d.documento_estado_id = ed.documento_estado_id
    INNER JOIN MAE_TIPO_DOCUMENTO td ON d.tipo_documento_id = td.tipo_documento_id
    INNER JOIN TRD_USUARIO_AREA tua ON d.USUARIO_AREA_ID = tua.USUARIO_AREA_ID
    INNER JOIN MAE_USUARIO u ON tua.USUARIO_ID = u.USUARIO_ID
    INNER JOIN MAE_PERSONA p ON u.PERSONA_ID = p.PERSONA_ID
    INNER JOIN MAE_AREA a ON tua.AREA_ID = a.AREA_ID
    INNER JOIN MAE_DOCUMENTO_ESTADO de ON d.DOCUMENTO_ESTADO_ID = de.DOCUMENTO_ESTADO_ID
    WHERE
    (estado_documento IS NULL OR ed.DESCRIPCION LIKE CONCAT('%', estado_documento, '%'))
    AND
    (numero_documento IS NULL OR d.NUMERO_DOCUMENTO LIKE CONCAT('%', numero_documento, '%'));
END //
DELIMITER ;