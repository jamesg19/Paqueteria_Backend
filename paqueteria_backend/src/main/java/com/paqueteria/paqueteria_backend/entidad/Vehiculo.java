package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Vehiculo")
@Getter
@Setter
public class Vehiculo {

    @Id
    @Column(name = "idVehiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "placa")
    private String placa;

    @Column(name = "tonelage")
    private String tonelage;

    @Column(name = "anio")
    private String anio;

    @Column(name = "noAsientos")
    private int noAsientos;

    @Column(name = "capacidadVol")
    private double capacidadVol;

    @Column(name = "capacidadTon")
    private double capacidadTon;


}
