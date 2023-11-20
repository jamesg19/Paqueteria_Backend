package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findById(int id);

    List<Vehiculo> findBySucursalIdSucursal(int id);


}
