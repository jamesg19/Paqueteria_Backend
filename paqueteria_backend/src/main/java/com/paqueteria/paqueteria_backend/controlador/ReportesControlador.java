package com.paqueteria.paqueteria_backend.controlador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDepto;
import com.paqueteria.paqueteria_backend.reportes.DatoReporteSucursal;
import com.paqueteria.paqueteria_backend.reportes.DatoReporteVechiculo;
import com.paqueteria.paqueteria_backend.reportes.RecibidoReporteSucursal;
import com.paqueteria.paqueteria_backend.reportes.ReporteDashboard;
import com.paqueteria.paqueteria_backend.reportes.ReporteSucursal;
import com.paqueteria.paqueteria_backend.reportes.ReporteVehiculo;
import com.paqueteria.paqueteria_backend.servicio.ReportesServicio;


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
            DatoAnalisis dato1 = new DatoAnalisis("Movimientos realizados","3");
            DatoAnalisis dato2 = new DatoAnalisis("Envios entregados","20");            

            reporte.insertDato(dato1);
            reporte.insertDato(dato2);
            
            DatoReporteVechiculo datoReporteVechiculo1 = new DatoReporteVechiculo("2020-01-01", "5", "10");
            DatoReporteVechiculo datoReporteVechiculo2 = new DatoReporteVechiculo("2020-01-03", "2", "50");
            DatoReporteVechiculo datoReporteVechiculo3 = new DatoReporteVechiculo("2020-01-04", "5", "12");

            reporte.insertDatoTable(datoReporteVechiculo1);
            reporte.insertDatoTable(datoReporteVechiculo2);
            reporte.insertDatoTable(datoReporteVechiculo3);
            
            System.out.println(vehiculoForm);            
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
            ReporteDashboard reporte = new ReporteDashboard();
            DatoAnalisis dato1 = new DatoAnalisis("Envios en ruta","3");
            DatoAnalisis dato2 = new DatoAnalisis("Envios completados","20");
            DatoAnalisis dato3 = new DatoAnalisis("Ganancias Totales","50");
            DatoAnalisis dato4 = new DatoAnalisis("Gastos totales","25");
            DatoAnalisis dato5 = new DatoAnalisis("Margen de beneficios","25");  
            DatoAnalisis dato6 = new DatoAnalisis("Porcentaje de ganancias","50%");          

            reporte.insertDatoGeneral(dato1);
            reporte.insertDatoGeneral(dato2);
            reporte.insertDatoGeneral(dato3);
            reporte.insertDatoGeneral(dato4);
            reporte.insertDatoGeneral(dato5);
            reporte.insertDatoGeneral(dato6);

            reporte.insertDatoGastoGeneral(dato4);

            reporte.insertDatoGastoEspecial(dato5);

            reporte.insertDatoGanancias(dato3);
            reporte.insertDatoGanancias(dato4);

            reporte.insertDatoMenosGanancias(dato1);


            
            System.out.println(fechaAnalizar);            
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
            
            Double totalGastos = totalGastosFijos + totalGastosEspeciales;

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
