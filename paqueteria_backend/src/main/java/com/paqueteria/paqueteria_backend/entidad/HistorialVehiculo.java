package com.paqueteria.paqueteria_backend.entidad;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table (name = "Historial_Sucursales_Vehiculo")
@Getter
@Setter
public class HistorialVehiculo {
    @Id
    @Column(name = "idHistorial_Sucursales_Vehiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idVehiculo")
    private int idVehiculo;

    @Column(name = "idSucursal")
    private int idSucursal;

    @Column(name = "ocupacion")
    private double ocupacion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha")
    private LocalDate fecha;


}
