package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.entidad.Municipio;
import com.paqueteria.paqueteria_backend.entidad.Ruta;
import com.paqueteria.paqueteria_backend.servicio.MunicipioServicio;
import com.paqueteria.paqueteria_backend.servicio.RutaServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rutas")
public class RutasControlador {

//localhost:8443/api/sucursales/get_sucursales
    private RutaServicio rutaServicio;

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param rutaServicio
     */
    public RutasControlador(RutaServicio rutaServicio) {
        this.rutaServicio = rutaServicio;
    }




    @GetMapping("/get_rutas")
    public List<Ruta> getRutas(HttpServletRequest request, HttpServletResponse response)  {
        try {
            List<Ruta> rutas=this.rutaServicio.obtenerRutas();

            return rutas;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }

    }

    @PostMapping("/save_ruta")
    public ResponseEntity<String> saveRuta(HttpServletRequest request, HttpServletResponse response, @RequestBody Ruta ruta)  {
        try {
            return this.rutaServicio.saveRuta(ruta);


        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>("", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/get_ruta_id")
    public Optional<Ruta> getRutaId(HttpServletRequest request, HttpServletResponse response, @RequestParam int id)  {
        try {
            Optional<Ruta> rutas=this.rutaServicio.obtenerRutaId(id);

            return rutas;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }

    }


}
