package com.paqueteria.paqueteria_backend.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Simulacion {
    private List<Envio> envios;
    private List<HistoricoSucursal> historicoSucursal;
    private List<HistorialVehiculo> historicoVehiculo;
    private List<EnvioAtrasado> envioAtrasado;

}
