package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.entidad.Sucursales;
import com.paqueteria.paqueteria_backend.servicio.SucursalServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalesControlador {

//localhost:8443/api/sucursales/get_sucursales
    private SucursalServicio sucursalServicio;

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param sucursalServicio
     */
    public SucursalesControlador(SucursalServicio sucursalServicio) {
        this.sucursalServicio = sucursalServicio;
    }

    /**
     * OBETNER UN OBJETO SUCURSAL POR ID
     * @param request
     * @param response
     * @param id
     * @return
     */
    @GetMapping("/get_sucursal")
    public Sucursales getSucursal(HttpServletRequest request, HttpServletResponse response, @RequestParam int id)  {
        try {
            Sucursales sucursal=this.sucursalServicio.obtenerSucursalId(id);

            return sucursal;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }

    }

    /**
     * Obtiene una lista de sucursales
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/get_sucursales")
    public List<Sucursales> getSucursales(HttpServletRequest request, HttpServletResponse response)  {
        try {
            List<Sucursales> sucursal=this.sucursalServicio.obtenerSucursales();

            return sucursal;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }

    }
}
