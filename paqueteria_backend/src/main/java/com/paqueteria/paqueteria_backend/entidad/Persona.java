package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Persona")
@Getter
@Setter
public class Persona {
    @Id
    @Column(name="idPersona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="edad")
    private int edad;

    @Column(name="domicilio")
    private String domicilio;

    @Column(name="celular")
    private String celular;

    @Column(name="nit")
    private String nit;
}
