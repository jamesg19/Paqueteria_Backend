package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {

    //Municipio findById(String id);

    List<Departamento> findAll();

    Optional<Departamento> findById(int idDepartamento);
}
