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
            ReporteSucursal reporte = new ReporteSucursal();            
            DatoAnalisis dato1 = new DatoAnalisis("Envios Realizados","14");
            DatoAnalisis dato2 = new DatoAnalisis("Envios Recibidos","14");
            DatoAnalisis dato3 = new DatoAnalisis("Personal Trabajando","14");
            DatoAnalisis dato4 = new DatoAnalisis("Gastos","14");
            DatoAnalisis dato5 = new DatoAnalisis("Ganancias","14");

            reporte.insertDato(dato1);
            reporte.insertDato(dato2);
            reporte.insertDato(dato3);
            reporte.insertDato(dato4);
            reporte.insertDato(dato5);

            DatoReporteSucursal datoRS1 = new DatoReporteSucursal("1", "2020-01-01", "Entregado");
            DatoReporteSucursal datoRS2 = new DatoReporteSucursal("3", "2020-01-02", "Entregado");
            DatoReporteSucursal datoRS3 = new DatoReporteSucursal("7", "2020-01-03", "Entregado");

            reporte.insertEnviado(datoRS1);
            reporte.insertEnviado(datoRS2);
            reporte.insertEnviado(datoRS3);


            reporte.insertRecibido(datoRS3);

            reporte.insertPaso(datoRS1);
            
            //yyyy-mm-dd
            System.out.println(recibidoReporteSucursal.getFechaAnalizar());
            System.out.println(recibidoReporteSucursal.getSucursalForm());       
            
            //Traer envios recibidos de la sucursal entre el 1 del mes a la fecha limite

            //Traer envio enviados de la sucursal entre el 1 del mes a la fecha limite

            //Traer envios que estan de paso de la sucursal entre el 1 del mes a la fecha limite

            //Traer cantidad de personal trabajando en la sucursal
            

            //Calcular los gastos de una sucursal

            //Calcular las ganancias de una sucursal

            
            return reporte;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }        
    }



}
