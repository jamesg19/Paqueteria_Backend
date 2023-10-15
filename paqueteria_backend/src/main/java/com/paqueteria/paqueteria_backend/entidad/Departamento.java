package com.paqueteria.paqueteria_backend.entidad;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Departamento")
@Getter
@Setter
public class Departamento {
    
    @Id    
    @Column(name = "idDepartamento")
    private int id;

    @Column(name = "nombre", length = 145)
    private String nombre;

    /*@JsonManagedReference
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "departamento",fetch = FetchType.EAGER)
    private Set<Municipio> municipio = new HashSet<>();*/
    @JsonManagedReference
    @OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.MERGE, mappedBy = "departamento")
    private Set<Municipio> municipio = new HashSet<>();

    
}
