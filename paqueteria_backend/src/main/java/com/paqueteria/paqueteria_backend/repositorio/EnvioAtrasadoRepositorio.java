package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.EnvioAtrasado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioAtrasadoRepositorio extends JpaRepository<EnvioAtrasado,long> {
    int deleteByIdEnvio(int idEnvio);
    EnvioAtrasado getEnvioAtrasadoById(int id);
}
