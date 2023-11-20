package com.paqueteria.paqueteria_backend.controlador;

import com.paqueteria.paqueteria_backend.entidad.Persona;
import com.paqueteria.paqueteria_backend.errores.Error;
import com.paqueteria.paqueteria_backend.servicio.PersonaServicio;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaControlador {
    private PersonaServicio personaServicio;

    public PersonaControlador(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @GetMapping("/get_persona_id")
    public Persona getPersona(@RequestParam int id){
        try{
            return this.personaServicio.obtenerPersona(id);
        }catch (Exception e){
            System.out.println("Error en controlador persona por id"+e);
            return null;
        }
    }

    @GetMapping("/get_persona_nit")
    public ResponseEntity<Persona> getPersonaNit(@RequestParam String nit){
        var oPersona = personaServicio.obtenerPersonaNit(nit);

        if(oPersona.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(oPersona.get());
    }
    @GetMapping("/get_personas")
    public List<Persona> getPersonas(){
        try{
            return this.personaServicio.obtenerPersonas();
        }catch (Exception e){
            System.out.println("Error en controlador personas"+e);
            return null;
        }
    }

    @PostMapping("/save_persona")
    public ResponseEntity<Persona> savePersona(@RequestBody Persona persona) throws Error{
            if(personaServicio.obtenerPersonaNit(persona.getNit()).isPresent())
                throw new Error("Usuario Repetido",HttpStatus.BAD_REQUEST);
            var created = personaServicio.savePersona(persona);
            return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<Persona>> login(@RequestBody Persona persona) throws Error{
        System.out.println(persona.getId());
        System.out.println(persona.getPassword());
        var person=personaServicio.login(persona.getId(),persona.getPassword());
        System.out.println(person.isPresent());
        person.get().setPassword("");
        if(!person.isPresent())
            throw new Error("Credenciales incorrectas",HttpStatus.CONFLICT);

        return ResponseEntity.ok(person);
    }

    @PutMapping("/editar_persona")
    public ResponseEntity<Persona> editarPersona(@RequestBody Persona persona) throws Error{
        var created = personaServicio.updatePersona(persona);
        return ResponseEntity.ok(created);
    }
}
