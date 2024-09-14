DELIMITER //
CREATE PROCEDURE sp_listar_indicaciones(
	IN indicacion_descripcion VARCHAR(100)
)
BEGIN
	SELECT * FROM MAE_INDICACION 
    WHERE 
    (indicacion_descripcion IS NULL OR DESCRIPCION LIKE CONCAT('%', indicacion_descripcion, '%'));    
END //
DELIMITER ;