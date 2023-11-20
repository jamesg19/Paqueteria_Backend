package com.paqueteria.paqueteria_backend.controlador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            double porcVal = (double) porc[3]; 
            gananciasTotalesEnvios += porcVal;
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

            AnalisisVehiculo analisisVehiculo = new AnalisisVehiculo();
            analisisVehiculo.setDescripcion("La descripcion del analisis es:");
            analisisVehiculo.setRecomendacion("La recomendacion del analisis es:" );
            DatoAnalisis dato1 = new DatoAnalisis("Estimado de envios realizados por nuevos vehiculos","14");
            DatoAnalisis dato2 = new DatoAnalisis("Estimado de gastos por gasolina","14");
            DatoAnalisis dato3 = new DatoAnalisis("Estimado de ganancias","14");
            
            //Datos de multidatos
            DatoAnalisis datoG1 = new DatoAnalisis("0","0");
            DatoAnalisis datoG2 = new DatoAnalisis("6 meses","14");
            DatoAnalisis datoG3 = new DatoAnalisis("1 año","50");
            DatoAnalisis datoG4 = new DatoAnalisis("1 año","55");
            
            MultiDatos multi1 = new MultiDatos();
            multi1.setName("Gastos");
            multi1.insertDatoAnalisis(datoG1);
            multi1.insertDatoAnalisis(datoG2);
            multi1.insertDatoAnalisis(datoG3);
            
            MultiDatos multi2 = new MultiDatos();
            multi2.setName("Ganancias");
            multi2.insertDatoAnalisis(datoG1);
            multi2.insertDatoAnalisis(datoG2);
            multi2.insertDatoAnalisis(datoG4);
            
            analisisVehiculo.insertDatoAnalisis(dato1);
            analisisVehiculo.insertDatoAnalisis(dato2);
            analisisVehiculo.insertDatoAnalisis(dato3);
            
            analisisVehiculo.insertMultiDato(multi1);
            analisisVehiculo.insertMultiDato(multi2);

            System.out.println(recibidoNuevaRuta.getSucursalorigen());
            System.out.println(recibidoNuevaRuta.getDistancias());
            System.out.println(recibidoNuevaRuta.getSucursales());


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

            AnalisisVehiculo analisisVehiculo = new AnalisisVehiculo();
            analisisVehiculo.setDescripcion("La descripcion del analisis es:");
            analisisVehiculo.setRecomendacion("La recomendacion del analisis es:" );
            DatoAnalisis dato1 = new DatoAnalisis("Estimado de envios realizados por nuevos vehiculos","14");
            DatoAnalisis dato2 = new DatoAnalisis("Estimado de gastos por gasolina","14");
            DatoAnalisis dato3 = new DatoAnalisis("Estimado de ganancias","14");
            
            //Datos de multidatos
            DatoAnalisis datoG1 = new DatoAnalisis("0","0");
            DatoAnalisis datoG2 = new DatoAnalisis("6 meses","14");
            DatoAnalisis datoG3 = new DatoAnalisis("1 año","50");
            DatoAnalisis datoG4 = new DatoAnalisis("1 año","55");
            
            MultiDatos multi1 = new MultiDatos();
            multi1.setName("Gastos");
            multi1.insertDatoAnalisis(datoG1);
            multi1.insertDatoAnalisis(datoG2);
            multi1.insertDatoAnalisis(datoG3);
            
            MultiDatos multi2 = new MultiDatos();
            multi2.setName("Ganancias");
            multi2.insertDatoAnalisis(datoG1);
            multi2.insertDatoAnalisis(datoG2);
            multi2.insertDatoAnalisis(datoG4);
            
            analisisVehiculo.insertDatoAnalisis(dato1);
            analisisVehiculo.insertDatoAnalisis(dato2);
            analisisVehiculo.insertDatoAnalisis(dato3);
            
            analisisVehiculo.insertMultiDato(multi1);
            analisisVehiculo.insertMultiDato(multi2);

            System.out.println(recibidoNuevaRuta.getLongitud());
            System.out.println(recibidoNuevaRuta.getLatitud());
            System.out.println(recibidoNuevaRuta.getCantidadVehiculos());
            System.out.println(recibidoNuevaRuta.getCantidadPersonal());            
            System.out.println(recibidoNuevaRuta.getSucursales());
            System.out.println(recibidoNuevaRuta.getDistancias());


            return analisisVehiculo;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }
}
