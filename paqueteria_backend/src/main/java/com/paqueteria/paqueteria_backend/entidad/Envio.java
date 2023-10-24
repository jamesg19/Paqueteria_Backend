package com.paqueteria.paqueteria_backend.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @JoinColumn(name="nitEmisor", referencedColumnName = "idPersona")
    private  Persona personaEmisor;


    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="nitReceptor", referencedColumnName = "idPersona")
    private  Persona personaReceptor;

    @Column(name="fechaLlegada")
    private LocalDateTime fechaLlegada;

    @Column(name="subTotal")
    private double subTotal;


}
