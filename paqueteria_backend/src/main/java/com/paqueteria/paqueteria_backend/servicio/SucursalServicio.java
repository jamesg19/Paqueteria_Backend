package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDepto;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDto;
import com.paqueteria.paqueteria_backend.repositorio.DepartamentoRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.SucursalDtoRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalServicio {

    private SucursalRepositorio sucursalRepositorio;
    private SucursalDtoRepositorio sucursalDtoRepositorio;
    private EntityManager entityManager;

    /**
     * Constructor
     * @param sucursalRepositorio
     */
    public SucursalServicio(SucursalRepositorio sucursalRepositorio, SucursalDtoRepositorio sucursalDtoRepositorio, EntityManager entityManager) {
        this.sucursalRepositorio = sucursalRepositorio;
        this.sucursalDtoRepositorio=sucursalDtoRepositorio;
        this.entityManager=entityManager;
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

    public ResponseEntity<String> editarSucursal(SucursalDto sucursal){
        try{

            SucursalDto suc=this.sucursalDtoRepositorio.findByIdSucursal(sucursal.getIdSucursal());
            if(suc != null){

                suc.setEstado(sucursal.isEstado());
                suc.setEsEnlace(sucursal.isEsEnlace());
                this.sucursalDtoRepositorio.save(suc);
            }
            return new ResponseEntity<>("", HttpStatus.OK);

        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error al guardar sucursal: "+e, HttpStatus.CONFLICT);
        }

    }

    public ResponseEntity<String> saveSucursal(SucursalDto sucursal){
        try{


            System.out.println("Estado: "+sucursal.isEstado());
            System.out.println("Enlace: "+sucursal.isEsEnlace());
            SucursalDto suc2=this.sucursalDtoRepositorio.save(sucursal);


            return new ResponseEntity<>("", HttpStatus.OK);

        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error al guardar sucursal: "+e, HttpStatus.CONFLICT);
        }

    }

    public List<SucursalDepto> obtenerSucursalPorDepartamento(){
        String sqlQuery = "SELECT d.idDepartamento, d.idDepartamento1, COUNT(s.idSucursal) AS cantidadSucursales\n" +
                "FROM Departamento d\n" +
                "LEFT JOIN Sucursal s ON s.idDepartamento = d.idDepartamento AND s.estado=1\n" +
                "GROUP BY d.idDepartamento, d.idDepartamento1;";

        Query query = entityManager.createNativeQuery(sqlQuery);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        // Mapeo manual de los resultados a objetos SucursalDepto
        List<SucursalDepto> sucursalDeptos = new ArrayList<>();
        for (Object[] result : results) {
            int idDepartamento = ((Number) result[0]).intValue();
            String idDepartamento1 = ((String) result[1]).toString();
            int cantidadSucursales = ((Number) result[2]).intValue();

            SucursalDepto sucursalDepto = new SucursalDepto(idDepartamento, idDepartamento1, cantidadSucursales);
            sucursalDeptos.add(sucursalDepto);
        }

        return sucursalDeptos;
    }



}
