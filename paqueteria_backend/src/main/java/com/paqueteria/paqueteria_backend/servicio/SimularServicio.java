package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimularServicio {
    private EnvioServicio envioServicio;

    public SimularServicio(EnvioServicio envioServicio) {
        this.envioServicio = envioServicio;
    }

    public void simular(){

    }


    private List<EnvioSimple> getEnviosEnRuta(){
        this.envioServicio.getEnviosEnRuta("En ruta");

    }


}
