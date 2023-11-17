package com.paqueteria.paqueteria_backend.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.servicio.SimularServicio;
import com.paqueteria.paqueteria_backend.servicio.SucursalServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/simular")
public class SimularController {
    private SimularServicio simularServicio;
    public SimularController(SimularServicio simularServicio) {
        this.simularServicio=simularServicio;
    }

    @GetMapping("/simular")
    public void getSucursalId(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        //logica de simular
        //1) OBTENER ENVIOS CON ESTADO EN RUTA
        //2)
        System.out.println("Entra");
        this.simularServicio.simular();
    }
}
