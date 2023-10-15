package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.djikstra.Djikstra;
import com.paqueteria.paqueteria_backend.djikstra.DjisktraTest;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;
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
    //private SucursalRepositorio sucursalRepositorio;

//localhost:8443/api/sucursales/get_sucursales

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param
     */
    public DjisktraControlador() {
        //this.sucursalRepositorio=sucursalRepositorio;

    }



    @GetMapping("/get_ruta")
    public String getRUta(HttpServletRequest request, HttpServletResponse response, String origen, String destino)  {
        try {
            //DjisktraTest optimizador= new DjisktraTest(origen, destino, this.sucursalRepositorio );

            return "funciona";
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }




}
