package com.paqueteria.paqueteria_backend.reportes;

public class DatoReporteVechiculo {

    private String fecha;
    private String sucursal;
    private String capacidad;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCapacidad() {
        return capacidad;
    }


    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public DatoReporteVechiculo(String fecha, String sucursal, String capacidad) {
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.capacidad = capacidad;
    }

    

    
}
