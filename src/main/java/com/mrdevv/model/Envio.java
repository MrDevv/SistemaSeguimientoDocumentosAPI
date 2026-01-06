package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trs_envio")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "envio_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    Documento documento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_area_origen_id")
    UsuarioArea usuarioAreaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_area_destino_id")
    UsuarioArea usuarioAreaDestino;

    @ManyToOne
    @JoinColumn(name = "indicacion_id")
    Indicacion indicacion;

    Integer folios;

    String observacion;

    @CreationTimestamp
    Date fechaEnvio;

    @ManyToOne
    @JoinColumn(name = "documento_estado_id")
    DocumentoEstado documentoEstado;
}
