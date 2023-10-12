package com.paqueteria.paqueteria_backend.servicio;


import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServicio {

    private SucursalRepositorio sucursalRepositorio;

    /**
     * Constructor
     * @param sucursalRepositorio
     */
    public SucursalServicio(SucursalRepositorio sucursalRepositorio) {
        this.sucursalRepositorio = sucursalRepositorio;
    }

    public Sucursal obtenerSucursalId(int id){
        /**
         * Aqui en servicio toda la logica
         */
        //Hace la consulta por medio del repositorio que accede a la base de datos
        Sucursal mySucursal=this.sucursalRepositorio.findById(id);

        return mySucursal;
    }

    /**
     * Guardar
     */
    public void guardarSucursal(){
        Sucursal sucursalNueva=new Sucursal();



    }

    /**
     * Obtiene todas las sucursales
     * @param
     * @return
     */

    public List<Sucursal> obtenerSucursales(){
        /**
         * Aqui en servicio toda la logica
         */
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Sucursal> mySucursal=this.sucursalRepositorio.findAll();

        return mySucursal;
    }

}
