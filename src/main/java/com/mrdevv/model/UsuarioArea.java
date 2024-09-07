package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TRD_USUARIO_AREA")
public class UsuarioArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_AREA_ID")
    Long id;
    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "AREA_ID")
    Area area;
    Date fechaIngreso;
    Date fechaSalida;
    Character estado;

    public static String convertirEstado(Character estado){
        switch (estado) {
            case 'a':
                return "activo";
            case 'i':
                return "inactivo";
            case 's':
                return "suspendido";
            default:
                return "estado desconocido";
        }
    }
}
