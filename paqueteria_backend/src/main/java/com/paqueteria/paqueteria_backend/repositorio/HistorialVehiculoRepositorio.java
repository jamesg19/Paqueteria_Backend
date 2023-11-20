package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.HistorialVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialVehiculoRepositorio extends JpaRepository<HistorialVehiculo,Long> {
    HistorialVehiculo findFirstByIdVehiculoOrderByFechaDesc(int idVehiculo);
}
