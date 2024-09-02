package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MAE_AREA")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AREA_ID")
    Long id;
    String descripcion;
    Boolean estado;
}
