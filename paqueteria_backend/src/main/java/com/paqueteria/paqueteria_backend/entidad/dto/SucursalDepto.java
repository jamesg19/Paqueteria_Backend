package com.paqueteria.paqueteria_backend.entidad.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SucursalDepto {
    private int idDepartamento;
    private String idDepartamento1;
    private int cantidadSucursales;

    public SucursalDepto(int idDepartamento, String idDepartamento1, int cantidadSucursales) {
        this.idDepartamento = idDepartamento;
        this.idDepartamento1 = idDepartamento1;
        this.cantidadSucursales = cantidadSucursales;
    }
}
