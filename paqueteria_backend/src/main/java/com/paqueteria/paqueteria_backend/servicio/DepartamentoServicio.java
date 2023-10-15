package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.repositorio.DepartamentoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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



    public List<Departamento> obtenerDepartamentos(){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Departamento> departamentos=this.departamentoRepositorio.findAll();

        return departamentos;
    }

    public Optional<Departamento> obtenerDepartamentoId(int id){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        Optional<Departamento> departamentos=this.departamentoRepositorio.findById(id);

        return departamentos;
    }

}
