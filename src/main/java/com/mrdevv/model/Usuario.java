package com.mrdevv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MAE_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_ID")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONA_ID")
    Persona persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROL_ID")
    Rol rol;

    String nombreUsuario;

    String password;

    @Builder.Default
    @Column(insertable = false, updatable = false)
    Boolean estado = true;

    public void generarUserName(){
        String username = "";

        Character firstCharacter = persona.getNombres().charAt(0);
        String[] apellidos = persona.getApellidos().split(" ");
        Character secondCharacter = ' ';
        String dni = persona.getDni().substring(0, 2);

        if (apellidos.length > 1){
            secondCharacter = apellidos[1].charAt(0);
        }

        nombreUsuario = username.concat(firstCharacter.toString())
                .concat(apellidos[0])
                .concat(secondCharacter.toString())
                .concat(dni).toLowerCase();
    }
}
