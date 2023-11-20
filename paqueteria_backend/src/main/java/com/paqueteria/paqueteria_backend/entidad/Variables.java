package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "Variables_Mejora")
@Getter
@Setter
public class Variables {
    @Id
    @Column(name="idVariables_Mejora")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="valor")
    private int valor;



}
