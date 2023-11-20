package com.paqueteria.paqueteria_backend.servicio;

import com.paqueteria.paqueteria_backend.entidad.Municipio;
import com.paqueteria.paqueteria_backend.entidad.Ruta;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.entidad.dto.RutaDto;
import com.paqueteria.paqueteria_backend.repositorio.MunicipioRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.RutaDtoRepositorio;
import com.paqueteria.paqueteria_backend.repositorio.RutaRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutaServicio {

    private RutaRepositorio rutaRepositorio;
    private RutaDtoRepositorio rutaDtoRepositorio;

    /**
     * Constructor
     * @param rutaRepositorio
     */
    public RutaServicio(RutaRepositorio rutaRepositorio,RutaDtoRepositorio rutaDtoRepositorio) {
        this.rutaRepositorio = rutaRepositorio;
        this.rutaDtoRepositorio=rutaDtoRepositorio;
    }

    /**
     * Obtiene todas las rutas
     * @param
     * @return
     */

    public List<Ruta> obtenerRutas(){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Ruta> rutas=this.rutaRepositorio.findAll();


        return rutas;
    }

    public List<Ruta> obtenerRutasMapa(){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        List<Ruta> rutas=this.rutaRepositorio.findByOrigenEstado(true);


        return rutas;
    }

    public Optional<Ruta> obtenerRutaId(int id){
        //Hace la consulta por medio del repositorio que accede a la base de datos
        Optional<Ruta> rutas=this.rutaRepositorio.findById(id);


        return rutas;
    }

    public ResponseEntity<String> saveRuta(Ruta ruta){
        try {
            //Hace la consulta por medio del repositorio que accede a la base de datos
            this.rutaRepositorio.save(ruta);
            System.out.println("Lo guarda...");
            return new ResponseEntity<>("", HttpStatus.OK);

        } catch(Exception e){
            System.out.println("Error al guardar: \n"+e);
            return new ResponseEntity<>("", HttpStatus.CONFLICT);

        }
    }

    public ResponseEntity<String> saveRutas(RutaDto ruta){
        try {
            int origen=ruta.getOrigen();
            int destino=ruta.getDestino();
            //verificar que no exista
            var exist=this.rutaDtoRepositorio.findByOrigenAndDestino(origen,destino);
            if(exist.isPresent()){
                return new ResponseEntity<>("La ruta ya existe", HttpStatus.CONFLICT);
            }
            //Hace la consulta por medio del repositorio que accede a la base de datos

            this.rutaDtoRepositorio.save(ruta);

            ruta.setOrigen(destino);
            ruta.setDestino(origen);
            this.rutaDtoRepositorio.save(ruta);

            System.out.println("Lo guarda RutaDto ruta...");
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch(Exception e){
            System.out.println("Error al guardar RutaDto ruta: \n"+e);
            return new ResponseEntity<>("", HttpStatus.CONFLICT);

        }
    }

    public Ruta getByOD(Sucursal origen, Sucursal destino) {
        return this.rutaRepositorio.findByOrigenAndDestino(origen,destino);
    }

}
