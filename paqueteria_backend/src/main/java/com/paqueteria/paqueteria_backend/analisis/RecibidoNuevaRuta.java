package com.paqueteria.paqueteria_backend.analisis;

import java.util.List;

public class RecibidoNuevaRuta {
    private String sucursalorigen;
    private List<String> sucursales;
    private List<Double> distancias;

    
    public String getSucursalorigen() {
        return sucursalorigen;
    }
    public void setSucursalorigen(String sucursalorigen) {
        this.sucursalorigen = sucursalorigen;
    }
    
    public List<String> getSucursales() {
        return sucursales;
    }
    public void setSucursales(List<String> sucursales) {
        this.sucursales = sucursales;
    }
    
    public List<Double> getDistancias() {
        return distancias;
    }
    public void setDistancias(List<Double> distancias) {
        this.distancias = distancias;
    }

    
}
