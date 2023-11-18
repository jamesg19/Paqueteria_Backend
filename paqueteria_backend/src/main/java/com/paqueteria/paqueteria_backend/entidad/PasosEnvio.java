package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Pasos_Envio")
@Getter
@Setter
public class PasosEnvio {
    @Id
    @Column(name="idPasos_Envio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="IdEnvio")
    private int idEnvio;

    @Column(name="IdSucursal")
    private int idSucursal;

}
