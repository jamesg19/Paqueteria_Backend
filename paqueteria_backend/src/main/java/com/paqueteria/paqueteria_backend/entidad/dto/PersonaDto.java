package com.paqueteria.paqueteria_backend.entidad.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Persona")
@Getter
@Setter

public class PersonaDto {
    @Id
    @Column(name = "idPersona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="password")
    private String password;

    @Column(name="rol")
    private String rol;



}
