package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Envio;
import com.paqueteria.paqueteria_backend.entidad.EnvioAtrasado;
import com.paqueteria.paqueteria_backend.entidad.HistoricoSucursal;
import com.paqueteria.paqueteria_backend.entidad.PasosEnvio;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimple;
import com.paqueteria.paqueteria_backend.entidad.dto.EnvioSimpleRepo;
import com.paqueteria.paqueteria_backend.repositorio.*;
import jakarta.transaction.Transactional;
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
    @Autowired
    PasosEnvioRepositorio envioPasosRepo;
    @Autowired
    HistoricoSucursalRepo historicoSucursalRepo;
    @Autowired
    EnvioAtrasadoRepositorio envioAtrasadoRepositorio;
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

    public List<EnvioSimple> getEnviosEnRuta(String estado){
        return this.envioSimpleRepo.findByEstado(estado);
    }

    public List<PasosEnvio> getPasosEnvio(int idEnvio){return this.envioPasosRepo.findByIdEnvioOrderByIdAsc(idEnvio);}
    @Transactional
    public int deletePasosEnvio(int idEnvio){return this.envioPasosRepo.deletePasosEnvioByIdEnvio(idEnvio);}

    public PasosEnvio savePasosEnvio(PasosEnvio envio){return  this.envioPasosRepo.save(envio);}

    public List<HistoricoSucursal> getHistorico(int idEnvio){
        return this.historicoSucursalRepo.findByIdEnvioOrderByFechaDesc(idEnvio);
    }

    public HistoricoSucursal saveHistoricoSucursal(HistoricoSucursal historico){
        return this.historicoSucursalRepo.save(historico);
    }

    public EnvioAtrasado getEnvioAtrasado(int idEnvioAtrasado){return this.envioAtrasadoRepositorio.getEnvioAtrasadoById(idEnvioAtrasado);}

    public EnvioAtrasado saveEnvioAtrasado(EnvioAtrasado envioAtrasado){return this.envioAtrasadoRepositorio.save(envioAtrasado);}

    @Transactional
    public int deleteEnvioAtrasado(int idEnvio){return this.envioAtrasadoRepositorio.deleteByIdEnvio(idEnvio);}
}
