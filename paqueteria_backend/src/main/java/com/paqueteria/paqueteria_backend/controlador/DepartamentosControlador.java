package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.servicio.DepartamentoServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentosControlador {

//localhost:8443/api/sucursales/get_sucursales
    private DepartamentoServicio departamentoServicio;

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param departamentoServicio
     */
    public DepartamentosControlador(DepartamentoServicio departamentoServicio) {
        this.departamentoServicio = departamentoServicio;
    }


    @GetMapping("/get_departamentos")
    public List<Departamento> getDepartamentos(HttpServletRequest request, HttpServletResponse response)  {
        try {
            List<Departamento> departamentos =this.departamentoServicio.obtenerDepartamentos();

            return departamentos;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }


    @GetMapping("/get_departamento")
    public Optional<Departamento> getSucursales(HttpServletRequest request, HttpServletResponse response, @RequestParam int id)  {
        try {
            Optional<Departamento> departamento=this.departamentoServicio.obtenerDepartamentoId(id);

            return departamento;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }

    }


}
