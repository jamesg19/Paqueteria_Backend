package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona,Long> {
    Persona findPersonaById(int id);
    Optional<Persona> findPersonaByNit(String nit);
    List<Persona> findAll();

    Optional<Persona> findOnePersonaByIdAndPassword(int id,String password);
}
