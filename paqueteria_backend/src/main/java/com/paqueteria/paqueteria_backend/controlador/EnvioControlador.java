package com.paqueteria.paqueteria_backend.controlador;

import com.paqueteria.paqueteria_backend.entidad.Envio;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import com.paqueteria.paqueteria_backend.errores.Error;
import com.paqueteria.paqueteria_backend.servicio.EnvioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/envios")
public class EnvioControlador {
    @Autowired
    private EnvioServicio envioServicio;
    @GetMapping("/get_envio_id")
    public ResponseEntity<Envio> getEnvio(@RequestParam int idEnvio) throws Error{
        var envio = this.envioServicio.obtenerEnvioId(idEnvio);
        if(Objects.isNull(envio))throw new Error("No existe envio", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(envio);
    }


    @PostMapping("/save_envio")
    public ResponseEntity<EnvioSimple> save(@RequestBody EnvioSimple envioEntrada)throws Error {
        System.out.println(envioEntrada);
        return ResponseEntity.ok(this.envioServicio.saveEnvio(envioEntrada));
    }
}
