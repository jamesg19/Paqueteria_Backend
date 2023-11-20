package com.paqueteria.paqueteria_backend.servicio;

import org.springframework.stereotype.Service;

import com.paqueteria.paqueteria_backend.repositorio.SucursalDtoRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

@Service
public class AnalisisServicio {
    private EntityManager entityManager;

    public AnalisisServicio(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public List<Object[]> obtenerGastoGasolinaPorSucursal(String idSucursal){
        String sqlQuery = "SELECT idGasto,idSucursal,descripcion,monto,tipo,Asamblea_idAsamblea FROM Gasto WHERE descripcion = 'gasolina' AND idSucursal = :idSucursal";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal", idSucursal);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerVehiculosPorSucursal(String idSucursal){
        String sqlQuery = "SELECT idVehiculo,idSucursal,marca,tipo,placa,tonelage,anio,noAsientos,capacidadVol,capacidadTon FROM Vehiculo WHERE idSucursal = :idSucursal";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal", idSucursal);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerTodosEnviosPorSucursal(String idSucursal){
        String sqlQuery = "SELECT idEnvio,idSucursalOrigen,idSucursalDestino,nitEmisor,nitReceptor,fecha,total,peso,volumen,diasTranscurridos,estado FROM Envio WHERE idSucursalOrigen = :idSucursal";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal", idSucursal);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    
    
}
