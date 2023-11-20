package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.Vehiculo;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDepto;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDto;
import com.paqueteria.paqueteria_backend.entidad.dto.VehiculoDto;
import com.paqueteria.paqueteria_backend.servicio.SucursalServicio;
import com.paqueteria.paqueteria_backend.servicio.VehiculoServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoControlador {

//localhost:8443/api/sucursales/get_sucursales
    private SucursalServicio sucursalServicio;
    private VehiculoServicio vehiculoServicio;

    public VehiculoControlador(SucursalServicio sucursalServicio, VehiculoServicio vehiculoServicio) {
        this.sucursalServicio = sucursalServicio;
        this.vehiculoServicio=vehiculoServicio;
    }


    @GetMapping("/getVehiculosPorSucursalId")
    public List<Vehiculo> getSucursalId(HttpServletRequest request, HttpServletResponse response, @RequestParam int  id)  {
       return this.vehiculoServicio.listarPorSucursalId(id);
    }





    @PostMapping("/save_vehiculo")
    public ResponseEntity<String> saveVehiculo(HttpServletRequest request, HttpServletResponse response, @RequestBody VehiculoDto vehiculo)  {

        try {
            //System.out.println(sucursal.getDepartamento().getId());
            return this.vehiculoServicio.saveVehiculo(vehiculo);
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>("", HttpStatus.CONFLICT);
        }
    }



    @PostMapping("/editar_vehiculo")
    public ResponseEntity<String> editarvehiculo(HttpServletRequest request, HttpServletResponse response, @RequestBody VehiculoDto vehiculo)  {

        try {
            System.out.println("Entra...");
            //System.out.println(sucursal.getDepartamento().getId());
            return this.vehiculoServicio.editarVehiculo(vehiculo);
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>("", HttpStatus.CONFLICT);
        }
    }


}