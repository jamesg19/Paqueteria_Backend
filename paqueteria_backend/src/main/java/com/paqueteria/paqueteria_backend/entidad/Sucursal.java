package com.paqueteria.paqueteria_backend.entidad;

import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Sucursal")
@IdClass(SucursalId.class)
@Getter
@Setter
public class Sucursal {
    @Id
    @Column(name = "idSucursal", length = 10, nullable = false)
    private String idSucursal;

    @Id
    @Column(name = "idMunicipio", length = 10, nullable = false)
    private String idMunicipio;

    @Column(name = "idDepartamento", length = 10)
    private String idDepartamento;

    @Column(name = "direccion", length = 145)
    private String direccion;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Column(name = "esEnlace")
    private Boolean esEnlace;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @Id
    @JoinColumn(name = "idMunicipio", referencedColumnName = "idMunicipio", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_Sucursal_muni"))
    private Municipio municipio;

    @ManyToOne
    @Id
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_Sucursal_dep"))
    private Departamento departamento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return idSucursal.equals(sucursal.idSucursal) &&
               idMunicipio.equals(sucursal.idMunicipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSucursal, idMunicipio);
    }

}
