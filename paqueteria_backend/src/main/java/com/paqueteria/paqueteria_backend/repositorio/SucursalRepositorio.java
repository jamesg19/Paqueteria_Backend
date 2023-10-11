package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Sucursales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursales, Integer> {

    Sucursales findById(int id);



    List<Sucursales> findAll();

}
