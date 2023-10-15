package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Municipio;
import com.paqueteria.paqueteria_backend.entidad.Ruta;
import com.paqueteria.paqueteria_backend.repositorio.MunicipioRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.RutaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutaServicio {

    private RutaRepositorio rutaRepositorio;

    /**
     * Constructor
     * @param rutaRepositorio
     */
    public RutaServicio(RutaRepositorio rutaRepositorio) {
        this.rutaRepositorio = rutaRepositorio;
    }

    /**
     * Obtiene todas las rutas
     * @param
     * @return
     */

    public List<Ruta> obtenerRutas(){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Ruta> rutas=this.rutaRepositorio.findAll();


        return rutas;
    }

    public Optional<Ruta> obtenerRutaId(int id){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        Optional<Ruta> rutas=this.rutaRepositorio.findById(id);


        return rutas;
    }

}