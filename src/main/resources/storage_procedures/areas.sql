DELIMITER //
CREATE PROCEDURE sp_listar_areas(
    IN estado_area TINYINT,
    IN nombre_area VARCHAR(200)
)
BEGIN
	SELECT * FROM MAE_AREA
    WHERE
    (estado_area IS NULL OR ESTADO = estado_area)
    AND
    (nombre_area IS NULL OR DESCRIPCION LIKE CONCAT('%', nombre_area, '%'));
END //
DELIMITER ;

-- deshabilitar area por id
DELIMITER //
CREATE PROCEDURE sp_deshabilitar_area(
    IN id_area TINYINT    
)
BEGIN
	UPDATE mae_area SET estado = 0 WHERE area_id = id_area; 
END //
DELIMITER ;

-- habilitar area por id
DELIMITER //
CREATE PROCEDURE sp_habilitar_area(
    IN id_area TINYINT    
)
BEGIN
	UPDATE mae_area SET estado = 1 WHERE area_id = id_area; 
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_listar_usuarios_activos_por_area(
	IN id_area TINYINT
)
BEGIN
	SELECT 
    UA.USUARIO_AREA_ID,
    P.NOMBRES,
    P.APELLIDOS
    FROM TRD_USUARIO_AREA UA    
    INNER JOIN MAE_USUARIO U ON UA.USUARIO_ID = U.USUARIO_ID
    INNER JOIN MAE_PERSONA P ON U.PERSONA_ID = P.PERSONA_ID
    WHERE UA.AREA_ID = id_area AND UA.ESTADO = 'a';
END //
DELIMITER ;