package com.paqueteria.paqueteria_backend.entidad.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="envio")
@Getter
@Setter

public class EnvioSimple {
    @Id
    @Column(name = "idEnvio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="idSucursalOrigen")
    private long idSucursalOrigen;

    @Column(name="idSucursalDestino")
    private long idSucursalDestino;

    @Column(name="nitEmisor")
    private long nitEmisor;

    @Column(name="nitReceptor")
    private long nitReceptor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha")
    private LocalDate fecha;

    @Column(name="total")
    private double subTotal;

    @Column(name = "peso")
    private double peso;

    @Column(name = "volumen")
    private double volumen;

    
    @Column(name = "diasTranscurridos")
    private int diasTranscurridos;


    @Column(name = "estado")
    private String estado;
}
