package com.paqueteria.paqueteria_backend.entidad.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioSimpleRepo extends JpaRepository<EnvioSimple,Long> {
    EnvioSimple findById(long id);
}
