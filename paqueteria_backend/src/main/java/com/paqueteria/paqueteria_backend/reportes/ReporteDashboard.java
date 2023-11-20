package com.paqueteria.paqueteria_backend.reportes;

import java.util.ArrayList;
import java.util.List;

import com.paqueteria.paqueteria_backend.analisis.DatoAnalisis;

public class ReporteDashboard {
    private List<DatoAnalisis> datosgenerales;
    private List<DatoAnalisis> datosgastosgenerales;
    private List<DatoAnalisis> datosgastosespeciales;
    private List<DatoAnalisis> datossucursalesganancias;
    private List<DatoAnalisis> datossucursalesmenosganancias;

    public ReporteDashboard() {
        this.datosgenerales = new ArrayList<DatoAnalisis>(); 
        this.datosgastosgenerales = new ArrayList<DatoAnalisis>(); 
        this.datosgastosespeciales = new ArrayList<DatoAnalisis>(); 
        this.datossucursalesganancias = new ArrayList<DatoAnalisis>(); 
        this.datossucursalesmenosganancias = new ArrayList<DatoAnalisis>();
    }
    public List<DatoAnalisis> getDatosgenerales() {
        return datosgenerales;
    }
    public void setDatosgenerales(List<DatoAnalisis> datosgenerales) {
        this.datosgenerales = datosgenerales;
    }
    
    public List<DatoAnalisis> getDatosgastosgenerales() {
        return datosgastosgenerales;
    }
    public void setDatosgastosgenerales(List<DatoAnalisis> datosgastosgenerales) {
        this.datosgastosgenerales = datosgastosgenerales;
    }
    
    public List<DatoAnalisis> getDatosgastosespeciales() {
        return datosgastosespeciales;
    }
    public void setDatosgastosespeciales(List<DatoAnalisis> datosgastosespeciales) {
        this.datosgastosespeciales = datosgastosespeciales;
    }
    
    public List<DatoAnalisis> getDatossucursalesganancias() {
        return datossucursalesganancias;
    }
    public void setDatossucursalesganancias(List<DatoAnalisis> datossucursalesganancias) {
        this.datossucursalesganancias = datossucursalesganancias;
    }
    
    public List<DatoAnalisis> getDatossucursalesmenosganancias() {
        return datossucursalesmenosganancias;
    }
    public void setDatossucursalesmenosganancias(List<DatoAnalisis> datossucursalesmenosganancias) {
        this.datossucursalesmenosganancias = datossucursalesmenosganancias;
    }

    public void insertDatoGeneral(DatoAnalisis dato){
        this.datosgenerales.add(dato);
    }

    public void insertDatoGastoGeneral(DatoAnalisis dato){
        this.datosgastosgenerales.add(dato);
    }
    public void insertDatoGastoEspecial(DatoAnalisis dato){
        this.datosgastosespeciales.add(dato);
    }
    public void insertDatoGanancias(DatoAnalisis dato){
        this.datossucursalesganancias.add(dato);
    }
    public void insertDatoMenosGanancias(DatoAnalisis dato){
        this.datossucursalesmenosganancias.add(dato);
    }

    
}
