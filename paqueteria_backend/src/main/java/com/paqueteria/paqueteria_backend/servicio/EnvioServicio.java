package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Envio;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimpleRepo;
import com.paqueteria.paqueteria_backend.repositorio.EnvioRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioServicio {
    @Autowired
    private EnvioRepositorio envioRepo;
    @Autowired
    private SucursalRepositorio sucursalRepo;
    @Autowired
    private EnvioSimpleRepo envioSimpleRepo;
    public Envio obtenerEnvioId(int id){
        return this.envioRepo.findEnvioById(id);
    }
    public List<Envio> obtenerEnvios(){
        return this.envioRepo.findAll();
    }
    public EnvioSimple saveEnvio(EnvioSimple envio){
        return this.envioSimpleRepo.save(envio);
    }

    public List<EnvioSimple> getEnviosIdSucursalOrigen(long id){return this.envioSimpleRepo.findEnvioSimpleByIdSucursalOrigen(id);}

    public List<EnvioSimple> getAll(){return this.envioSimpleRepo.findAll();}
}
