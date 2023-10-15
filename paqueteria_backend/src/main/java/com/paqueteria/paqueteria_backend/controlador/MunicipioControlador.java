package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Municipio;
import com.paqueteria.paqueteria_backend.servicio.DepartamentoServicio;
import com.paqueteria.paqueteria_backend.servicio.MunicipioServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioControlador {

//localhost:8443/api/sucursales/get_sucursales
    private MunicipioServicio municipioServicio;

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param municipioServicio
     */
    public MunicipioControlador( MunicipioServicio municipioServicio) {
        this.municipioServicio = municipioServicio;
    }




    @GetMapping("/get_municipios")
    public List<Municipio> getSucursales(HttpServletRequest request, HttpServletResponse response, @RequestParam int id)  {
        try {
            List<Municipio> departamento=this.municipioServicio.obtenerMunicipiosDeptoId(id);

            return departamento;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }

    }


}
