package com.paqueteria.paqueteria_backend.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="Municipio")
@Getter
@Setter
public class Municipio {
    @Id
    @Column(name = "idMunicipio", length = 10, nullable = false)
    private int id;



    @Column(name = "nombre", length = 45)
    private String nombre;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="idDepartamento")
    private Departamento departamento;

}
