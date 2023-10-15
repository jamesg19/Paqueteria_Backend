package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Ruta")
@Getter
@Setter
public class Ruta {

    @Id
    @Column(name = "idRuta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSucursalOrigen", referencedColumnName = "idSucursal")
    private Sucursal origen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSucursalDestino", referencedColumnName = "idSucursal")
    private Sucursal destino;

    @Column(name = "distancia")
    private double distancia;

    @Column(name = "dias")
    private int dias;

    @Column(name = "costo")
    private double costo;


}
