package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.CantidadEnvioPorSucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import com.paqueteria.paqueteria_backend.entidad.dto.ResponseListEnvioSimple;
import com.paqueteria.paqueteria_backend.repositorio.CantidadEnvioPorSucursalRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimularServicio {
    private EnvioServicio envioServicio;
    private CantidadEnvioPorSucursalRepositorio cantidadEnvioPorSucursal;

    public SimularServicio(EnvioServicio envioServicio,CantidadEnvioPorSucursalRepositorio cantidadEnvioPorSucursal) {
        this.envioServicio = envioServicio;
        this.cantidadEnvioPorSucursal=cantidadEnvioPorSucursal;

    }
    //LOGICA DE SIMULACION DE ENVIOS by James
    public void simular(){
        //OBTIENE LA LISTA DE ENVIO EN RUTA
        List<EnvioSimple> lista=this.getEnviosEnRuta();

        List<EnvioSimple> enviosDisponibles= new ArrayList<>();
        List<EnvioSimple> enviosStandBy= new ArrayList<>();
        ResponseListEnvioSimple response = new ResponseListEnvioSimple();
        /**
         * VERIFICAR SI LOS ENVIOS PROCEDEN (SEGUN EL MONTO MINIMO) SI NO CUMPLEN CON EL MONTO MINIMO DESCARTA LOS ENVIOS ES DECIR NO HARA EL ENVIO
         */
        response=this.verificarMontoMinimoEnvio(lista,3);
        enviosDisponibles=response.getDisponibles();
        enviosStandBy=response.getStandBy();

        //ITERAR CADA ENVIO SEGUN SU LISTA en PasosEnvio

    }



    /**
     * Obtiene todos los envios con estado "enRuta"
     * @return
     */
    private List<EnvioSimple> getEnviosEnRuta(){
        return  this.envioServicio.getEnviosEnRuta("enRuta");
    }




    /**
     * DEVUELVE UNA LISTA DE LOS PAQUETES APTOS PARA EL ENVIO y UNA LISTA DE LOS ENVIOS EN STAND BY
     * ES DECIR MAYORES AL MINIMO PARA QUE SE PUEDAN ENTREGAR
     * @param listaEnviosDisponibles
     * @return
     */
    private ResponseListEnvioSimple verificarMontoMinimoEnvio(List<EnvioSimple> listaEnviosDisponibles, int cantidadMin){
        //Obtiene la cantidad de envios que van desde el origen hacia un destino
        List<CantidadEnvioPorSucursal> listaDeCantidadPorSucursal=this.cantidadEnvioPorSucursal.findAll();
        List<EnvioSimple> enviosStandBy= new ArrayList<>();
        ResponseListEnvioSimple response = new ResponseListEnvioSimple();

        for (CantidadEnvioPorSucursal item : listaDeCantidadPorSucursal) {

            System.out.println("Cantidad de Envíos: " + item.getCantidadEnvios());
            //OBTIENE LA CANTIDAD DE ENVIOS
            int cantidad=item.getCantidadEnvios();
            //si es menor de 5 entonces la sucursal no tiene suficientes articulos para enviar
            if(cantidad<cantidadMin){
                // Utilizando stream y filter para buscar en la lista
                List<EnvioSimple> enviosEncontrados = listaEnviosDisponibles.stream()
                        .filter(envio -> envio.getIdSucursalOrigen() == item.getIdSucursalOrigen())
                        .toList();

                enviosStandBy.addAll(enviosEncontrados);
                listaEnviosDisponibles.removeAll(enviosEncontrados);
            }


        }
        for(EnvioSimple item: listaEnviosDisponibles){
            System.out.println("------");
            System.out.println(item.getId()+" "+item.getIdSucursalOrigen());
            System.out.println();
        }
        response.setDisponibles(listaEnviosDisponibles);
        response.setStandBy(enviosStandBy);
        return response;
    }




    /**
     * RECORRE LA LISTA EN PASOS ENVIO PARA
     * @param lista
     */
    private void recorrerRuta(List<EnvioSimple> lista){

    }


}
