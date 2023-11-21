package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ReportesTem")
@Getter
@Setter

public class ReporteSimulacion {
    //origen, destino, vehiculo , #paquetes, ocupacion
    @Id
    @Column(name="idReportesTem")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="origen")
    private int origen;

    @Column(name="destino")
    private int destino;

    @Column(name="vehiculo")
    private int vehiculo;

    @Column(name="numeroEnviados")
    private int numeroEnviados;

    @Column(name="ocupacion")
    private int ocupacion;




}
