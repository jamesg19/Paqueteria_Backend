package com.paqueteria.paqueteria_backend.errores;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class Manejador extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Error.class)
    public ResponseEntity<?> errorClase(Error er){
        System.out.println("Paso antes en error personalizado");
        return new ResponseEntity<>(er.getDetalle(),(HttpStatus.valueOf(er.getDetalle().getStatus())));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> errorIo(IOException io){
        System.out.println("Paso antes en io");
        return new ResponseEntity<>(io.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
