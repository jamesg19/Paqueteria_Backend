package com.paqueteria.paqueteria_backend.controlador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paqueteria.paqueteria_backend.analisis.AnalisisVehiculo;
import com.paqueteria.paqueteria_backend.analisis.DatoAnalisis;
import com.paqueteria.paqueteria_backend.analisis.MultiDatos;
import com.paqueteria.paqueteria_backend.analisis.RecibidoNuevaRuta;
import com.paqueteria.paqueteria_backend.analisis.RecibidoNuevaSucursal;
import com.paqueteria.paqueteria_backend.servicio.AnalisisServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/analisis")
public class AnalisisControlador {

    private AnalisisServicio analisisServicio;
    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param AnalisisServicio
    */
    public AnalisisControlador(AnalisisServicio analisisServicio) {
        this.analisisServicio = analisisServicio;
    }
    

    @GetMapping("/nuevosVehiculos")
    public AnalisisVehiculo getAnalisis(HttpServletRequest request, HttpServletResponse response, 
        @RequestParam String cantidadVehiculos, @RequestParam String sucursalForm)  {
        
        //Trae los gastos de gasolina de la sucursal
        List<Object[]> gastosGasolinaAlMes = this.analisisServicio.obtenerGastoGasolinaPorSucursal(sucursalForm);
        double gastoGasolinaMes = 0.0;
        for (Object[]  gasto : gastosGasolinaAlMes) {
            Double gasto1 = (Double) gasto[3]; 
            gastoGasolinaMes += gasto1;
        }

        //Trae la cantidad de vehiculos que tiene la sucursal
        List<Object[]> vehiculos = this.analisisServicio.obtenerVehiculosPorSucursal(sucursalForm);
        int cantidadVehiculosSucursal = vehiculos.size();

        //Si no hay vehiculos se usa solo el gasto de gasolina
        double gastoGasolinaPorCadaVehiculo = 0.0;
        if(cantidadVehiculosSucursal <= 0){
            gastoGasolinaPorCadaVehiculo = gastoGasolinaMes;
        }else{
            gastoGasolinaPorCadaVehiculo = gastoGasolinaMes/cantidadVehiculosSucursal;
        }

        //Traer todos los envios de la sucursal 
        List<Object[]> enviosTotales = this.analisisServicio.obtenerTodosEnviosPorSucursal(sucursalForm);
        int cantidadEnviosTotales = enviosTotales.size();

        //aproximar cantidad de envios al mes
        //Esta quemado el 24 porque solo han pasado 24 meses, en teoria
        int cantidadEnviosMes = Integer.valueOf(enviosTotales.size()/24);

        //Calcular ganancias totales de todos los envios
        double gananciasTotalesEnvios = 0.0;
        for (Object[]  envio : enviosTotales) {
            int total = (int) envio[6]; 
            gananciasTotalesEnvios += total;
        }

        //Calcular ganancias promedio al mes
        double gananciasPromedioMes = 0.0;
        if(cantidadEnviosMes > 0){
            gananciasPromedioMes = gananciasTotalesEnvios/cantidadEnviosMes;
        }

        //Calcular ganancias por vehiculo
        double gananciaPorVehiculoMes = 0.0;
        if(cantidadVehiculosSucursal > 0){
            gananciaPorVehiculoMes = gananciasPromedioMes/cantidadVehiculosSucursal;
        }else{
            gananciaPorVehiculoMes = gananciasPromedioMes;
        }

        //Calcular un porcentaje de aumento de ganancias
        List<Object[]> porcentajesAumentoGanancias = analisisServicio.obtenerPorcentajeAumentoPorSucursal(sucursalForm);
        int cantidadPorcentajes = 0;
        double porcentaje = 0.0;
        for (Object[]  porc : porcentajesAumentoGanancias) {
            BigDecimal porcVal = (BigDecimal) porc[3]; 
            porcentaje += porcVal.doubleValue();
            cantidadPorcentajes +=1;
        }
        porcentaje = (porcentaje / cantidadPorcentajes)/100;

        //Talvez tambien se podria predecir que no haya mas envios atrasados, o con menos atraso pero no se como se esta trabajando eso y ya es tarde, quiero dormir



        String descripcion = "Primero se obtienen los gastos de gasolina de la sucursal a donde se quiere ingresar un vehiculo en este caso se obtuvo: " + gastoGasolinaMes + " al mes"+
         " ,luego se obtiene la cantidad de vehiculos de esta sucursal: " + cantidadVehiculosSucursal + 
         " ,despues obtenemos lo que gastamos de gasolina por vehiculo, por lo que el nuevo gasto sera: "+gastoGasolinaPorCadaVehiculo +" Quetzales"+
         " . Ahora teniendo en cuenta el gasto de gasolina se tiene que analizar la cantidad de envios que podria enviar al mes "+ 
         " para ello se traen toda la cantidad de envios que ha tenido que enviar en el tiempo de trabajo que son: " + cantidadEnviosTotales +" Envios,"+
         " ya que por ahora se ha trabajado durante 2 años se puede estimar la cantidad de envios que se llevaron al mes: " + cantidadEnviosMes + " Envios,"+ 
         " de esta forma se pueden obtener las ganancias promedio durante un mes: "+ gananciasPromedioMes + " Quetzales,"+
         " tambien podemos estimar las ganancias que cada vehiculo trae al mes en este caso: "+ gananciaPorVehiculoMes +"Quetzales"+
         " dado esto podemos predecir si es o no rentable a un año el uso de los nuevos vehiculos." + 
         " Ademas de todo ello se calculo que el porcentaje de aumento de ganancias en la sucursal sobre los ultimos " + cantidadPorcentajes + " meses, ha sido de: " + porcentaje;
        AnalisisVehiculo analisisVehiculo = new AnalisisVehiculo();
        analisisVehiculo.setDescripcion(descripcion);

        //Calcular los envios por cada vehiculo
        double cantidadEnviosEnviadosMesPorVehiculo = cantidadEnviosMes/(cantidadVehiculosSucursal + Double.valueOf(cantidadVehiculos));

        //Calculos de perdidas
        double perdidas1 = gastoGasolinaPorCadaVehiculo*Double.valueOf(cantidadVehiculos);
        double perdidas2 = gastoGasolinaPorCadaVehiculo*Double.valueOf(cantidadVehiculos) * 6;
        double perdidas3 = gastoGasolinaPorCadaVehiculo*Double.valueOf(cantidadVehiculos) * 12;

        //Calculos de ganancias
        double ganancias1 = gananciaPorVehiculoMes *Double.valueOf(cantidadVehiculos);
        double ganancias2 = (gananciaPorVehiculoMes *Double.valueOf(cantidadVehiculos) * 6) * porcentaje + (gananciaPorVehiculoMes *Double.valueOf(cantidadVehiculos) * 6);
        double ganancias3 = (gananciaPorVehiculoMes *Double.valueOf(cantidadVehiculos) * 12) * porcentaje + (gananciaPorVehiculoMes *Double.valueOf(cantidadVehiculos) *12);


        //Datos generales
        DatoAnalisis dato1 = new DatoAnalisis("Estimado de envios por cada vehiculo",String.valueOf(cantidadEnviosEnviadosMesPorVehiculo));
        DatoAnalisis dato2 = new DatoAnalisis("Estimado de gastos por gasolina",String.valueOf(perdidas3));
        DatoAnalisis dato3 = new DatoAnalisis("Estimado de ganancias",String.valueOf(ganancias3));

        analisisVehiculo.insertDatoAnalisis(dato1);
        analisisVehiculo.insertDatoAnalisis(dato2);
        analisisVehiculo.insertDatoAnalisis(dato3);

        //Datos de perdidas
        DatoAnalisis datoP1 = new DatoAnalisis("0 meses",String.valueOf(perdidas1));
        DatoAnalisis datoP2 = new DatoAnalisis("6 meses",String.valueOf(perdidas2));
        DatoAnalisis datoP3 = new DatoAnalisis("1 año",String.valueOf(perdidas3));

        //Datos de ganancias
        DatoAnalisis datoG1 = new DatoAnalisis("0 meses",String.valueOf(ganancias1));
        DatoAnalisis datoG2 = new DatoAnalisis("6 meses",String.valueOf(ganancias2));
        DatoAnalisis datoG3 = new DatoAnalisis("1 año",String.valueOf(ganancias3));

        MultiDatos multi1 = new MultiDatos();
        multi1.setName("Gastos");
        multi1.insertDatoAnalisis(datoP1);
        multi1.insertDatoAnalisis(datoP2);
        multi1.insertDatoAnalisis(datoP3);

        MultiDatos multi2 = new MultiDatos();
        multi2.setName("Ganancias");
        multi2.insertDatoAnalisis(datoG1);
        multi2.insertDatoAnalisis(datoG2);
        multi2.insertDatoAnalisis(datoG3);

        analisisVehiculo.insertMultiDato(multi1);
        analisisVehiculo.insertMultiDato(multi2);
        

        //Crear una recomendacion en base a lo analizado
        String recomendacion = "Segun lo aprendido y analizado se recomienda: ";
        if(perdidas3 > ganancias3){
            recomendacion += " A largo plazo no se recomienda ya que las perdidas superan a las ganancias de los envios, ";
        }else{
            recomendacion += " A largo plazo se recomienda usar esa cantidad de vehiculos ya que se tienen ganancias suficientes para superar los gastos, ";
        }
        if(perdidas2 > ganancias2){
            recomendacion += " A mediano plazo no se recomiendaria ya que se tienen perdidas mas grandes que ganancias, ";
        }else{
            recomendacion += " A mediano plazo se recomienda usar esa cantidad de vehiculos ya que se tendrian ganancias suficientes para superar los gastos, ";
        }
        if(perdidas1 > ganancias1){
            recomendacion += " A corto plazo no se recomendaria porque las ganancias no superan a los gastos, ";
        }else{
            recomendacion += " A corto plazo se recomienda usar esa cantidad de vehiculos ya que se tendrian inmediatamente ganancias para superar los gastos. ";
        }

        analisisVehiculo.setRecomendacion(recomendacion);
        
        return analisisVehiculo;
    }


