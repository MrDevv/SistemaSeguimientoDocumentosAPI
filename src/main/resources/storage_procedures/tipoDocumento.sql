CREATE OR REPLACE FUNCTION sp_listar_tipos_documento(
    v_tipo_doc_descripcion VARCHAR(100)
)
RETURNS TABLE(
	tipo_documento_id INT,
	descripcion VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
	RETURN QUERY
	SELECT td.tipo_documento_id, td.descripcion FROM mae_tipo_documento td
    WHERE
    (v_tipo_doc_descripcion IS NULL OR td.descripcion LIKE '%' || v_tipo_doc_descripcion || '%');    
END;
$$;