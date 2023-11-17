package com.paqueteria.paqueteria_backend.controlador;

import com.paqueteria.paqueteria_backend.entidad.Sucursal;
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


    @GetMapping("/simular")
    public void getSucursalId(HttpServletRequest request, HttpServletResponse response)  {
        //logica de simular
        //1) OBTENER ENVIOS CON ESTADO EN RUTA
        //2) 

    }
}
