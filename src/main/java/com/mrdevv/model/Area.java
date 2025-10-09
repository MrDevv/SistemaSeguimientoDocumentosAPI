package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mae_area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="area_id")
    Long id;

    String descripcion;

    @Builder.Default
    @Column(insertable = false, updatable = false)
    Boolean estado = true;
}
