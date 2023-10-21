package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Persona;
import com.paqueteria.paqueteria_backend.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServicio {
    @Autowired
    private PersonaRepositorio personaRepo;
    public List<Persona> obtenerPersonas(){
        return this.personaRepo.findAll();
    }

    public Persona obtenerPersona(int id){
        return this.personaRepo.findPersonaById(id);
    }

    public Optional<Persona> obtenerPersonaNit(String nit){return this.personaRepo.findPersonaByNit(nit);}
    public Persona savePersona(Persona persona){
        return personaRepo.save(persona);
    }
}
