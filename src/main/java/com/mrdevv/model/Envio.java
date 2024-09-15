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
@Table(name = "TRS_ENVIO")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENVIO_ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "DOCUMENTO_ID")
    Documento documento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_AREA_ORIGEN_ID")
    UsuarioArea usuarioAreaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_AREA_DESTINO_ID")
    UsuarioArea usuarioAreaDestino;

    @ManyToOne
    @JoinColumn(name = "INDICACION_ID")
    Indicacion indicacion;

    Integer folios;

    String observacion;

    @CreationTimestamp
    Date fechaEnvio;
}
