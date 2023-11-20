package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.entidad.Municipio;
import com.paqueteria.paqueteria_backend.entidad.Ruta;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.RutaDto;
import com.paqueteria.paqueteria_backend.servicio.MunicipioServicio;
import com.paqueteria.paqueteria_backend.servicio.RutaServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Iterator;
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
    @GetMapping("/get_rutas_mapa")
    public List<Ruta> getRutasMapa(HttpServletRequest request, HttpServletResponse response)  {
        try {
            List<Ruta> rutass=this.rutaServicio.obtenerRutasMapa();
            int contador1 = 0;
            int contador2 = 0;
            // Crear un iterador para recorrer la lista
            Iterator<Ruta> iterator = rutass.iterator();
            List<Ruta> listaDeRutasFiltradas = rutass;
            while (iterator.hasNext()) {
                Ruta ruta = iterator.next();



                boolean esReverso = false;
                for (Ruta otraRuta : rutass) {
                    if (ruta.getOrigen().getIdSucursal() == otraRuta.getDestino().getIdSucursal() &&
                            ruta.getDestino().getIdSucursal() == otraRuta.getOrigen().getIdSucursal()) {
                        esReverso = true;
                        contador1+=1;
                        System.out.println("--");
                        System.out.println("Origen: "+ruta.getOrigen().getIdSucursal()+" Destino: "+ruta.getDestino().getIdSucursal());
                        System.out.println("Origen: "+otraRuta.getOrigen().getIdSucursal()+" Destino: "+otraRuta.getDestino().getIdSucursal());
                        System.out.println();
                        break;
                    }
                }
                if (esReverso==true) {
                    // Remover el elemento que cumple con las condiciones originales
                    System.out.println("Remueve");
                    iterator.remove();
                    listaDeRutasFiltradas.remove(ruta);
                }



                }
            System.out.println("Repetidos "+contador1);
            System.out.println(listaDeRutasFiltradas.size());
            return listaDeRutasFiltradas;

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

    @PostMapping("/save_rutas")
    public ResponseEntity<String> saveRutas(HttpServletRequest request, HttpServletResponse response, @RequestBody RutaDto ruta)  {
        try {
            return this.rutaServicio.saveRutas(ruta);


        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>("La ruta ya existe", HttpStatus.CONFLICT);
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

    @GetMapping("/getOD")
    public ResponseEntity<Ruta> getRutaOD(@RequestParam Sucursal origen, @RequestParam Sucursal destino) throws Error{
        return ResponseEntity.ok(this.rutaServicio.getByOD(origen,destino));
    }


}
