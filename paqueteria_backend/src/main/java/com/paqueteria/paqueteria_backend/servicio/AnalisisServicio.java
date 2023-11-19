package com.paqueteria.paqueteria_backend.servicio;

import org.springframework.stereotype.Service;

import com.paqueteria.paqueteria_backend.repositorio.SucursalDtoRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@Service
public class AnalisisServicio {
    private SucursalRepositorio sucursalRepositorio;

    public AnalisisServicio(SucursalRepositorio sucursalRepositorio) {
        this.sucursalRepositorio = sucursalRepositorio;
    }

    
    
}
