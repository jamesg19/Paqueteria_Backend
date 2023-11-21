package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.ReporteSimulacion;
import com.paqueteria.paqueteria_backend.repositorio.ReportesSimulacionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteSimulacionServicio {
    @Autowired
    ReportesSimulacionRepo reporteRepo;

    public List<ReporteSimulacion> getAll(){
        return this.reporteRepo.findAll();
    }

    public ReporteSimulacion save(ReporteSimulacion entrada){
        return this.reporteRepo.save(entrada);
    }
}
