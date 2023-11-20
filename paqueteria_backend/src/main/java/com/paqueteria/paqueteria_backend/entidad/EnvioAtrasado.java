package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Envio_Atrasado")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
