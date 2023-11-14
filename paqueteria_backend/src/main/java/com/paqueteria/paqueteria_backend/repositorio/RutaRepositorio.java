package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Municipio;
import com.paqueteria.paqueteria_backend.entidad.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RutaRepositorio extends  JpaRepository<Ruta, Long> {

    //Municipio findById(String id);

    List<Ruta> findAll();

    List<Ruta> findByOrigenEstado(boolean estado);

    /**
     * Busca si el destino esta disponible estado=(true/false)
     * @param estado
     * @return
     */
    List<Ruta> findByDestino_Estado(boolean estado);

    Optional<Ruta> findById(int id);

}
