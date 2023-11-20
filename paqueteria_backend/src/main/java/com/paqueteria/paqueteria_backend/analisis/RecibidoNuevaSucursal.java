package com.paqueteria.paqueteria_backend.analisis;

import java.util.List;

public class RecibidoNuevaSucursal {
    private Double longitud;
    public Double getLongitud() {
        return longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    private Double latitud;
    public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    private Integer cantidadVehiculos;
    public Integer getCantidadVehiculos() {
        return cantidadVehiculos;
    }
    public void setCantidadVehiculos(Integer cantidadVehiculos) {
        this.cantidadVehiculos = cantidadVehiculos;
    }
    private Integer cantidadPersonal;
    public Integer getCantidadPersonal() {
        return cantidadPersonal;
    }
    public void setCantidadPersonal(Integer cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
    }
    private List<String> sucursales;
    public List<String> getSucursales() {
        return sucursales;
    }
    public void setSucursales(List<String> sucursales) {
        this.sucursales = sucursales;
    }
    private List<Double> distancias;
    public List<Double> getDistancias() {
        return distancias;
    }
    public void setDistancias(List<Double> distancias) {
        this.distancias = distancias;
    }

    
}
