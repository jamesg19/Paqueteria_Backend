package com.paqueteria.paqueteria_backend.analisis;
import java.util.ArrayList;
import java.util.List;

public class AnalisisVehiculo {
    private String descripcion;
    private String recomendacion;
    private List<DatoAnalisis> datos;
    private List<MultiDatos> multiDatos;

    public AnalisisVehiculo() {
        this.datos = new ArrayList<DatoAnalisis>();
        this.multiDatos = new ArrayList<MultiDatos>();
    }

    public List<DatoAnalisis> getDatos() {
        return datos;
    }

    public void setDatos(List<DatoAnalisis> datos) {
        this.datos = datos;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void insertDatoAnalisis(DatoAnalisis dato){
        this.datos.add(dato);
    }

    public List<MultiDatos> getMultiDatos() {
        return multiDatos;
    }

    public void setMultiDatos(List<MultiDatos> multiDatos) {
        this.multiDatos = multiDatos;
    }

    public void insertMultiDato(MultiDatos multiDato){
        this.multiDatos.add(multiDato);
    }

    
}
