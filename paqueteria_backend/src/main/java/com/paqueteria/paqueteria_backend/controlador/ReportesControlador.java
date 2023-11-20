package com.paqueteria.paqueteria_backend.controlador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paqueteria.paqueteria_backend.analisis.DatoAnalisis;
import com.paqueteria.paqueteria_backend.reportes.DatoReporteSucursal;
import com.paqueteria.paqueteria_backend.reportes.DatoReporteVechiculo;
import com.paqueteria.paqueteria_backend.reportes.RecibidoReporteSucursal;
import com.paqueteria.paqueteria_backend.reportes.ReporteDashboard;
import com.paqueteria.paqueteria_backend.reportes.ReporteSucursal;
import com.paqueteria.paqueteria_backend.reportes.ReporteVehiculo;
import com.paqueteria.paqueteria_backend.servicio.ReportesServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/reportes")
public class ReportesControlador {
    private ReportesServicio reportesServicio;

    

    public ReportesControlador(ReportesServicio reportesServicio) {
        this.reportesServicio = reportesServicio;
    }



    @GetMapping("/vehiculos")
    public List<Object[]> getAnalisis(HttpServletRequest request, HttpServletResponse response){
        try {            
            List<Object[]> obj= this.reportesServicio.obtenerTodosLosVehiculos();
            return obj;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }

    @PostMapping("/movvehiculo")
    public ReporteVehiculo getReporteMovVehiculo(
        @RequestBody String vehiculoForm){
        try {            
            
            ReporteVehiculo reporte = new ReporteVehiculo();
            //Se obtienen los movimientos de vehiculo y en base a ello se realiza el reporte
            List<Object[]> movimientosVehiculo = reportesServicio.obtenerMovimientosVehiculo(vehiculoForm);
            int cantidadMovimientos = movimientosVehiculo.size();

            for (Object[]  movimiento : movimientosVehiculo) {
                DatoReporteVechiculo datoReporteVechiculo1 = new DatoReporteVechiculo("2020-01-01",String.valueOf(movimiento[2]) , "10");           
                reporte.insertDatoTable(datoReporteVechiculo1);
            }


            
            DatoAnalisis dato1 = new DatoAnalisis("Movimientos realizados",String.valueOf(cantidadMovimientos));
            DatoAnalisis dato2 = new DatoAnalisis("Envios entregados","20");

            reporte.insertDato(dato1);
            reporte.insertDato(dato2);                       
            return reporte;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }

    @PostMapping("/dashboard")
    public ReporteDashboard getReporteDashboard(
        @RequestBody String fechaAnalizar){
        try {
            //Calcular primer dia del mes y anio que enviaron
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(fechaAnalizar, formatter);

            // Obtener el primer día del mes
            LocalDate primerDiaDelMes = fecha.withDayOfMonth(1);        
            
            //Obtener primer dia del siguiente mes
            LocalDate primerDiaSiguienteMes = primerDiaDelMes.plusMonths(1).withDayOfMonth(1);

            //Traer todos los envios que estan en ruta
            List<Object[]> enviosEnRuta = reportesServicio.obtenerEnviosPorEstadoEntreFechas("enRuta", primerDiaDelMes.toString(), fecha.toString());
            int cantidadEnviosEnRuta = enviosEnRuta.size();

            //Traer todos los envios completados
            List<Object[]> enviosCompletados = reportesServicio.obtenerEnviosPorEstadoEntreFechas("completo", primerDiaDelMes.toString(), fecha.toString());
            int cantidadEnviosCompletos = enviosCompletados.size();

            //Traer todas las ganancias de todos los envios
            List<Object[]> enviosTodos = reportesServicio.obtenerTodosEnviosEntreFechas(primerDiaDelMes.toString(), fecha.toString());
            int totalGanancias = 0;
            for (Object[]  envio : enviosTodos) {
                int totalEnvio = (int) envio[6]; 
                totalGanancias += totalEnvio;
            }

            //Traer todos los gastos
                //Gastos fijos de todas las sucursales
            List<Object[]> gastosFijos = reportesServicio.obtenerTodosGastosFijosAgrupadosPorNombre();
            Double totalGastosFijos = 0.0;
            for (Object[]  gasto : gastosFijos) {
                Double totalEnvio = (Double) gasto[1]; 
                totalGastosFijos += totalEnvio;
            }
                //Gastos especiales de todas las sucursales entre 2 fechas

                //Encontrar la asamblea de la que se obtendran los gastos
            List<Object[]> asambleas = this.reportesServicio.obtenerAsambleaPorFecha(primerDiaSiguienteMes.toString());
            int idAsamblea = 0;
            for (Object[]  asamblea : asambleas) {
                idAsamblea = (int) asamblea[0];                
            }

            List<Object[]> gastosEspeciales = reportesServicio.obtenerTodosGastosEspecialesPorAsambleaAgrupados(idAsamblea);
            Double totalGastosEspeciales = 0.0;
            for (Object[]  gasto : gastosEspeciales) {
                Double totalEnvio = (Double) gasto[1]; 
                totalGastosEspeciales += totalEnvio;
            }
            
                //Gastos de personal entre 2 fechas
            List<Object[]> gastosPersonal = reportesServicio.obtenerTodosGastoPersonalPorAsamblea(idAsamblea);
            Double totalGastosPersonal = 0.0;
            for (Object[]  gasto : gastosPersonal) {
                Double totalEnvio = (Double) gasto[3]; 
                totalGastosPersonal += totalEnvio;
            }
            
            Double totalGastos = totalGastosFijos + totalGastosEspeciales + totalGastosPersonal;

            //Calcular el margen de beneficios

            double margenBeneficios = totalGanancias - totalGastos;

            //Calcular el porcentaje de ganancia
            double porcejateGanancia = (totalGanancias/totalGastos) * 100;

            //Traer las 10 sucursales con mas ganancias
            List<Object[]> sucursalesMas = reportesServicio.obtenerSucursalesMasGananciasEntreFechas(primerDiaDelMes.toString(), fecha.toString());            

            //Traer las 10 sucursales con menos ganancias
            List<Object[]> sucursalesMenos = reportesServicio.obtenerSucursalesMenosGananciasEntreFechas(primerDiaDelMes.toString(), fecha.toString());
            


            ReporteDashboard reporte = new ReporteDashboard();
            DatoAnalisis dato1 = new DatoAnalisis("Envios en ruta",String.valueOf(cantidadEnviosEnRuta));
            DatoAnalisis dato2 = new DatoAnalisis("Envios completados",String.valueOf(cantidadEnviosCompletos));
            DatoAnalisis dato3 = new DatoAnalisis("Ganancias Totales",String.valueOf(totalGanancias));
            DatoAnalisis dato4 = new DatoAnalisis("Gastos totales",String.valueOf(totalGastos));
            DatoAnalisis dato5 = new DatoAnalisis("Margen de beneficios",String.valueOf(margenBeneficios));  
            DatoAnalisis dato6 = new DatoAnalisis("Porcentaje de ganancias",String.valueOf(porcejateGanancia)+"%");          

            reporte.insertDatoGeneral(dato1);
            reporte.insertDatoGeneral(dato2);
            reporte.insertDatoGeneral(dato3);
            reporte.insertDatoGeneral(dato4);
            reporte.insertDatoGeneral(dato5);
            reporte.insertDatoGeneral(dato6);

            //Insertar a grafico gastos generales
            DatoAnalisis datoGastoPersonal = new DatoAnalisis("Personal",String.valueOf(totalGastosPersonal));
            reporte.insertDatoGastoGeneral(datoGastoPersonal);
            for (Object[]  gasto : gastosFijos) {                
                DatoAnalisis datoG1 = new DatoAnalisis(String.valueOf(gasto[0]),String.valueOf(gasto[1]));
                reporte.insertDatoGastoGeneral(datoG1);
            }

            //Insertar a grafico de gastos especiales
            for (Object[]  gasto : gastosEspeciales) {                
                DatoAnalisis datoG1 = new DatoAnalisis(String.valueOf(gasto[0]),String.valueOf(gasto[1]));
                reporte.insertDatoGastoEspecial(datoG1);
            }

            //Insertar sucursales con mas ganancias
            for (Object[]  gasto : sucursalesMas) {                
                DatoAnalisis datoG1 = new DatoAnalisis(String.valueOf(gasto[1]),String.valueOf(gasto[2]));
                reporte.insertDatoGanancias(datoG1);
            }

            //Insertar sucursales con menos ganancias
            for (Object[]  gasto : sucursalesMenos) {                
                DatoAnalisis datoG1 = new DatoAnalisis(String.valueOf(gasto[1]),String.valueOf(gasto[2]));
                reporte.insertDatoMenosGanancias(datoG1);
            }

                      
            return reporte;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }

    @PostMapping("/movsucursal")
    public ReporteSucursal getReporteMovSucursal(@RequestBody RecibidoReporteSucursal recibidoReporteSucursal){
        try {      
            //Calcular primer dia del mes y anio que enviaron
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(recibidoReporteSucursal.getFechaAnalizar(), formatter);

            // Obtener el primer día del mes
            LocalDate primerDiaDelMes = fecha.withDayOfMonth(1);        
            
            //Obtener primer dia del siguiente mes
            LocalDate primerDiaSiguienteMes = primerDiaDelMes.plusMonths(1).withDayOfMonth(1);
            
            //Traer envios recibidos de la sucursal entre el 1 del mes a la fecha limite
            List<Object[]> enviosRecibidos = reportesServicio.obtenerCantidadEnviosRecibidosEntreFechas(recibidoReporteSucursal.getSucursalForm(), primerDiaDelMes.toString(),fecha.toString());
            String cantidadDeEnviosRecibidos = String.valueOf(enviosRecibidos.size());

            //Traer envio enviados de la sucursal entre el 1 del mes a la fecha limite
            List<Object[]> enviosEnviados = reportesServicio.obtenerCantidadEnviosEnviadosEntreFechas(recibidoReporteSucursal.getSucursalForm(), primerDiaDelMes.toString(),fecha.toString());
            String cantidadDeEnviosEnviados = String.valueOf(enviosEnviados.size());

            //Traer envios que estan de paso de la sucursal entre el 1 del mes a la fecha limite

            //Traer cantidad de personal trabajando en la sucursal
            String cantPersonal = this.reportesServicio.obtenerCantidadPersonalTrabajanSucursal(recibidoReporteSucursal.getSucursalForm());

            //Calcular los gastos de una sucursal
                //Suma de gastos especiales y gastos fijos, mas pago de personal en el mes
                    //Gastos fijos
            List<Object[]> gastosFijos = this.reportesServicio.obtenerGastosFijosPorSucursal(recibidoReporteSucursal.getSucursalForm());

            Double totalGastosFijos = 0.0;
            for (Object[]  gasto : gastosFijos) {
                Double totalEnvio = (Double) gasto[3]; 
                totalGastosFijos += totalEnvio;
            }
                    //Gastos especiales
                    //Encontrar la asamblea a la que pertenece
            List<Object[]> asambleas = this.reportesServicio.obtenerAsambleaPorFecha(primerDiaSiguienteMes.toString());
            int idAsamblea = 0;
            for (Object[]  asamblea : asambleas) {
                idAsamblea = (int) asamblea[0];                
            }
                    //Encontrar los gastos
            List<Object[]> gastosEspeciales = this.reportesServicio.obtenerGastosEspeciales(recibidoReporteSucursal.getSucursalForm(),idAsamblea);   

            Double totalGastosEspeciales = 0.0;
            for (Object[]  gasto : gastosEspeciales) {
                Double totalGastosEspecial = (Double) gasto[3]; 
                totalGastosEspeciales += totalGastosEspecial;
            }      

                    //Encontrar gastos de pago a personal
            List<Object[]> gastosPersonal = this.reportesServicio.obtenerGastoPersonalPorSucursalYAsamblea(recibidoReporteSucursal.getSucursalForm(),idAsamblea);   
            Double totalGastosPersonal = 0.0;
            for (Object[]  gasto : gastosPersonal) {
                Double gastoPersonal = (Double) gasto[3]; 
                totalGastosPersonal += gastoPersonal;
            } 
            
            Double totalGastos = totalGastosFijos + totalGastosEspeciales + totalGastosPersonal;

            //Calcular las ganancias de una sucursal
                //Suma de todos los envios enviados en ese mes
            int ganancias = 0;
            for (Object[] envioEnviado : enviosEnviados) {
                int totalEnvio = (int) envioEnviado[6]; 
                ganancias += totalEnvio;
            }

            //Crear los datos de las tablas
            ReporteSucursal reporte = new ReporteSucursal();    

            //Datos de los envios recibidos
            for (Object[] envioRecibido : enviosRecibidos) {                
                DatoReporteSucursal datoRS1 = new DatoReporteSucursal(String.valueOf(envioRecibido[0]), String.valueOf(envioRecibido[5]), String.valueOf(envioRecibido[10])); 
                reporte.insertRecibido(datoRS1);
            }
            for (Object[] envioEnviado : enviosEnviados) {
                DatoReporteSucursal datoRS1 = new DatoReporteSucursal(String.valueOf(envioEnviado[0]), String.valueOf(envioEnviado[5]), String.valueOf(envioEnviado[10])); 
                reporte.insertEnviado(datoRS1);                
            }

            
            


                    
            DatoAnalisis dato1 = new DatoAnalisis("Envios Realizados",cantidadDeEnviosEnviados);
            DatoAnalisis dato2 = new DatoAnalisis("Envios Recibidos",cantidadDeEnviosRecibidos);
            DatoAnalisis dato3 = new DatoAnalisis("Personal Trabajando",cantPersonal);
            DatoAnalisis dato4 = new DatoAnalisis("Gastos",String.valueOf(totalGastos));
            DatoAnalisis dato5 = new DatoAnalisis("Ganancias",String.valueOf(ganancias));

            reporte.insertDato(dato1);
            reporte.insertDato(dato2);
            reporte.insertDato(dato3);
            reporte.insertDato(dato4);
            reporte.insertDato(dato5);       

            //reporte.insertPaso(datoRS1);
            
            //yyyy-mm-dd
            //System.out.println(recibidoReporteSucursal.getFechaAnalizar());
            //System.out.println(recibidoReporteSucursal.getSucursalForm());       

            
            return reporte;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }



}
