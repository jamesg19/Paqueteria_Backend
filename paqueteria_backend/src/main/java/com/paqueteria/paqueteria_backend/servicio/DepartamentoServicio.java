package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.repositorio.DepartamentoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServicio {

    private DepartamentoRepositorio departamentoRepositorio;

    /**
     * Constructor
     * @param departamentoRepositorio
     */
    public DepartamentoServicio(DepartamentoRepositorio departamentoRepositorio) {
        this.departamentoRepositorio = departamentoRepositorio;
    }

    /**
     * Obtiene todos los municipios
     * @param
     * @return
     */

    public List<Departamento> obtenerDepartamentos(){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Departamento> departamentos=this.departamentoRepositorio.findAll();

        return departamentos;
    }

}
