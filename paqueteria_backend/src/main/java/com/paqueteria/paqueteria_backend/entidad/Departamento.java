package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="Departamento")
@Getter
@Setter
public class Departamento {
    
    @Id    
    @Column(name = "idDepartamento", length = 10, nullable = false)
    private String idDepartamento;

    @Column(name = "nombre", length = 145)
    private String nombre;

    
}
