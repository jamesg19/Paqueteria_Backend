package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.HistoricoSucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoSucursalRepo extends JpaRepository<HistoricoSucursal,Long> {
    List<HistoricoSucursal> findByIdEnvioOrderByFechaDesc(int id);
}
