package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cantidad_envio_por_sucursal")
@Getter
@Setter
public class CantidadEnvioPorSucursal {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="idSucursalOrigen")
    private int idSucursalOrigen;

    @Column(name="idSucursalDestino")
    private int idSucursalDestino;

    @Column(name="cantidadEnvios")
    private int cantidadEnvios;

}
