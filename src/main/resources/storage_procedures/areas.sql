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