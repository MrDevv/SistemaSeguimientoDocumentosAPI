package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trs_recepcion")
public class Recepcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recepcion_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "envio_id")
    Envio envio;

    @ManyToOne
    @JoinColumn(name = "usuario_area_id")
    UsuarioArea usuarioArea;

    Date fechaRecepcion;

    @ManyToOne
    @JoinColumn(name = "documento_estado_id")
    DocumentoEstado estadoRecepcion;
}
