package com.paqueteria.paqueteria_backend.controlador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paqueteria.paqueteria_backend.analisis.AnalisisVehiculo;
import com.paqueteria.paqueteria_backend.analisis.DatoAnalisis;
import com.paqueteria.paqueteria_backend.analisis.MultiDatos;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/analisis")
public class AnalisisControlador {

    /**
     * Constructor del repositorio
     * Encargado de recibir las peticiones Http
     * @param AnalisisServicio
     
    public AnalisisControlador(AnalisisServicio analisisServicio) {
        this.AnalisisServicio = analisisServicio;
    }
    */

    @GetMapping("/nuevosVehiculos")
    public AnalisisVehiculo getAnalisis(HttpServletRequest request, HttpServletResponse response, 
        @RequestParam String cantidadVehiculos, @RequestParam String sucursalForm)  {
        AnalisisVehiculo analisisVehiculo = new AnalisisVehiculo();
        analisisVehiculo.setDescripcion("La descripcion del analisis es:" + cantidadVehiculos);
        analisisVehiculo.setRecomendacion("La recomendacion del analisis es:" + sucursalForm);
        DatoAnalisis dato1 = new DatoAnalisis("Estimado de envios realizados por nuevos vehiculos","14");
        DatoAnalisis dato2 = new DatoAnalisis("Estimado de gastos por gasolina","14");
        DatoAnalisis dato3 = new DatoAnalisis("Estimado de ganancias","14");

        //Datos de multidatos
        DatoAnalisis datoG1 = new DatoAnalisis("0","0");
        DatoAnalisis datoG2 = new DatoAnalisis("6 meses","14");
        DatoAnalisis datoG3 = new DatoAnalisis("1 año","50");
        DatoAnalisis datoG4 = new DatoAnalisis("1 año","55");

        MultiDatos multi1 = new MultiDatos();
        multi1.setName("Gastos");
        multi1.insertDatoAnalisis(datoG1);
        multi1.insertDatoAnalisis(datoG2);
        multi1.insertDatoAnalisis(datoG3);

        MultiDatos multi2 = new MultiDatos();
        multi2.setName("Ganancias");
        multi2.insertDatoAnalisis(datoG1);
        multi2.insertDatoAnalisis(datoG2);
        multi2.insertDatoAnalisis(datoG4);

        analisisVehiculo.insertDatoAnalisis(dato1);
        analisisVehiculo.insertDatoAnalisis(dato2);
        analisisVehiculo.insertDatoAnalisis(dato3);

        analisisVehiculo.insertMultiDato(multi1);
        analisisVehiculo.insertMultiDato(multi2);
        
        //Traer la sucursal que se le quiere ingresar los vehiculos
        
        //Traer la cantidad de vehiculos que tiene la sucursal
        
        //Traer los gastos que realiza de gasolina al mes

        //Dividir los gastos de gasolina en la cantidad de vehiculos que tiene

        //Con eso se obtiene el gasto de gasolina extra que se realiza


        


        return analisisVehiculo;
    }

}
