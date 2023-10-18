package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDepto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal, String> {

    //Municipio findById(String id);

    Page<Sucursal> findAll(Pageable pageable);

    List<Sucursal> findByEstadoAndEsEnlace(boolean estado, boolean enlace);
    List<Sucursal> findByEstado(boolean estado);

    Optional<Sucursal> findByIdSucursal(long id);
    /**
     * SELECT S.idSucursal, S.nombre id, (
     *     SELECT nombre from Departamento where idDepartamento = S.idDepartamento
     * ) As Departamento,
     * (
     *     SELECT nombre from Municipio where idDepartamento = S.idDepartamento and idMunicipio = S.idMunicipio
     * ) As Municipio
     * from Sucursal As S;
     */
    //value="UPDATE cart_item SET quantity =:`quantity` WHERE (id =:`cartId`);\n", nativeQuery=true
    @Query(value="SELECT s.idDepartamento, d.idDepartamento1, COUNT(s.idSucursal) AS cantidadSucursales\n" +
            "FROM Sucursal s\n" +
            "JOIN Departamento d ON s.idDepartamento = d.idDepartamento\n" +
            "GROUP BY s.idDepartamento, d.idDepartamento1;", nativeQuery=true)
    List<SucursalDepto> getDataMap();

}
