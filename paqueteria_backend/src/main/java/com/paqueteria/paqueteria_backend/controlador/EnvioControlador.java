package com.paqueteria.paqueteria_backend.controlador;

import com.paqueteria.paqueteria_backend.entidad.Envio;
import com.paqueteria.paqueteria_backend.entidad.HistoricoSucursal;
import com.paqueteria.paqueteria_backend.entidad.PasosEnvio;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import com.paqueteria.paqueteria_backend.errores.Error;
import com.paqueteria.paqueteria_backend.servicio.EnvioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        return ResponseEntity.ok(this.envioServicio.saveEnvio(envioEntrada));
    }

    @GetMapping("/get_sucursal_id")
    public ResponseEntity<List<EnvioSimple>> getBySucursalOrige(@RequestParam long id)throws Error{
        return ResponseEntity.ok(this.envioServicio.getEnviosIdSucursalOrigen(id));
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<EnvioSimple>> getAll() throws Error{
        return ResponseEntity.ok(this.envioServicio.getAll());
    }

    @GetMapping("/get_pasos_envio")
    public ResponseEntity<List<PasosEnvio>> getPasosEnvio(@RequestParam int idEnvio) throws  Error{
        return ResponseEntity.ok(this.envioServicio.getPasosEnvio(idEnvio));
    }

    @DeleteMapping("/delete_pasos_envio")
    public ResponseEntity deletePasosEnvio(@RequestParam int idEnvio) throws Error {
        return ResponseEntity.ok(this.envioServicio.deletePasosEnvio(idEnvio));
    }
    @PostMapping("/save_pasos_envio")
    public ResponseEntity<List<PasosEnvio>> savePasosEnvio(@RequestBody List<Sucursal> sucursales, @RequestParam int idEnvio) throws Error{
        List <PasosEnvio> retorno = new ArrayList<>();
        sucursales.stream().forEach(data->{
            PasosEnvio auxPasos = new PasosEnvio();
            auxPasos.setIdSucursal(data.getIdSucursal());
            auxPasos.setIdEnvio(idEnvio);
            retorno.add(envioServicio.savePasosEnvio(auxPasos));
        });
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/get_historico_sucursales")
    public ResponseEntity<List<HistoricoSucursal>> getHistoricoSucursales(@RequestParam int idEnvio) throws  Error{
        return ResponseEntity.ok(this.envioServicio.getHistorico(idEnvio));
    }

    @PostMapping("/save_historico_sucursales")
    public  ResponseEntity<HistoricoSucursal> saveHistoricoSucursal(@RequestBody HistoricoSucursal historicoSucursal) throws Error{
        return ResponseEntity.ok(this.envioServicio.saveHistoricoSucursal(historicoSucursal));
    }
 }
