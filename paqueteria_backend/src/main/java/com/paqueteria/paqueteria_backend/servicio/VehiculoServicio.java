package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.HistorialVehiculo;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.Vehiculo;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDepto;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDto;
import com.paqueteria.paqueteria_backend.entidad.dto.VehiculoDto;
import com.paqueteria.paqueteria_backend.repositorio.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServicio {

    private VehiculoRepositorio vehiculoRepositorio;
    private VehiculoDtoRepositorio vehiculoDtoRepositorio;
    @Autowired
    private HistorialVehiculoRepositorio historicoVehiculo;
    public VehiculoServicio(VehiculoRepositorio vehiculoRepositorio,VehiculoDtoRepositorio vehiculoDtoRepositorio) {
        this.vehiculoRepositorio=vehiculoRepositorio;
        this.vehiculoDtoRepositorio=vehiculoDtoRepositorio;
    }


    public List<Vehiculo> listarPorSucursalId(int id){
        return this.vehiculoRepositorio.findBySucursalIdSucursal(id);
    }

    public ResponseEntity<String> saveVehiculo(VehiculoDto vehiculo){
        try{

            VehiculoDto veh=this.vehiculoDtoRepositorio.save(vehiculo);



            return new ResponseEntity<>("", HttpStatus.OK);

        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error al guardar vehiculo: "+e, HttpStatus.CONFLICT);
        }

    }


    public ResponseEntity<String> editarVehiculo(VehiculoDto vehiculo){
        try{
            this.vehiculoDtoRepositorio.save(vehiculo);

            return new ResponseEntity<>("", HttpStatus.OK);

        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error al editar vehiculo: "+e, HttpStatus.CONFLICT);
        }

    }






 public HistorialVehiculo getVehiculoHistrorial(int idVehiculo) throws Error{
        return this.historicoVehiculo.findFirstByIdVehiculoOrderByFechaDesc(idVehiculo);
    }


}
