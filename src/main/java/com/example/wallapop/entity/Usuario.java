package com.example.wallapop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El email es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @Length(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
    @Column(length = 500)
    private String password;

    private String nombre;

    private String telefono;

    private String poblacion;

}
