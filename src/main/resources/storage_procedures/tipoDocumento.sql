DELIMITER //
CREATE PROCEDURE sp_listar_tipos_documento(
    IN tipo_doc_descripcion VARCHAR(100)
)
BEGIN
	SELECT * FROM MAE_TIPO_DOCUMENTO
    WHERE
    (tipo_doc_descripcion IS NULL OR DESCRIPCION LIKE CONCAT('%',tipo_doc_descripcion,'%'));    
END //
DELIMITER ;