package com.paqueteria.paqueteria_backend.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimularServicio {
    private EnvioServicio envioServicio;

    public SimularServicio(EnvioServicio envioServicio) {
        this.envioServicio = envioServicio;
    }

    public void simular() throws JsonProcessingException {
        //OBTENER ENVIOS EN RUTA
        List<EnvioSimple> listaEnvios=this.getEnviosEnRuta();
        //AGRUPAR ENVIOS POR SUCURSAL
            //Calcular el peso total


    }


    private List<EnvioSimple> getEnviosEnRuta() throws JsonProcessingException {
        List<EnvioSimple> lista=this.envioServicio.getEnviosEnRuta("enRuta");
        // Crear un ObjectMapper (Jackson)
        ObjectMapper objectMapper = new ObjectMapper();

        // Convertir el objeto a JSON
        String json = objectMapper.writeValueAsString(lista);
        System.out.println("Hola");
        System.out.println(json);

        return  lista;

    }


    private List<Sucursal> calculoDeEnviosPorSucursal(){


    }


}
