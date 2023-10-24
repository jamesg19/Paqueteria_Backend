package com.paqueteria.paqueteria_backend.errores;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.LocalDateTime;

@Getter
public class Error extends Exception{
    private ProblemDetail detalle;

    public Error(String mensaje, HttpStatus status) {
        super(mensaje);
        this.detalle = ProblemDetail.forStatusAndDetail(status,mensaje);
        this.detalle.setProperty("Fecha", LocalDateTime.now());
    }
}
