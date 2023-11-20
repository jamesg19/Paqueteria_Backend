package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Variables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariablesMejoraRepo extends JpaRepository<Variables,Long> {
    Variables getVariablesById(int id);
}
