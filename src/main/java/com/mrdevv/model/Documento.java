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
@Table(name = "mae_documento")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="documento_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_id")
    TipoDocumento tipoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_area_id")
    UsuarioArea usuarioArea;

    @Column(name = "numero_documento")
    String numDocumento;

    String asunto;

    Integer folios;

    @CreationTimestamp
    Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "documento_estado_id")
    DocumentoEstado estado;
}
