package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.CantidadEnvioPorSucursal;
import com.paqueteria.paqueteria_backend.entidad.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CantidadEnvioPorSucursalRepositorio extends JpaRepository<CantidadEnvioPorSucursal,String> {

    List<CantidadEnvioPorSucursal> findAll();

}
