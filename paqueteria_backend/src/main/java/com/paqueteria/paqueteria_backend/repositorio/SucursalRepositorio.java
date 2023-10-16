package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
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

}
