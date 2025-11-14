package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trd_usuario_area")
public class UsuarioArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_area_id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "area_id")
    Area area;

    @CreationTimestamp
    @Column(updatable = false, insertable = false)
    Date fechaIngreso;
    Date fechaSalida;

    @Builder.Default
    @Column(insertable = false, updatable = false)
    String estado =  "a";

    public static String convertirEstado(String estado){
        System.out.println(estado);
        switch (estado) {
            case "a":
                return "activo";
            case "i":
                return "inactivo";
            case "s":
                return "suspendido";
            default:
                return "estado desconocido";
        }
    }
}
