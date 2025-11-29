package com.mrdevv.repository;

import com.mrdevv.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByNombreUsuario(String username);

    @Modifying
    @Query(value = "update mae_usuario set nombre_usuario = :username where usuario_id = :id", nativeQuery = true)
    void updateUserName(Long id, String username);
}
