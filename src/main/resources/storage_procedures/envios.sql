create or replace function sp_listar_envios(
	v_numero_documento varchar,
	v_usuario_area_id bigint,
	v_area_id bigint,
	v_fecha_inicio varchar,
	v_fecha_fin varchar
)
returns table(
	envio_id integer,
	numero_documento varchar,
	tipo_documento varchar,
	folios integer,
	indicacion varchar,
	usuario_origen varchar,
	area_origen varchar,
	usuario_destino varchar,
	area_destino varchar,
	obervacion text,
	fecha_envio timestamp,
	fecha_recepcion timestamp,
	estado_envio varchar,
	estado_recepcion varchar,
	estado_documento varchar
)
language 'plpgsql'
as $$
begin
	return query
	select
		e.envio_id,
		d.numero_documento,
		td.descripcion "tipo_documento",
		e.folios,
		i.descripcion "indicacion",
		concat(po.nombres, ' ', po.apellidos)::varchar,
		ao.descripcion "area_origen",
		concat(pd.nombres, ' ', pd.apellidos)::varchar,		
		ad.descripcion "area_destino",
		e.observacion,
		e.fecha_envio,
		r.fecha_recepcion,
		dee.descripcion "estado_envio",
		der.descripcion "estado_recepcion",
		ded.descripcion "estado_documento"
	from trs_envio e
	inner join trs_recepcion r on e.envio_id = r.envio_id
	inner join mae_documento d on e.documento_id = d.documento_id
	inner join mae_indicacion i on e.indicacion_id = i.indicacion_id
	inner join mae_tipo_documento td on d.tipo_documento_id = td.tipo_documento_id
	-- datos del usuario origen
	inner join trd_usuario_area uao on e.usuario_area_origen_id = uao.usuario_area_id
	inner join mae_usuario uo on uao.usuario_id = uo.usuario_id
	inner join mae_persona po on uo.persona_id = po.persona_id
	-- datos del area origen
	inner join mae_area ao on uao.area_id = ao.area_id
	-- datos del usuario destino
	inner join trd_usuario_area uad on e.usuario_area_destino_id = uad.usuario_area_id
	inner join mae_usuario ud on uad.usuario_id = ud.usuario_id
	inner join mae_persona pd on ud.persona_id = pd.persona_id
	-- datos del area destino
	inner join mae_area ad on uad.area_id = ad.area_id
	-- datos estados del documento
	inner join mae_documento_estado ded on d.documento_estado_id = ded.documento_estado_id
	inner join mae_documento_estado der on r.documento_estado_id = der.documento_estado_id
	inner join mae_documento_estado dee on e.documento_estado_id = dee.documento_estado_id
	where 
		(v_numero_documento IS NULL OR d.numero_documento LIKE CONCAT('%', v_numero_documento, '%'))
	    and
	    (v_usuario_area_id IS NULL OR uao.usuario_area_id = v_usuario_area_id)
	    and
	    (v_area_id IS NULL OR uao.area_id = v_area_id)
	    and
	    (v_fecha_inicio IS NULL OR e.fecha_envio >= v_fecha_inicio::timestamp)
	    and
	    (v_fecha_fin IS NULL OR e.fecha_envio < v_fecha_fin::timestamp);
end;
$$