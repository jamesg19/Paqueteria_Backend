package com.paqueteria.paqueteria_backend.controlador;

import com.paqueteria.paqueteria_backend.entidad.Simulacion;
import com.paqueteria.paqueteria_backend.entidad.Variables;
import com.paqueteria.paqueteria_backend.servicio.VariablesMejoraServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variables")
public class VariablesControlador {
    @Autowired
    private VariablesMejoraServicio variablesServicio;

    @GetMapping("/get_all")
    public ResponseEntity<List<Variables>> getAll(HttpServletRequest request, HttpServletResponse response)  {
        return ResponseEntity.ok(this.variablesServicio.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Variables> update(@RequestBody Variables entrada, @RequestParam int id){
        return ResponseEntity.ok(this.variablesServicio.save(entrada));
    }
}
