package com.paqueteria.paqueteria_backend.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Envio")
@Getter
@Setter
public class Envio {
    @Id
    @Column(name="idEnvio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="idSucursalOrigen", referencedColumnName = "idSucursal")
    private  Sucursal sucursalOrigen;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="idSucursalDestino", referencedColumnName = "idSucursal")
    private Sucursal sucursalDestino;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="nitEmisor", referencedColumnName = "nit")
    private  Persona personaEmisor;


    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="nitReceptor", referencedColumnName = "nit")
    private  Persona personaReceptor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha")
    private LocalDate fecha;

    @Nullable
    @Column(name="total")
    private double subTotal;

    @Nullable
    @Column(name = "peso")
    private double peso;

    @Nullable
    @Column(name = "volumen")
    private double volumen;

    @Nullable
    @Column(name = "diasTranscurridos")
    private int diasTranscurridos;

    @Nullable
    @Column(name = "estado")
    private String estado;

}