    @PostMapping("/nuevaruta")
    public AnalisisVehiculo getAnalisisNuevaRuta(@RequestBody RecibidoNuevaRuta recibidoNuevaRuta){
        try {               
            String sucursalorigen = recibidoNuevaRuta.getSucursalorigen();
            
            

            //Calcular un porcentaje de aumento de ganancias
            List<Object[]> porcentajesAumentoGanancias = analisisServicio.obtenerPorcentajeAumentoPorSucursal(sucursalorigen);
            int cantidadPorcentajes = 0;
            double porcentaje = 0.0;
            for (Object[]  porc : porcentajesAumentoGanancias) {
                double porcVal = (double) porc[3]; 
                porcentaje += porcVal;
                cantidadPorcentajes +=1;
            }
            porcentaje = (porcentaje / cantidadPorcentajes)/100;

            //Talvez tambien se podria predecir que no haya mas envios atrasados, o con menos atraso pero no se como se esta trabajando eso y ya es tarde, quiero dormir

            //Traer las rutas que ya tiene la sucursal

            //

            String descripcion = "Para analizar la usabilidad y efectividad de las rutas enviadas se realiza lo siguiente: " + 
                " Primero se trae la sucursal origen y se analizan ";
            AnalisisVehiculo analisisVehiculo = new AnalisisVehiculo();
            analisisVehiculo.setDescripcion(descripcion);


            //Calculos de perdidas
            double perdidas1 = 0.0;
            double perdidas2 = 0.0;
            double perdidas3 = 0.0;

            //Calculos de ganancias
            double ganancias1 = 0.0;
            double ganancias2 = 0.0;
            double ganancias3 = 0.0;


            //Datos generales
            DatoAnalisis dato1 = new DatoAnalisis("Estimado de envios por cada vehiculo",String.valueOf(0));
            DatoAnalisis dato2 = new DatoAnalisis("Estimado de gastos por gasolina",String.valueOf(perdidas3));
            DatoAnalisis dato3 = new DatoAnalisis("Estimado de ganancias",String.valueOf(ganancias3));

            analisisVehiculo.insertDatoAnalisis(dato1);
            analisisVehiculo.insertDatoAnalisis(dato2);
            analisisVehiculo.insertDatoAnalisis(dato3);

            //Datos de perdidas
            DatoAnalisis datoP1 = new DatoAnalisis("0 meses",String.valueOf(perdidas1));
            DatoAnalisis datoP2 = new DatoAnalisis("6 meses",String.valueOf(perdidas2));
            DatoAnalisis datoP3 = new DatoAnalisis("1 año",String.valueOf(perdidas3));

            //Datos de ganancias
            DatoAnalisis datoG1 = new DatoAnalisis("0 meses",String.valueOf(ganancias1));
            DatoAnalisis datoG2 = new DatoAnalisis("6 meses",String.valueOf(ganancias2));
            DatoAnalisis datoG3 = new DatoAnalisis("1 año",String.valueOf(ganancias3));

            MultiDatos multi1 = new MultiDatos();
            multi1.setName("Gastos");
            multi1.insertDatoAnalisis(datoP1);
            multi1.insertDatoAnalisis(datoP2);
            multi1.insertDatoAnalisis(datoP3);

            MultiDatos multi2 = new MultiDatos();
            multi2.setName("Ganancias");
            multi2.insertDatoAnalisis(datoG1);
            multi2.insertDatoAnalisis(datoG2);
            multi2.insertDatoAnalisis(datoG3);

            analisisVehiculo.insertMultiDato(multi1);
            analisisVehiculo.insertMultiDato(multi2);
            

            //Crear una recomendacion en base a lo analizado
            String recomendacion = "Segun lo aprendido y analizado se recomienda: ";
            if(perdidas3 > ganancias3){
                recomendacion += " A largo plazo no se recomienda ya que las perdidas superan a las ganancias de los envios, ";
            }else{
                recomendacion += " A largo plazo se recomienda usar esa cantidad de vehiculos ya que se tienen ganancias suficientes para superar los gastos, ";
            }
            if(perdidas2 > ganancias2){
                recomendacion += " A mediano plazo no se recomiendaria ya que se tienen perdidas mas grandes que ganancias, ";
            }else{
                recomendacion += " A mediano plazo se recomienda usar esa cantidad de vehiculos ya que se tendrian ganancias suficientes para superar los gastos, ";
            }
            if(perdidas1 > ganancias1){
                recomendacion += " A corto plazo no se recomendaria porque las ganancias no superan a los gastos, ";
            }else{
                recomendacion += " A corto plazo se recomienda usar esa cantidad de vehiculos ya que se tendrian inmediatamente ganancias para superar los gastos. ";
            }

            analisisVehiculo.setRecomendacion(recomendacion);
            
            return analisisVehiculo;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }

    @PostMapping("/nuevasucursal")
    public AnalisisVehiculo getAnalisisNuevaSucursal(@RequestBody RecibidoNuevaSucursal recibidoNuevaRuta){
        try {                        

            Double longitud = recibidoNuevaRuta.getLongitud();
            Double latitud = recibidoNuevaRuta.getLatitud();
            Integer cantidadVehiculos = recibidoNuevaRuta.getCantidadVehiculos();
            Integer cantidadPersonal = recibidoNuevaRuta.getCantidadPersonal();

            //Traer las 3 sucursales mas cercanas a donde se quiere poner
            List<Object[]> sucursalesMasCercanas = new ArrayList<>();
            sucursalesMasCercanas = analisisServicio.obtenerSucursalesMasCercanas(longitud, latitud);

            String idSucursal1 = String.valueOf(sucursalesMasCercanas.get(0)[0]);
            String idSucursal2 = String.valueOf(sucursalesMasCercanas.get(1)[0]);
            String idSucursal3 = String.valueOf(sucursalesMasCercanas.get(2)[0]);
            

            //Se analizan cuales son sus ganancias y perdidas, asi como tambien su personal, sus vehiculos, y los envios que envian y reciben
            //traer todos los vehiculos de las 3 sucursales
            List<Object[]> vehiculos3Sucursales = analisisServicio.obtenerVehiculos3Sucursales(idSucursal1, idSucursal2, idSucursal3);

            //gasto de gasolina de las sucursales
            List<Object[]> gastosGasolina3Sucursales = analisisServicio.obtenerGastoGasolina3Sucursales(idSucursal1, idSucursal2, idSucursal3);

            Double gastoGasolinaPromedioMes = 0.0;
            for (Object[]  gasto : gastosGasolina3Sucursales) {
                Double gasto1 = (Double) gasto[1]; 
                gastoGasolinaPromedioMes += gasto1;
            }            

            Double gastoGasolinaVehiculoPromedioMes = gastoGasolinaPromedioMes/(vehiculos3Sucursales.size());


            //traer todos los envios enviados de las 3 sucursales
            List<Object[]> enviosEnviados3Sucursales = analisisServicio.obtenerEnviosEnviados3Sucursales(idSucursal1, idSucursal2, idSucursal3);

            int cantidadEnviosEnviadosPromedio = (enviosEnviados3Sucursales.size()/3);

            Double gananciasPorEnvios = 0.0;
            for (Object[]  envio : enviosEnviados3Sucursales) {
                int gananciaPorEnvio = (int) envio[6]; 
                gananciasPorEnvios += gananciaPorEnvio;
            }

            Double gananciasPorEnviosAlMes = (gananciasPorEnvios/24)/3;


            //Traer todos los envios recibidos de las 3 sucursales
            List<Object[]> enviosRecibidos3Sucursales = analisisServicio.obtenerEnviosRecibidos3Sucursales(idSucursal1, idSucursal2, idSucursal3);

            int cantidadEnviosRecibidosPromedioMes = enviosRecibidos3Sucursales.size()/24;

            //traer todo el personal que tienen las 3 sucursales
            List<Object[]> personal3Sucursales = analisisServicio.obtenerPersonal3Sucursales(idSucursal1, idSucursal2, idSucursal3);

            int cantidadPersonalPromedio = personal3Sucursales.size()/3;

            //Traer lo que se gasta en personal de las 3 sucursales
            List<Object[]> gastosPersonal3Sucursales = analisisServicio.obtenerGastoPersonal3Sucursales(idSucursal1, idSucursal2, idSucursal3);
            Double gastoPersonal = 0.0;
            for (Object[]  gasto : gastosPersonal3Sucursales) {
                Double gasto1 = (Double) gasto[3]; 
                gastoPersonal += gasto1;
            } // Este no es al mes

            Double gastoPersonalMes = gastoPersonal/24;
            Double gastoPromedioPorPersonaMes = gastoPersonalMes/cantidadPersonalPromedio;

            List<Object[]> gastosFijo3Sucursales = analisisServicio.obtenerGastoFijo3Sucursales(idSucursal1, idSucursal2, idSucursal3);
            Double gastoFijo = 0.0;
            for (Object[]  gasto : gastosFijo3Sucursales) {
                Double gasto1 = (Double) gasto[1]; 
                String nombreGasto = String.valueOf(gasto[0]);
                if(nombreGasto.equalsIgnoreCase("gasolina")){
                    
                }else{
                    gastoFijo += gasto1;
                }
            } // Este si es al mes
            gastoFijo = gastoFijo / 3; 



            List<Object[]> gastosEspecial3Sucursales = analisisServicio.obtenerGastoEspecial3Sucursales(idSucursal1, idSucursal2, idSucursal3);
            Double gastoEspecial = 0.0;
            for (Object[]  gasto : gastosEspecial3Sucursales) {
                Double gasto1 = (Double) gasto[1]; 
                gastoEspecial += gasto1;
            }// Este no es al mes

            Double gastosEspecialesPromedioMes = (gastoEspecial/24)/3;


            // Se analizan las sucursales a donde se van a enviar, sus envios recibidos, podria ser desde las mas cercanas
            // esto para visualizar si 


            //Calcular un porcentaje de aumento de ganancias
            List<Object[]> porcentajesAumentoGanancias = analisisServicio.obtenerPorcentajeAumentoPorSucursal(idSucursal1);
            List<Object[]> porcentajesAumentoGanancias2 = analisisServicio.obtenerPorcentajeAumentoPorSucursal(idSucursal2);
            List<Object[]> porcentajesAumentoGanancias3 = analisisServicio.obtenerPorcentajeAumentoPorSucursal(idSucursal3);
            int cantidadPorcentajes = 0;
            double porcentaje = 0.0;
            for (Object[]  porc : porcentajesAumentoGanancias) {
                BigDecimal porcVal = (BigDecimal) porc[3]; 
                porcentaje += porcVal.doubleValue();
                cantidadPorcentajes +=1;
            }
            for (Object[]  porc : porcentajesAumentoGanancias2) {
                BigDecimal porcVal = (BigDecimal) porc[3]; 
                porcentaje += porcVal.doubleValue();
                cantidadPorcentajes +=1;
            }
            for (Object[]  porc : porcentajesAumentoGanancias3) {
                BigDecimal porcVal = (BigDecimal) porc[3]; 
                porcentaje += porcVal.doubleValue();
                cantidadPorcentajes +=1;
            }
            porcentaje = (porcentaje / cantidadPorcentajes)/100;

            //Talvez tambien se podria predecir que no haya mas envios atrasados, o con menos atraso pero no se como se esta trabajando eso y ya es tarde, quiero dormir



            String descripcion = "Para analizar la usabilidad y efectividad de las rutas enviadas se realiza lo siguiente: " + 
                " Primero se trae la sucursal origen y se analizan las sucursales mas cercanas activas de donde se pondra la nueva," +
                " esto con el objetivo de poder visualizar segun su area, el pago de los empleados, sus gastos, y ganancias sobre envios"+
                " de esta forma se obtienen que las sucursales mas cercanas a donde se quiere poner la nueva sucursal son: Sucursal1 ID: " + idSucursal1 +
                " ,Sucursal2 ID: " + idSucursal2 + " ,Sucursal3 ID: " + idSucursal3 + ". Ahora de estas 3 sucursales se obtienen los siguientes datos: " +
                " Promedio de personal trabajando = " +cantidadPersonalPromedio + 
                " Promedio de ganancias por envios al mes = " + gananciasPorEnviosAlMes + 
                " Promedio de vehiculos = " + (vehiculos3Sucursales.size()/3)+
                " Promedio de gasto de gasolina por vehiculo al mes = " + gastoGasolinaVehiculoPromedioMes +
                " Promedio de gasto por personal al mes = " + gastoPromedioPorPersonaMes +
                " Promedio de gasto especial al mes = " + gastosEspecialesPromedioMes +
                " Promedio de gasto fijo sin contar la gasolina al mes = " + gastoFijo +
                " Ademas se encontro que el porcentaje promedio de ganancias minimo entre las 3 sucursales es: " + (porcentaje*100) + "%" +
                " Teniendo estos datos se calculan algunas predicciones sobre lo que se puede ganar o perder en 1 año, Cabe mencionar que se calculan los datos"+
                " segun las cantidades de vehiculos y personal ingresadas"
                ;
            AnalisisVehiculo analisisVehiculo = new AnalisisVehiculo();
            analisisVehiculo.setDescripcion(descripcion);

            if(cantidadPersonal == null){
                cantidadPersonal = cantidadPersonalPromedio;
            }
            if(cantidadVehiculos == null){
                cantidadVehiculos = vehiculos3Sucursales.size()/3;
            }

            Double totalGastosMes = gastoFijo+(gastoPromedioPorPersonaMes * cantidadPersonal)+gastosEspecialesPromedioMes + (gastoGasolinaPromedioMes*cantidadVehiculos);

            //Calculos de perdidas
            double perdidas1 = totalGastosMes * 1;
            double perdidas2 = totalGastosMes * 6;
            double perdidas3 = totalGastosMes * 12;

            //Calculos de ganancias
            double ganancias1 = (gananciasPorEnviosAlMes * porcentaje) + gananciasPorEnviosAlMes;
            double ganancias2 = (gananciasPorEnviosAlMes * porcentaje * 6) + gananciasPorEnviosAlMes * 6;
            double ganancias3 = (gananciasPorEnviosAlMes * porcentaje * 12) + gananciasPorEnviosAlMes * 12;


            //Datos generales
            DatoAnalisis dato1 = new DatoAnalisis("Estimado de envios al mes (Enviados)",String.valueOf(cantidadEnviosEnviadosPromedio));
            DatoAnalisis dato2 = new DatoAnalisis("Estimado de gastos totales",String.valueOf(perdidas3));
            DatoAnalisis dato3 = new DatoAnalisis("Estimado de ganancias",String.valueOf(ganancias3));
            DatoAnalisis dato4 = new DatoAnalisis("Estimado de vehiculos",String.valueOf(cantidadVehiculos));
            DatoAnalisis dato5 = new DatoAnalisis("Estimado de personal",String.valueOf(cantidadPersonal));
            //DatoAnalisis dato6 = new DatoAnalisis("Estimado de rutas",String.valueOf(0));


            analisisVehiculo.insertDatoAnalisis(dato1);
            analisisVehiculo.insertDatoAnalisis(dato2);
            analisisVehiculo.insertDatoAnalisis(dato3);
            analisisVehiculo.insertDatoAnalisis(dato4);
            analisisVehiculo.insertDatoAnalisis(dato5);
            //analisisVehiculo.insertDatoAnalisis(dato6);

            //Datos de perdidas
            DatoAnalisis datoP1 = new DatoAnalisis("0 meses",String.valueOf(perdidas1));
            DatoAnalisis datoP2 = new DatoAnalisis("6 meses",String.valueOf(perdidas2));
            DatoAnalisis datoP3 = new DatoAnalisis("1 año",String.valueOf(perdidas3));

            //Datos de ganancias
            DatoAnalisis datoG1 = new DatoAnalisis("0 meses",String.valueOf(ganancias1));
            DatoAnalisis datoG2 = new DatoAnalisis("6 meses",String.valueOf(ganancias2));
            DatoAnalisis datoG3 = new DatoAnalisis("1 año",String.valueOf(ganancias3));

            MultiDatos multi1 = new MultiDatos();
            multi1.setName("Gastos");
            multi1.insertDatoAnalisis(datoP1);
            multi1.insertDatoAnalisis(datoP2);
            multi1.insertDatoAnalisis(datoP3);

            MultiDatos multi2 = new MultiDatos();
            multi2.setName("Ganancias");
            multi2.insertDatoAnalisis(datoG1);
            multi2.insertDatoAnalisis(datoG2);
            multi2.insertDatoAnalisis(datoG3);

            analisisVehiculo.insertMultiDato(multi1);
            analisisVehiculo.insertMultiDato(multi2);
            

            //Crear una recomendacion en base a lo analizado
            String recomendacion = "Segun lo aprendido y analizado se recomienda: ";
            if(perdidas3 > ganancias3){
                recomendacion += " A largo plazo no se recomienda ya que las perdidas superan a las ganancias de los envios, ";
            }else{
                recomendacion += " A largo plazo se recomienda usar esa cantidad de vehiculos ya que se tienen ganancias suficientes para superar los gastos, ";
            }
            if(perdidas2 > ganancias2){
                recomendacion += " A mediano plazo no se recomiendaria ya que se tienen perdidas mas grandes que ganancias, ";
            }else{
                recomendacion += " A mediano plazo se recomienda usar esa cantidad de vehiculos ya que se tendrian ganancias suficientes para superar los gastos, ";
            }
            if(perdidas1 > ganancias1){
                recomendacion += " A corto plazo no se recomendaria porque las ganancias no superan a los gastos, ";
            }else{
                recomendacion += " A corto plazo se recomienda usar esa cantidad de vehiculos ya que se tendrian inmediatamente ganancias para superar los gastos. ";
            }

            analisisVehiculo.setRecomendacion(recomendacion);
            
            return analisisVehiculo;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }
}
