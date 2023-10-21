package com.paqueteria.paqueteria_backend.repositorio;

import com.paqueteria.paqueteria_backend.entidad.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface EnvioRepositorio extends JpaRepository<Envio,Long> {
    Envio findEnvioById(int idEnvio);
    List<Envio> findAll();

}
