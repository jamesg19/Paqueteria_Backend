package com.paqueteria.paqueteria_backend.entidad;

import java.io.Serializable;
import java.util.Objects;

public class SucursalId implements Serializable {
    private String idSucursal;
    private String idMunicipio;

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    // Getter y Setter para idMunicipio
    public String getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SucursalId that = (SucursalId) o;
        return Objects.equals(idSucursal, that.idSucursal) &&
               Objects.equals(idMunicipio, that.idMunicipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSucursal, idMunicipio);
    }
}