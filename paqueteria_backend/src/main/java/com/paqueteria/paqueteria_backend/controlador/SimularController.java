package com.paqueteria.paqueteria_backend.controlador;

import com.paqueteria.paqueteria_backend.entidad.Simulacion;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.servicio.EnvioServicio;
import com.paqueteria.paqueteria_backend.servicio.SimularServicio;
import com.paqueteria.paqueteria_backend.servicio.SucursalServicio;
import com.paqueteria.paqueteria_backend.servicio.VehiculoServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/simular")
public class SimularController {
    private SimularServicio simularServicio;
    @Autowired
    EnvioServicio envioServicio;
    @Autowired
    VehiculoServicio vehiculoServicio;
    public SimularController(SimularServicio simularServicio) {
        this.simularServicio=simularServicio;
    }

    @GetMapping("/simular")
    public ResponseEntity<Simulacion> getSucursalId(HttpServletRequest request, HttpServletResponse response)  {
        Simulacion retorno = new Simulacion();
        retorno.setEnvios(this.envioServicio.getAllEnvio());
        retorno.setHistoricoSucursal(this.envioServicio.getAllHistoricoSucural());
        retorno.setHistoricoVehiculo(this.vehiculoServicio.getAllHistorico());
        retorno.setEnvioAtrasado(this.envioServicio.getAllEnvioAtrasado());
        this.simularServicio.simular();
        return ResponseEntity.ok(retorno);
    }
}
