package com.paqueteria.paqueteria_backend.entidad;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="Historico_Sucursales")
@Getter
@Setter

public class HistoricoSucursal {
    @Id
    @Column(name="idHistorico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="idEnvio")
    private int idEnvio;

    @Column(name="idSucursal")
    private int idSucursal;

    @Column(name="idVehiculo")
    private int idVehiculo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha")
    private LocalDate fecha;
}
