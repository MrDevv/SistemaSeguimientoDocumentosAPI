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
@Table(name = "TRS_RECEPCION")
public class Recepcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECEPCION_ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "ENVIO_ID")
    Envio envio;

    @ManyToOne
    @JoinColumn(name = "USUARIO_AREA_ID")
    UsuarioArea usuarioArea;

    Date fechaRecepcion;

    @ManyToOne
    @JoinColumn(name = "DOCUMENTO_ESTADO_ID")
    DocumentoEstado estadoRecepcion;
}
