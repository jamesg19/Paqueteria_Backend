package com.paqueteria.paqueteria_backend.repositorio;


import com.paqueteria.paqueteria_backend.entidad.dto.VehiculoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoDtoRepositorio extends JpaRepository<VehiculoDto, String> {


}
