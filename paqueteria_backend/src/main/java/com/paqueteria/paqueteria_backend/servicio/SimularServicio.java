package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.*;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import com.paqueteria.paqueteria_backend.entidad.dto.ResponseListEnvioSimple;
import com.paqueteria.paqueteria_backend.repositorio.CantidadEnvioPorSucursalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import com.paqueteria.paqueteria_backend.djikstra.Djikstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SimularServicio {
    private EnvioServicio envioServicio;
    private CantidadEnvioPorSucursalRepositorio cantidadEnvioPorSucursal;
    private Djikstra dijstraAlgoritmo;
    @Autowired
    private SucursalServicio sucursalService;

    public SimularServicio(EnvioServicio envioServicio) {
        this.envioServicio = envioServicio;
        //this.cantidadEnvioPorSucursal=cantidadEnvioPorSucursal;

    }
    //LOGICA DE SIMULACION DE ENVIOS by James
    public void simular(){
        int cantidadMinima=3;
        //OBTIENE LA LISTA DE ENVIO EN RUTA
        List<EnvioSimple> lista=this.getEnviosEnRuta();

        //List<EnvioSimple> enviosDisponibles= new ArrayList<>();
        //List<EnvioSimple> enviosStandBy= new ArrayList<>();
        //ResponseListEnvioSimple response = new ResponseListEnvioSimple();
        /**
         * VERIFICAR SI LOS ENVIOS PROCEDEN (SEGUN EL MONTO MINIMO) SI NO CUMPLEN CON EL MONTO MINIMO DESCARTA LOS ENVIOS ES DECIR NO HARA EL ENVIO
         */
        //response=this.verificarMontoMinimoEnvio(lista,3);
        //enviosDisponibles=response.getDisponibles();
        //enviosStandBy=response.getStandBy();
        /*
        * Itera sobre el listado filtrado por enRuta, luego se recorre ese listado si en el historico se encuentra que esta
        * en la misma sucural de destino se cambia el estado del envio a etregado
        * */
        List<Envio> posiblesMovimientos = new ArrayList<>();
        lista.stream().forEach(data->{
            Envio envioAux = this.envioServicio.obtenerEnvioId(data.getId());
            HistoricoSucursal auxHistorico = this.envioServicio.getHistorico(envioAux.getId()).get(0);
            if(envioAux.getSucursalDestino().getIdSucursal() == auxHistorico.getIdSucursal()){
                envioAux.setEstado("entregado");
                this.envioServicio.saveEnvio(envioAux);
            }
            else{
                List<PasosEnvio> pasosEnvioList = this.envioServicio.getPasosEnvio(envioAux.getId());
                int indice= IntStream.range(0,pasosEnvioList.size()).filter(i->pasosEnvioList.get(i).getIdSucursal()==(auxHistorico.getIdSucursal())).findFirst().orElse(-1);
                int nextSucursal = indice!=-1 && indice < pasosEnvioList.size() - 1 ? indice + 1:-1;
                Optional<Sucursal> sucursalNext = sucursalService.obtenerSucursalId(nextSucursal);
                if(sucursalNext.isPresent()){
                    if(sucursalNext.get().isEstado())posiblesMovimientos.add(envioAux);
                    else {
                        EnvioAtrasado envR = EnvioAtrasado.builder().idEnvio(envioAux.getId()).idSucursal(auxHistorico.getIdSucursal()).build();
                        this.envioServicio.saveEnvioAtrasado(envR);
                        envioAux.setDiasTranscurridos(envioAux.getDiasTranscurridos()+1);
                        this.envioServicio.saveEnvio(envioAux);
                        //Aumentar diasTranscurrido a paquete y al historicoEnvio sobre la sucursal que no se mueve
                    }
                }
            }
        });
        if(posiblesMovimientos.size()>0){
            var colector = posiblesMovimientos.stream().collect(
                    Collectors.groupingBy(
                            envio->envio.getSucursalOrigen().getIdSucursal(),
                            Collectors.groupingBy(envio -> envio.getSucursalDestino().getIdSucursal())
                    )
            );

            colector.forEach((idOrigen,enviosDestino)->{
                System.out.println("Sucursal origen :"+idOrigen+" con envios a : ");
                enviosDestino.forEach((idDestino,envios)->{
                    System.out.println("\t\t -"+idDestino+" tiene los siguientes envios");
                    double cantidadPeso = envios.stream().mapToDouble(Envio::getPeso).sum();
                    Optional<Vehiculo>
                });
            });
            System.out.println("SSD");
        }
        //this.verificarMontoMinimoEnvio(lista,0);

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
