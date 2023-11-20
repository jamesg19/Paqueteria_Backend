package com.paqueteria.paqueteria_backend.analisis;

import java.util.ArrayList;
import java.util.List;

public class MultiDatos {
    private String name;
    private List<DatoAnalisis> series;

    public MultiDatos() {
        this.series = new ArrayList<DatoAnalisis>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<DatoAnalisis> getSeries() {
        return series;
    }

    public void setSeries(List<DatoAnalisis> series) {
        this.series = series;
    }

    public void insertDatoAnalisis(DatoAnalisis dato){
        this.series.add(dato);
    }
  
}
