package com.paqueteria.paqueteria_backend.entidad.dto;

import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Ruta")
@Getter
@Setter
public class RutaDto {

    @Id
    @Column(name = "idRuta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "idSucursalOrigen")
    private int origen;


    @Column(name = "idSucursalDestino")
    private int destino;

    @Column(name = "distancia")
    private double distancia;

    @Column(name = "dias")
    private int dias;

    @Column(name = "costo")
    private double costo;


}
