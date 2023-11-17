package com.paqueteria.paqueteria_backend.entidad;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RutaOptima {

    private List<Sucursal> rutas;
    private double distancia;
}
