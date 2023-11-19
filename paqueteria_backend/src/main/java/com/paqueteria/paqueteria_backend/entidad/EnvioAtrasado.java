package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Envio_Atrasadp")
@Getter
@Setter
public class EnvioAtrasado {
    @Id
    @Column(name="idEnvio_Atrasado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idEnvio")
    private int idEnvio;

    @Column(name = "idSucursal")
    private int idSucursal;
}
