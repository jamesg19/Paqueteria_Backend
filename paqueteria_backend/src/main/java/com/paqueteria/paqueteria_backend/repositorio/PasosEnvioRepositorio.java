package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.PasosEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasosEnvioRepositorio extends JpaRepository<PasosEnvio,Long> {
    List<PasosEnvio> findByIdEnvioOrderByIdAsc(int idEnvio);
    int deletePasosEnvioByIdEnvio(int idEnvio);
}
