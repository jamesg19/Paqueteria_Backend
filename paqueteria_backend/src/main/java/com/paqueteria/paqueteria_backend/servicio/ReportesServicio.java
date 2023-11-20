package com.paqueteria.paqueteria_backend.servicio;


import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


@Service
public class ReportesServicio {
    private EntityManager entityManager;

    /**
     * Constructor
     * @param entityManager
     */
    public ReportesServicio(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public List<Object[]> obtenerTodosLosVehiculos(){
        String sqlQuery = "SELECT * FROM Vehiculo";

        Query query = entityManager.createNativeQuery(sqlQuery);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results;
    }

    public String obtenerCantidadPersonalTrabajanSucursal(String idSucursal){
        String sqlQuery = "SELECT COUNT(*) AS cantidad FROM Personal WHERE idSucursal = :idSucursal";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal", idSucursal);

        @SuppressWarnings("unchecked")
        List<Long> results = query.getResultList();

        if (!results.isEmpty()) {
            // Acceder al primer elemento y obtener el primer dato (índice 0)
            Object primerDatoObjeto = results.get(0);            
            return primerDatoObjeto.toString();
        } else {
            // Manejar el caso donde la lista está vacía
            //throw new RuntimeException("La lista está vacía.");
            return "0";
        }        
    }

    public List<Object[]> obtenerCantidadEnviosRecibidosEntreFechas(String idSucursal,String fecha1, String fecha2){
        String sqlQuery = "SELECT idEnvio,idSucursalOrigen,idSucursalDestino,nitEmisor,nitReceptor,fecha,total,peso,volumen,diasTranscurridos,estado FROM Envio WHERE idSucursalDestino = :idSucursal AND fecha BETWEEN :fecha1 AND :fecha2";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal", idSucursal);
        query.setParameter("fecha1", fecha1);
        query.setParameter("fecha2", fecha2);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerCantidadEnviosEnviadosEntreFechas(String idSucursal,String fecha1, String fecha2){
        String sqlQuery = "SELECT idEnvio,idSucursalOrigen,idSucursalDestino,nitEmisor,nitReceptor,fecha,total,peso,volumen,diasTranscurridos,estado FROM Envio WHERE idSucursalOrigen = :idSucursal AND fecha BETWEEN :fecha1 AND :fecha2";

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idSucursal", idSucursal);
        query.setParameter("fecha1", fecha1);
        query.setParameter("fecha2", fecha2);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerGastosFijosPorSucursal(String idSucursal){
        String sqlQuery = "SELECT idGasto,idSucursal,descripcion,monto,tipo,Asamblea_idAsamblea FROM Gasto WHERE tipo  = 'FIJO' AND idSucursal = :idSucursal";

        Query query = entityManager.createNativeQuery(sqlQuery);        
        query.setParameter("idSucursal", idSucursal);
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerAsambleaPorFecha(String fecha){
        String sqlQuery = "SELECT idAsamblea,fechaRealizada,lugarRealizada FROM Asamblea WHERE fechaRealizada = :fecha";

        Query query = entityManager.createNativeQuery(sqlQuery);        
        query.setParameter("fecha", fecha);
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerGastosEspeciales(String idSucursal,int idAsamblea){
        String sqlQuery = "SELECT idGasto,idSucursal,descripcion,monto,tipo,Asamblea_idAsamblea FROM Gasto WHERE tipo  = 'ESPECIAL' AND idSucursal = :idSucursal AND Asamblea_idAsamblea = :idAsamblea";

        Query query = entityManager.createNativeQuery(sqlQuery);        
        query.setParameter("idSucursal", idSucursal);
        query.setParameter("idAsamblea", idAsamblea);
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerMovimientosVehiculo(String vehiculo){
        String sqlQuery = "SELECT idHistorial_Sucursales_Vehiculo,idVehiculo,idSucursal,ocupacion,fecha FROM Historial_Sucursales_Vehiculo WHERE idVehiculo = :vehiculo";

        Query query = entityManager.createNativeQuery(sqlQuery);        
        query.setParameter("vehiculo", vehiculo);        
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerGastoPersonalPorSucursalYAsamblea(String idSucursal,int idAsamblea){
        String sqlQuery = "SELECT h.idHonorario, h.nombre, h.descripcion, h.monto, h.idAsamblea FROM paqueteria.Personal p JOIN paqueteria.Honorario h ON p.idPersonal = h.Personal_idPersonal WHERE p.idSucursal = :idSucursal AND h.idAsamblea = :idAsamblea";

        Query query = entityManager.createNativeQuery(sqlQuery);        
        query.setParameter("idSucursal", idSucursal);
        query.setParameter("idAsamblea", idAsamblea);
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        
        
        return results;
    }


    public List<Object[]> obtenerEnviosPorEstadoEntreFechas(String estado, String fecha1, String fecha2){
        String sqlQuery = "SELECT idEnvio,idSucursalOrigen,idSucursalDestino,nitEmisor,nitReceptor,fecha,total,peso,volumen,diasTranscurridos,estado FROM Envio WHERE estado = :estado AND fecha BETWEEN :fecha1 AND :fecha2";

        Query query = entityManager.createNativeQuery(sqlQuery);        
        query.setParameter("estado", estado);
        query.setParameter("fecha1", fecha1);
        query.setParameter("fecha2", fecha2);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerTodosEnviosEntreFechas(String fecha1, String fecha2){
        String sqlQuery = "SELECT idEnvio,idSucursalOrigen,idSucursalDestino,nitEmisor,nitReceptor,fecha,total,peso,volumen,diasTranscurridos,estado FROM Envio WHERE fecha BETWEEN :fecha1 AND :fecha2";

        Query query = entityManager.createNativeQuery(sqlQuery);                
        query.setParameter("fecha1", fecha1);
        query.setParameter("fecha2", fecha2);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerTodosGastosFijosAgrupadosPorNombre(){
        String sqlQuery = "SELECT descripcion, SUM(monto) AS total_monto FROM Gasto WHERE tipo = 'FIJO' GROUP BY descripcion";

        Query query = entityManager.createNativeQuery(sqlQuery);                
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerTodosGastosEspecialesPorAsambleaAgrupados(int idAsamblea){
        String sqlQuery = "SELECT descripcion, SUM(monto) FROM Gasto WHERE tipo  = 'ESPECIAL' AND Asamblea_idAsamblea = :idAsamblea GROUP BY descripcion";

        Query query = entityManager.createNativeQuery(sqlQuery);                
        query.setParameter("idAsamblea", idAsamblea);
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerTodosGastoPersonalPorAsamblea(int idAsamblea){
        String sqlQuery = "SELECT h.idHonorario, h.nombre, h.descripcion, h.monto, h.idAsamblea FROM paqueteria.Personal p JOIN paqueteria.Honorario h ON p.idPersonal = h.Personal_idPersonal WHERE h.idAsamblea = :idAsamblea";

        Query query = entityManager.createNativeQuery(sqlQuery);                
        query.setParameter("idAsamblea", idAsamblea);
        

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        
        
        return results;
    }

    public List<Object[]> obtenerSucursalesMasGananciasEntreFechas(String fecha1, String fecha2){
        String sqlQuery = "SELECT s.idSucursal, s.nombre, SUM(e.total) AS ganancias_totales FROM paqueteria.Sucursal s JOIN paqueteria.Envio e ON s.idSucursal = e.idSucursalOrigen WHERE e.fecha BETWEEN :fecha1 AND :fecha2 GROUP BY s.idSucursal ORDER BY ganancias_totales DESC LIMIT 10";

        Query query = entityManager.createNativeQuery(sqlQuery);                
        query.setParameter("fecha1", fecha1);
        query.setParameter("fecha2", fecha2);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

    public List<Object[]> obtenerSucursalesMenosGananciasEntreFechas(String fecha1, String fecha2){
        String sqlQuery = "SELECT s.idSucursal, s.nombre, IFNULL(SUM(e.total), 0) AS ganancias_totales FROM paqueteria.Sucursal s LEFT JOIN paqueteria.Envio e ON s.idSucursal = e.idSucursalOrigen AND e.fecha BETWEEN :fecha1 AND :fecha2 GROUP BY s.idSucursal ORDER BY ganancias_totales ASC LIMIT 10;";

        Query query = entityManager.createNativeQuery(sqlQuery);                
        query.setParameter("fecha1", fecha1);
        query.setParameter("fecha2", fecha2);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();        

        return results;
    }

}
