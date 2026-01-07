package com.mrdevv.repository;

import com.mrdevv.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    @Query(value = "select * from trs_envio where documento_id = :documento_id order by fecha_envio desc limit 1", nativeQuery = true)
    Envio getUltimoEnvioByIdDocumento(@Param("documento_id") Long documentoId);

}
