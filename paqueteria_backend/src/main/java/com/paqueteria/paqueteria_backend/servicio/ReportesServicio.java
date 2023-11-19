package com.paqueteria.paqueteria_backend.servicio;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;


import java.util.List;


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

}
