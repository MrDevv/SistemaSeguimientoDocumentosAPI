package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MAE_DOCUMENTO")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DOCUMENTO_ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "TIPO_DOCUMENTO_ID")
    TipoDocumento tipoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_AREA_ID")
    UsuarioArea usuarioArea;

    @Column(name = "NUMERO_DOCUMENTO")
    String numDocumento;

    String asunto;

    Integer folios;

    @CreationTimestamp
    Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "DOCUMENTO_ESTADO_ID")
    DocumentoEstado estado;
}
