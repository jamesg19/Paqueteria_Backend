package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.djikstra.Djikstra;
import com.paqueteria.paqueteria_backend.djikstra.DjisktraTest;
import com.paqueteria.paqueteria_backend.entidad.Ruta;
import com.paqueteria.paqueteria_backend.entidad.RutaOptima;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;
import com.paqueteria.paqueteria_backend.servicio.RutaServicio;
import com.paqueteria.paqueteria_backend.servicio.SucursalServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ruta_optima")
public class DjisktraControlador {
    private SucursalServicio sucursalServicio;
    private RutaServicio rutaServicio;
    private Djikstra djikstra;

//http://localhost:8443/api/ruta_optima/get_ruta?origen=2&destino=3

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param
     */
    public DjisktraControlador(SucursalServicio sucursalServicio,RutaServicio rutaServicio) {
        this.sucursalServicio=sucursalServicio;
        this.rutaServicio = rutaServicio;
        this.djikstra = new Djikstra();
    }


    //si deviuelve vacio no esta activa
    //si devuelve solo el destino es poque no existe
    @GetMapping("/get_ruta")
    public RutaOptima getRUta(HttpServletRequest request, HttpServletResponse response, String origen, String destino)  {
        try {
            RutaOptima rutaOptimaResponse=new RutaOptima();

            List<Sucursal> sucursales = this.sucursalServicio.obtenerSucursalesEstado(true);
            List<Ruta> rutas = this.rutaServicio.obtenerRutas();                        

            /*String comoseVeElGrafico = "";
            for (int i = 0; i < graph.length; i++) {
                // Recorre cada elemento de la fila actual
                for (int j = 0; j < graph[i].length; j++) {
                    comoseVeElGrafico += graph[i][j] + " "; // Imprime el valor y un espacio en blanco
                }
                comoseVeElGrafico += '\n'; // Cambia de línea después de imprimir una fila completa
            }*/
            
            List<Sucursal> valRuta = this.djikstra.buscarMejorRuta(sucursales, rutas, Integer.parseInt(origen), Integer.parseInt(destino));
            rutaOptimaResponse.setRutas(valRuta);
            rutaOptimaResponse.setDistancia(djikstra.getDistanciaRecorrida());
            /*
            System.out.println("Inicio de ruta");
            for (Sucursal sucursal : valRuta) {
                System.out.println("Sucursal ID: " + sucursal.getIdSucursal());
            }
            System.out.println("Fin de ruta");
             */

            System.out.println("funciona: " + origen + " , " + destino + "Ruta Optima: ");
            return rutaOptimaResponse;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }    

    @GetMapping("/set_cambio_ruta")
    public String getRUta(HttpServletRequest request, HttpServletResponse response)  {
        try {
            this.djikstra.setHuboCambio(true);
            return "Ruta Cambiada";
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }   




}
