package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipioRepositorio extends JpaRepository<Municipio, Long> {

    //Municipio findById(String id);

    List<Municipio> findAll();
    List<Municipio> findByDepartamentoId(int id);

    Optional<Municipio> findById(int id);

}
