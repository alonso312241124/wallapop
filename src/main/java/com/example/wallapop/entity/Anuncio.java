package com.example.wallapop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="anuncios")
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "El precio debe ser positivo")
    private Double precio;

    @Column(length = 100)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    private LocalDateTime fechaCreacion;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anuncio")
    private List<FotoAnuncio> fotos = new ArrayList<>();
}