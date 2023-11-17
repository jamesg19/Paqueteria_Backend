package com.paqueteria.paqueteria_backend.entidad.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="envio")
@Getter
@Setter

public class EnvioSimple {
    @Id
    @Column(name = "idEnvio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="idSucursalOrigen")
    private long idSucursalOrigen;

    @Column(name="idSucursalDestino")
    private long idSucursalDestino;

    @Column(name="nitEmisor")
    private long nitEmisor;

    @Column(name="nitReceptor")
    private long nitReceptor;

    @Column(name="estado")
    private long estado;

    @Column(name="fechaLlegada")
    private LocalDateTime fecha;

    @Column(name="subTotal")
    private double subTotal;
}
