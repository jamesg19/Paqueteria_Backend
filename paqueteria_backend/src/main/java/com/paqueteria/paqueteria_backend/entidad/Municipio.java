package com.paqueteria.paqueteria_backend.entidad;

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
    private String idMunicipio;

    @Id
    @Column(name = "idDepartamento", length = 10, nullable = false)
    private String idDepartamento;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @ManyToOne
    @Id
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_Municipio_Dep"))
    private Departamento departamento;

}
