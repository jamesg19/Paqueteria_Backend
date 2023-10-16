package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDto;
import com.paqueteria.paqueteria_backend.repositorio.DepartamentoRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.SucursalDtoRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalServicio {

    private SucursalRepositorio sucursalRepositorio;
    private SucursalDtoRepositorio sucursalDtoRepositorio;

    /**
     * Constructor
     * @param sucursalRepositorio
     */
    public SucursalServicio(SucursalRepositorio sucursalRepositorio, SucursalDtoRepositorio sucursalDtoRepositorio) {
        this.sucursalRepositorio = sucursalRepositorio;
        this.sucursalDtoRepositorio=sucursalDtoRepositorio;
    }



    public Optional<Sucursal> obtenerSucursalId(long id){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        Optional<Sucursal> sucursales=this.sucursalRepositorio.findByIdSucursal(id);

        return sucursales;
    }

    public List<Sucursal> obtenerSucursalesEstado(boolean estado){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Sucursal> sucursales=this.sucursalRepositorio.findByEstado(estado);

        return sucursales;
    }

    public Page<Sucursal> obtenerTodasSucursales(Pageable pageable ){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        Page<Sucursal> sucursales=this.sucursalRepositorio.findAll(pageable);

        return sucursales;
    }

    public ResponseEntity<String> saveSucursal(SucursalDto sucursal){
        try{

            this.sucursalDtoRepositorio.save(sucursal);

            return new ResponseEntity<>("", HttpStatus.OK);

        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error al guardar sucursal: "+e, HttpStatus.CONFLICT);
        }

    }



}
