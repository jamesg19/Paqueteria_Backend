package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.SucursalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SucursalDtoRepositorio extends JpaRepository<SucursalDto, String> {

    SucursalDto findByIdSucursal(int id);
}
