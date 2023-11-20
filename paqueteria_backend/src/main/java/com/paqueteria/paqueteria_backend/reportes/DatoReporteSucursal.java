package com.paqueteria.paqueteria_backend.reportes;

public class DatoReporteSucursal {
    private String idEnvio;
    public DatoReporteSucursal(String idEnvio, String fecha, String estado) {
        this.idEnvio = idEnvio;
        this.fecha = fecha;
        this.estado = estado;
    }
    public String getIdEnvio() {
        return idEnvio;
    }
    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }
    private String fecha;
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    private String estado;
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
