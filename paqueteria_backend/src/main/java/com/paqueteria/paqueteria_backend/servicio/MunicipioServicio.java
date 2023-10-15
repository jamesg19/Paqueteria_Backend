package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Municipio;
import com.paqueteria.paqueteria_backend.repositorio.MunicipioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioServicio {

    private MunicipioRepositorio municipioRepositorio;

    /**
     * Constructor
     * @param municipioRepositorio
     */
    public MunicipioServicio(MunicipioRepositorio municipioRepositorio) {
        this.municipioRepositorio = municipioRepositorio;
    }

    /**
     * Obtiene todos los municipios
     * @param
     * @return
     */

    public List<Municipio> obtenerMunicipiosDeptoId(int id){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Municipio> municipios=this.municipioRepositorio.findByDepartamentoId(id);
        //Optional<Municipio> municipios=this.municipioRepositorio.findById(id);

        return municipios;
    }

}
