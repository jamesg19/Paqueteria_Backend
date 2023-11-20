package com.paqueteria.paqueteria_backend.reportes;

import java.util.ArrayList;
import java.util.List;

import com.paqueteria.paqueteria_backend.analisis.DatoAnalisis;

public class ReporteVehiculo {
    private List<DatoAnalisis> datos;
    public List<DatoAnalisis> getDatos() {
        return datos;
    }

    public void setDatos(List<DatoAnalisis> datos) {
        this.datos = datos;
    }

    private List<DatoReporteVechiculo> datostable;

    public List<DatoReporteVechiculo> getDatostable() {
        return datostable;
    }

    public void setDatostable(List<DatoReporteVechiculo> datostable) {
        this.datostable = datostable;
    }

    public ReporteVehiculo() {
        this.datos = new ArrayList<DatoAnalisis>();
        this.datostable = new ArrayList<DatoReporteVechiculo>();
    }

    public void insertDato(DatoAnalisis dato){
        this.datos.add(dato);
    }
    
    public void insertDatoTable(DatoReporteVechiculo dato){
        this.datostable.add(dato);
    }

    

}
