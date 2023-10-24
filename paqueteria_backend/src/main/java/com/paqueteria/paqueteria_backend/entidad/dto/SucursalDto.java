package com.paqueteria.paqueteria_backend.entidad.dto;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Municipio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Sucursal")
@Getter
@Setter
public class SucursalDto {

    @Id
    @Column(name = "idSucursal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSucursal;

    //@JsonIgnoreProperties({"municipio"})

    @Column(name = "idDepartamento")
    private int departamento_id;


    @Column(name = "idMunicipio")
    private int municipio_id;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "esEnlace")
    private boolean esEnlace;

    @Column(name = "estado")
    private boolean estado;

    @Column(name = "longitud")
    private double longitud;

    @Column(name = "latitud")
    private double latitud;



}
