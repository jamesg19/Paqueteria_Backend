package com.paqueteria.paqueteria_backend.reportes;

import java.util.ArrayList;
import java.util.List;

import com.paqueteria.paqueteria_backend.analisis.DatoAnalisis;

public class ReporteSucursal {
    private List<DatoAnalisis> datos;
    public List<DatoAnalisis> getDatos() {
        return datos;
    }

    public void setDatos(List<DatoAnalisis> datos) {
        this.datos = datos;
    }

    private List<DatoReporteSucursal> datostable1;    
    private List<DatoReporteSucursal> datostable2;
    private List<DatoReporteSucursal> datostable3;


    public List<DatoReporteSucursal> getDatostable3() {
        return datostable3;
    }

    public void setDatostable3(List<DatoReporteSucursal> datostable3) {
        this.datostable3 = datostable3;
    }

    public List<DatoReporteSucursal> getDatostable2() {
        return datostable2;
    }

    public void setDatostable2(List<DatoReporteSucursal> datostable2) {
        this.datostable2 = datostable2;
    }

    public List<DatoReporteSucursal> getDatostable1() {
        return datostable1;
    }

    public void setDatostable(List<DatoReporteSucursal> datostable) {
        this.datostable1 = datostable;
    }

    public ReporteSucursal() {
        this.datos = new ArrayList<DatoAnalisis>();
        this.datostable1 = new ArrayList<DatoReporteSucursal>();
        this.datostable2 = new ArrayList<DatoReporteSucursal>();
        this.datostable3 = new ArrayList<DatoReporteSucursal>();
    }

    public void insertDato(DatoAnalisis dato){
        this.datos.add(dato);
    }
    
    public void insertRecibido(DatoReporteSucursal dato){
        this.datostable1.add(dato);
    }

    public void insertEnviado(DatoReporteSucursal dato){
        this.datostable2.add(dato);
    }

    public void insertPaso(DatoReporteSucursal dato){
        this.datostable3.add(dato);
    }
}
