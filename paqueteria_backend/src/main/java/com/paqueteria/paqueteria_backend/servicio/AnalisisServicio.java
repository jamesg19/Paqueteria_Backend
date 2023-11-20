package com.paqueteria.paqueteria_backend.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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

    public List<Object[]> obtenerPorcentajeAumentoPorSucursal(String idSucursal){
        String sqlQuery = "SELECT DATE_FORMAT(fecha, '%Y-%m') AS Mes, SUM(total) AS TotalDelMes, COALESCE(LAG(SUM(total)) OVER (ORDER BY DATE_FORMAT(fecha, '%Y-%m')), 0) AS TotalDelMesAnterior, CASE WHEN COALESCE(LAG(SUM(total)) OVER (ORDER BY DATE_FORMAT(fecha, '%Y-%m')), 0) = 0 THEN 0 ELSE ((SUM(total) - LAG(SUM(total)) OVER (ORDER BY DATE_FORMAT(fecha, '%Y-%m'))) / LAG(SUM(total)) OVER (ORDER BY DATE_FORMAT(fecha, '%Y-%m'))) * 100 END AS PorcentajeDeAumento FROM Envio WHERE idSucursalOrigen = :idSucursal GROUP BY DATE_FORMAT(fecha, '%Y-%m') ORDER BY DATE_FORMAT(fecha, '%Y-%m');";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal", idSucursal);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    //SELECT idSucursal, nombre, direccion, latitud, longitud,estado, 2 * 6371 * ASIN(SQRT( POW(SIN((RADIANS(@latitudUbicacion) - RADIANS(latitud)) / 2), 2) + COS(RADIANS(@latitudUbicacion)) * COS(RADIANS(latitud)) * POW(SIN((RADIANS(@longitudUbicacion) - RADIANS(longitud)) / 2), 2) )) AS distancia FROM Sucursal WHERE estado = 1 ORDER BY distancia LIMIT 3;
    //SELECT idSucursal, nombre, direccion, latitud, longitud,estado, 2 * 6371 * ASIN(SQRT( POW(SIN((RADIANS(15.47497) - RADIANS(latitud)) / 2), 2) + COS(RADIANS(15.47497)) * COS(RADIANS(latitud)) * POW(SIN((RADIANS(-90316223) - RADIANS(longitud)) / 2), 2) )) AS distancia FROM Sucursal WHERE estado = 1 ORDER BY distancia LIMIT 3;
    //idSucursal | nombre                | direccion    | latitud   | longitud  | estado | distancia

    public List<Object[]> obtenerSucursalesMasCercanas(Double longitud, Double latitud){
        String sqlQuery = "SELECT idSucursal, nombre, direccion, latitud, longitud,estado, 2 * 6371 * ASIN(SQRT( POW(SIN((RADIANS(:latitud) - RADIANS(latitud)) / 2), 2) + COS(RADIANS(:latitud)) * COS(RADIANS(latitud)) * POW(SIN((RADIANS(:longitud) - RADIANS(longitud)) / 2), 2) )) AS distancia FROM Sucursal WHERE estado = 1 ORDER BY distancia LIMIT 3;";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("latitud", latitud);
        query.setParameter("longitud", longitud);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }


    public List<Object[]> obtenerVehiculos3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT idVehiculo,idSucursal,marca,tipo,placa,tonelage,anio,noAsientos,capacidadVol,capacidadTon FROM Vehiculo WHERE idSucursal =:idSucursal1 OR idSucursal=:idSucursal2 OR idSucursal =:idSucursal3";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerEnviosEnviados3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT idEnvio,idSucursalOrigen,idSucursalDestino,nitEmisor,nitReceptor,fecha,total,peso,volumen,diasTranscurridos,estado FROM Envio WHERE idSucursalOrigen = :idSucursal1 OR idSucursalOrigen = :idSucursal2 OR idSucursalOrigen = :idSucursal3";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerEnviosRecibidos3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT idEnvio,idSucursalOrigen,idSucursalDestino,nitEmisor,nitReceptor,fecha,total,peso,volumen,diasTranscurridos,estado FROM Envio WHERE idSucursalDestino = :idSucursal1 OR idSucursalDestino = :idSucursal2 OR idSucursalDestino = :idSucursal3";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerPersonal3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT * FROM Personal WHERE idSucursal = :idSucursal1 OR idSucursal = :idSucursal2 OR idSucursal = :idSucursal3";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerGastoPersonal3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT h.idHonorario, h.nombre, h.descripcion, h.monto, h.idAsamblea FROM paqueteria.Personal p JOIN paqueteria.Honorario h ON p.idPersonal = h.Personal_idPersonal WHERE p.idSucursal = :idSucursal1 OR p.idSucursal = :idSucursal2 OR p.idSucursal = :idSucursal3";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerGastoFijo3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT descripcion, SUM(monto) AS total_monto FROM Gasto WHERE tipo = 'FIJO' AND idSucursal IN(:idSucursal1,:idSucursal2,:idSucursal3) GROUP BY descripcion";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerGastoEspecial3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT descripcion, SUM(monto) AS total_monto FROM Gasto WHERE tipo = 'FIJO' AND idSucursal IN(:idSucursal1,:idSucursal2,:idSucursal3) GROUP BY descripcion";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public List<Object[]> obtenerGastoGasolina3Sucursales(String idSucursal1, String idSucursal2, String idSucursal3){
        String sqlQuery = "SELECT descripcion, SUM(monto) AS total_monto FROM Gasto WHERE descripcion = 'gasolina' AND idSucursal IN(:idSucursal1,:idSucursal2,:idSucursal3) GROUP BY descripcion";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal1", idSucursal1);
        query.setParameter("idSucursal2", idSucursal2);
        query.setParameter("idSucursal3", idSucursal3);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }







    

    
    
}
