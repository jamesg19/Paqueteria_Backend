package com.paqueteria.paqueteria_backend.controlador;


import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDepto;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDto;
import com.paqueteria.paqueteria_backend.servicio.DepartamentoServicio;
import com.paqueteria.paqueteria_backend.servicio.SucursalServicio;
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
@RequestMapping("/api/sucursal")
public class SucursalControlador {

//localhost:8443/api/sucursales/get_sucursales
    private SucursalServicio sucursalServicio;

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param sucursalServicio
     */
    public SucursalControlador(SucursalServicio sucursalServicio) {
        this.sucursalServicio = sucursalServicio;
    }


    @GetMapping("/get_sucursal_id")
    public Optional<Sucursal> getSucursalId(HttpServletRequest request, HttpServletResponse response, @RequestParam long id)  {
        try {
            Optional<Sucursal> departamentos =this.sucursalServicio.obtenerSucursalId(id);

            return departamentos;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }

    @GetMapping("/get_sucursales")
    public List<Sucursal> getSucursales(HttpServletRequest request, HttpServletResponse response, @RequestParam boolean estado)  {
        try {
            List<Sucursal> departamentos =this.sucursalServicio.obtenerSucursalesEstado(estado);

            return departamentos;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }

    @GetMapping("/get_todas_sucursales")
    public Page<Sucursal> getAllSucursales(HttpServletRequest request, HttpServletResponse response,Pageable pageable  )  {

        try {
            Page<Sucursal> departamentos =this.sucursalServicio.obtenerTodasSucursales(pageable);

            return departamentos;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }

    }

    @PostMapping("/save_sucursal")
    public ResponseEntity<String> saveSucursal(HttpServletRequest request, HttpServletResponse response, @RequestBody SucursalDto sucursal)  {

        try {
            //System.out.println(sucursal.getDepartamento().getId());
            return this.sucursalServicio.saveSucursal(sucursal);
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>("", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/editar_sucursal")
    public ResponseEntity<String> editarSucursal(HttpServletRequest request, HttpServletResponse response, @RequestBody SucursalDto sucursal)  {

        try {
            //System.out.println(sucursal.getDepartamento().getId());
            return this.sucursalServicio.editarSucursal(sucursal);
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>("", HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/get_sucursales_departamento")
    public List<SucursalDepto> getSucursalPorDepartamento(HttpServletRequest request, HttpServletResponse response)  {

        try {
            //System.out.println(sucursal.getDepartamento().getId());
            List<SucursalDepto> obj= this.sucursalServicio.obtenerSucursalPorDepartamento();
            return obj;
        }
        catch( Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }



}
