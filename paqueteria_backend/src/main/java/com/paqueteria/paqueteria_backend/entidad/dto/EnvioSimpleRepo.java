package com.paqueteria.paqueteria_backend.entidad.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioSimpleRepo extends JpaRepository<EnvioSimple,Long> {
    EnvioSimple findById(long id);
    List<EnvioSimple> findEnvioSimpleByIdSucursalOrigen(long id);

    List<EnvioSimple> findByEstado(String estado);

}
