CREATE OR REPLACE FUNCTION sp_listar_indicaciones(
	v_indicacion_descripcion VARCHAR(100)
)
RETURNS TABLE (
	indicacion_id INT,
	descripcion VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
	RETURN QUERY
	SELECT i.indicacion_id, i.descripcion FROM MAE_INDICACION i
    WHERE 
    (v_indicacion_descripcion IS NULL OR i.descripcion ILIKE '%' || v_indicacion_descripcion || '%');    
END;
$$;