package com.paqueteria.paqueteria_backend.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Sucursal")
@Getter
@Setter
public class Sucursal {

    @Id
    @Column(name = "idSucursal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSucursal;

    @JsonIgnoreProperties({"municipio"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento")
    private Departamento departamento;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idMunicipio", referencedColumnName = "idMunicipio")
    private Municipio municipio;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "esEnlace")
    private boolean esEnlace;

    @Column(name = "estado")
    private boolean estado;

/*
    public SucursalDto getSucursalDto(){

        SucursalDto suc=new SucursalDto();
        suc.setDireccion(this.getDireccion());
        suc.setNombre(this.getNombre());
        suc.setEsEnlace(this.isEsEnlace());
        suc.setEstado(this.isEstado());

        suc.setDepartamento_id(this.getDepartamento().getId());
        suc.setDepartamento_id(this.getMunicipio().getId());

        return suc;
    }*/
}
