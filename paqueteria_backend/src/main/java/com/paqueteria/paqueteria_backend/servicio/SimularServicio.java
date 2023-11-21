package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.*;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import com.paqueteria.paqueteria_backend.entidad.dto.ResponseListEnvioSimple;
import com.paqueteria.paqueteria_backend.entidad.dto.VehiculoDto;
import com.paqueteria.paqueteria_backend.repositorio.CantidadEnvioPorSucursalRepositorio;
import jakarta.persistence.criteria.CriteriaBuilder;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import com.paqueteria.paqueteria_backend.djikstra.Djikstra;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SimularServicio {
    private EnvioServicio envioServicio;
    private CantidadEnvioPorSucursalRepositorio cantidadEnvioPorSucursal;
    private Djikstra dijstraAlgoritmo;
    @Autowired
    private SucursalServicio sucursalService;
    @Autowired
    private VehiculoServicio vehiculoServio;
    @Autowired
    private VariablesMejoraServicio variablesServicio;
    @Autowired
    private ReporteSimulacionServicio reporteServicio;

    public SimularServicio(EnvioServicio envioServicio) {
        this.envioServicio = envioServicio;
        //this.cantidadEnvioPorSucursal=cantidadEnvioPorSucursal;

    }
    //LOGICA DE SIMULACION DE ENVIOS by James
    Map<Vehiculo,List<Envio>> enviosPorVehiculo = new HashMap<>();
    Map<Vehiculo,Integer> procimoDestino = new HashMap<>();

    public void simular(){
        int capacidadMaxima = variablesServicio.getVariables(2).getValor();
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
            if(data.getId()==27){
                System.out.println("tronitos");
            }

            Envio envioAux = this.envioServicio.obtenerEnvioId(data.getId());
            HistoricoSucursal auxHistorico = this.envioServicio.getHistorico(envioAux.getId()).get(0);
            if(envioAux.getSucursalDestino().getIdSucursal() == auxHistorico.getIdSucursal()){
                envioAux.setEstado("entregado");
                this.envioServicio.saveEnvio(envioAux);
                data.setEstado("entregado");
            }
            else{
                if(envioAux.getId()==9){
                    System.out.println("ee");
                }
                List<PasosEnvio> pasosEnvioList = this.envioServicio.getPasosEnvio(envioAux.getId());
                int indice= IntStream.range(0,pasosEnvioList.size()).filter(i->pasosEnvioList.get(i).getIdSucursal()==(auxHistorico.getIdSucursal())).findFirst().orElse(-1);
                int nextSucursal = indice!=-1 && indice < pasosEnvioList.size() - 1 ? indice + 1:-1;
                System.out.println(envioAux.getId());
                    Optional<Sucursal> sucursalNext = sucursalService.obtenerSucursalId(pasosEnvioList.get(nextSucursal).getIdSucursal());
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
        if(!posiblesMovimientos.isEmpty()){
            var colector = posiblesMovimientos.stream().collect(
                    Collectors.groupingBy(
                            envio->envio.getSucursalOrigen().getIdSucursal(),
                            Collectors.groupingBy(envio -> envio.getSucursalDestino().getIdSucursal())
                    )
            );
            colector.forEach((idOrigen,enviosDestino)->{
                System.out.println("Sucursal origen :"+idOrigen+" con envios a : ");
                enviosDestino.forEach((idDestino,envios)->{

                    double cantidadPeso = envios.stream().mapToDouble(Envio::getPeso).sum();
                    System.out.println("\t\t -"+idDestino+" tiene los siguientes envios total: "+cantidadPeso);
                    List<Vehiculo> vehiculoPropio = this.vehiculoServio.listarPorSucursalId(idOrigen).stream().sorted(Comparator.comparingDouble(Vehiculo::getId)).collect(Collectors.toList());
                    List<Vehiculo> vehiculoPrestado = this.vehiculoServio.listarPorSucursalId(idDestino).stream().filter(vehiculo->idOrigen==vehiculoServio.getVehiculoHistrorial(vehiculo.getId()).getIdSucursal()).toList().stream().sorted(Comparator.comparingDouble(Vehiculo::getCapacidadTon)).collect(Collectors.toList());
                    //variable para ver si toma los vehiculos pequeños como prioridad o los grandes
                    if(this.variablesServicio.getVariables(1).getValor()==1){
                        Collections.reverse(vehiculoPropio);
                        Collections.reverse(vehiculoPrestado);
                    }
                    //para usar los vehiculos de las otras sucursales si estos se encuentran en su sucursal
                    if(this.variablesServicio.getVariables(1).getValor()==0){
                        vehiculoPrestado.removeAll(vehiculoPrestado);
                    }
                    int j = 0;
                    for (Vehiculo vehiculo : vehiculoPrestado) {
                        if(cantidadPeso<=vehiculo.getCapacidadTon()*1000 && !envios.isEmpty()){
                            enviosPorVehiculo.put(vehiculo,List.copyOf(envios));
                            envios.removeAll(envios);
                            break;
                        }else{
                            List<Envio> envioAux = new ArrayList<>();
                            double peso = 0;
                            for (int i = j;i<envios.size();i++) {
                                if(peso+ envios.get(i).getPeso()<=vehiculo.getCapacidadTon()*1000){
                                    peso+= envios.get(i).getPeso();
                                    envioAux.add(envios.get(i));
                                }else{
                                    enviosPorVehiculo.put(vehiculo,List.copyOf(envioAux));
                                    peso = 0;
                                    envios.removeAll(envioAux);
                                    envioAux.clear();
                                    break;
                                }
                                j=i;
                                cantidadPeso-=envios.get(i).getPeso();
                            }
                        }
                    }
                    for (Vehiculo vehiculo : vehiculoPropio) {
                        if(cantidadPeso<=vehiculo.getCapacidadTon()*1000 && !envios.isEmpty()){
                            enviosPorVehiculo.put(vehiculo,List.copyOf(envios));
                            envios.removeAll(envios);
                            break;
                        }else{
                            List<Envio> envioAux = new ArrayList<>();
                            double peso = 0;
                            for (int i = j;i<envios.size();i++) {
                                if(peso+envios.get(i).getPeso()<=vehiculo.getCapacidadTon()*1000){
                                    peso+= envios.get(i).getPeso();
                                    envioAux.add(envios.get(i));
                                }else{
                                    enviosPorVehiculo.put(vehiculo,List.copyOf(envioAux));
                                    peso = 0;
                                    envios.removeAll(envioAux);
                                    envioAux.clear();
                                    break;
                                }
                                j=i;
                                cantidadPeso-=envios.get(i).getPeso();
                            }
                        }
                    }
                });
            });

            //validar donde en que sucursal se encuentra e vehiculo de los vehiculos disponibles
        }
        System.out.println("Sa");
        //this.verificarMontoMinimoEnvio(lista,0);
        ReporteSimulacion reporte = new ReporteSimulacion();
        List<Integer[]> movimientos = new ArrayList<>();
        this.enviosPorVehiculo.forEach((vehiculo,envios)->{
            double ocupacionEnvio = envios.stream().mapToDouble(Envio::getPeso).sum()/(vehiculo.getCapacidadTon()*1000);
            double minimoEnvio = (double) this.variablesServicio.getVariables(2).getValor()/100;
            System.out.println(" idOrigen "+envios.get(0).getSucursalOrigen().getIdSucursal()+" Destino "+envios.get(0).getSucursalDestino().getIdSucursal());
            if(ocupacionEnvio>=minimoEnvio){
                //envia paquete
                System.out.println("Vehiculo "+vehiculo.getId()+" hace viaje con oc "+ocupacionEnvio);
                HistorialVehiculo auxVHistorial = vehiculoServio.getVehiculoHistrorial(vehiculo.getId());
                HistorialVehiculo historiVe = new HistorialVehiculo();
                historiVe.setIdVehiculo(vehiculo.getId());
                historiVe.setFecha(auxVHistorial.getFecha().plusDays(1));
                historiVe.setIdSucursal(envios.get(0).getSucursalDestino().getIdSucursal());
                historiVe.setOcupacion(ocupacionEnvio);
                vehiculoServio.save(historiVe);
                if(envios.size()>0){
                    reporte.setOrigen(envios.get(0).getSucursalOrigen().getIdSucursal());
                    reporte.setDestino(envios.get(0).getSucursalDestino().getIdSucursal());
                    reporte.setVehiculo(vehiculo.getId());
                    reporte.setNumeroEnviados(envios.size());
                    reporte.setOcupacion((int)ocupacionEnvio);

                }
                boolean bandera = true;
                for (Envio enviosSave : envios) {
                    if(enviosSave.getId()==27){
                        System.out.println("sss");
                    }
                    HistoricoSucursal auxSucHist = envioServicio.getHistorico(enviosSave.getId()).get(0);
                    HistoricoSucursal histSuc = new HistoricoSucursal();
                    histSuc.setFecha(auxSucHist.getFecha().plusDays(1));
                    histSuc.setIdVehiculo(vehiculo.getId());
                    //modi11
                    List<PasosEnvio> pasosEnvioAux = this.envioServicio.getPasosEnvio(enviosSave.getId());
                    int indice= IntStream.range(0,pasosEnvioAux.size()).filter(i->pasosEnvioAux.get(i).getIdSucursal()==(auxSucHist.getIdSucursal())).findFirst().orElse(-1);
                    int nextSucursal = indice!=-1 && indice < pasosEnvioAux.size() - 1 ? indice + 1:-1;
                    if(nextSucursal==-1)continue;
                    System.out.println(enviosSave.getId());
                    enviosSave.setDiasTranscurridos(enviosSave.getDiasTranscurridos()+1);
                    histSuc.setIdSucursal(pasosEnvioAux.get(nextSucursal).getIdSucursal());
                    this.envioServicio.saveEnvio(enviosSave);
                    if(this.reporteServicio!=null && bandera){
                        reporte.setOrigen(auxSucHist.getIdSucursal());
                        reporte.setDestino(histSuc.getIdSucursal());
                        reporteServicio.save(reporte);
                        bandera = false;
                    }

                    //
                    histSuc.setIdEnvio(enviosSave.getId());
                    envioServicio.saveHistoricoSucursal(histSuc);
                }
            }else{
               //aumentar dias en espera en envio y en historico

                for (Envio envio : envios) {
                    //aumento de dias en envio
                    envio.setDiasTranscurridos(envio.getDiasTranscurridos()+1);
                    this.envioServicio.saveEnvio(envio);
                    HistoricoSucursal auxSucHist = envioServicio.getHistorico(envio.getId()).get(0);
                    HistoricoSucursal histSuc = new HistoricoSucursal();
                    histSuc.setFecha(auxSucHist.getFecha().plusDays(1));
                    histSuc.setIdVehiculo(vehiculo.getId());
                    //dddddd
                    histSuc.setIdSucursal(auxSucHist.getIdSucursal());
                    histSuc.setIdEnvio(envio.getId());
                    envioServicio.saveHistoricoSucursal(histSuc);
                }
                System.out.println("Vehiculo "+vehiculo.getId()+" no hace envio "+ocupacionEnvio);
            }
        });
        System.out.println("Sardinas");
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
