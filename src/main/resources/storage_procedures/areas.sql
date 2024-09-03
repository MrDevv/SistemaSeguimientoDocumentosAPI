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