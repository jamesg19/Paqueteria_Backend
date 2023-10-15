package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal, String> {

    //Municipio findById(String id);

    List<Sucursal> findAll();

    List<Sucursal> findByEstadoAndEsEnlace(boolean estado, boolean enlace);
    List<Sucursal> findByEstado(boolean estado);

    Optional<Sucursal> findByIdSucursal(long id);
    //INSERT INTO paqueteria.sucursal (idDepartamento, idMunicipio, direccion, nombre, esEnlace, estado) VALUES (8, 5, 'ddddd', 'nnnnnnnnnn', 1, 1)
    //@Query("SELECT NEW com.example.model.Sucursal(sDto.id, sDto.departamento, sDto.municipio, sDto.direccion, sDto.nombre, sDto.esEnlace, sDto.estado) FROM SucursalDto sDto WHERE sDto.id = :id")
    //@Query("")
    //Sucursal mapDtoToEntity(@Param("id") String id);
    //Optional<Sucursal> findByIdAndEstado(String id, boolean estado);
}
